package pe.upc.pawfectcaremicroservices.petmanagement.application;

import org.springframework.stereotype.Service;
import pe.upc.pawfectcaremicroservices.petmanagement.domain.model.aggregates.Owner;
import pe.upc.pawfectcaremicroservices.petmanagement.domain.model.commands.CreateOwnerCommand;
import pe.upc.pawfectcaremicroservices.petmanagement.domain.model.commands.UpdateOwnerCommand;
import pe.upc.pawfectcaremicroservices.petmanagement.domain.services.OwnerCommandService;
import pe.upc.pawfectcaremicroservices.petmanagement.infrastructure.persistence.jpa.repositories.OwnerRepository;
import pe.upc.pawfectcaremicroservices.petmanagement.infrastructure.persistence.jpa.repositories.PetRepository;

import java.util.Optional;

@Service
public class OwnerCommandServiceImpl implements OwnerCommandService {
    private  final OwnerRepository ownerRepository;

    public OwnerCommandServiceImpl(OwnerRepository ownerRepository , PetRepository petRepository ) {
       this.ownerRepository = ownerRepository;
    }

    @Override
    public Long handle(CreateOwnerCommand command) {
        if (ownerRepository.existsByEmail(command.email())) {
            throw new IllegalArgumentException("Owner with same email already exists");
        }
        var owner = new Owner(command);
        try {
            ownerRepository.save(owner);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving owner: " + e.getMessage());
        }
        return owner.getId();
    }

    @Override
    public Optional<Owner> handle(UpdateOwnerCommand command) {
        if (!ownerRepository.existsById(command.id())) throw new IllegalArgumentException("ownerId does not exist");
        var result = ownerRepository.findById(command.id());
        var ownerToUpdate = result.get();
        try {
            var updatedOwner = ownerRepository.save(ownerToUpdate.updateInformation(command.fullName(),command.phoneNumber(),command.email(),command.address()));
            return Optional.of(updatedOwner);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating owner: " + e.getMessage());
        }
    }


}
