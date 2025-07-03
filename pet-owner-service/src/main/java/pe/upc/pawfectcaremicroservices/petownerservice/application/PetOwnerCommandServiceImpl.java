package pe.upc.pawfectcaremicroservices.petownerservice.application;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pe.upc.pawfectcaremicroservices.petownerservice.domain.model.aggregates.PetOwner;
import pe.upc.pawfectcaremicroservices.petownerservice.domain.model.commands.CreatePetOwnerCommand;
import pe.upc.pawfectcaremicroservices.petownerservice.domain.model.commands.UpdatePetOwnerCommand;
import pe.upc.pawfectcaremicroservices.petownerservice.domain.services.PetOwnerCommandService;
import pe.upc.pawfectcaremicroservices.petownerservice.infrastructure.persistence.jpa.repositories.PetOwnerRepository;

import java.util.Optional;

@Service
public class PetOwnerCommandServiceImpl implements PetOwnerCommandService {
    private  final PetOwnerRepository petOwnerRepository;

    public PetOwnerCommandServiceImpl(PetOwnerRepository petOwnerRepository) {
       this.petOwnerRepository = petOwnerRepository;
    }

    @Override
    public Long handle(CreatePetOwnerCommand command) {
        if (petOwnerRepository.existsByEmail(command.email())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Owner with same email already exists");
        }
        var owner = new PetOwner(command);
        try {
            petOwnerRepository.save(owner);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving owner: " + e.getMessage());
        }
        return owner.getId();
    }

    @Override
    public Optional<PetOwner> handle(UpdatePetOwnerCommand command) {
        if (!petOwnerRepository.existsById(command.id())) throw new IllegalArgumentException("ownerId does not exist");
        var result = petOwnerRepository.findById(command.id());
        var ownerToUpdate = result.get();
        try {
            var updatedOwner = petOwnerRepository.save(ownerToUpdate.updateInformation(command.fullName(),command.phoneNumber(),command.email(),command.address()));
            return Optional.of(updatedOwner);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating owner: " + e.getMessage());
        }
    }


}
