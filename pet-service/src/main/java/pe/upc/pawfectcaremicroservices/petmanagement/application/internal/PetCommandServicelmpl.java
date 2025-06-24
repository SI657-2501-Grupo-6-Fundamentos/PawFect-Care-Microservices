package pe.upc.pawfectcaremicroservices.petmanagement.application.internal;

import org.springframework.stereotype.Service;
import pe.upc.pawfectcaremicroservices.petmanagement.domain.model.aggregates.Pet;
import pe.upc.pawfectcaremicroservices.petmanagement.domain.model.commands.CreatePetCommand;
import pe.upc.pawfectcaremicroservices.petmanagement.domain.model.commands.DeletePetCommand;
import pe.upc.pawfectcaremicroservices.petmanagement.domain.model.commands.UpdatePetCommand;
import pe.upc.pawfectcaremicroservices.petmanagement.domain.services.PetCommandService;
import pe.upc.pawfectcaremicroservices.petmanagement.application.external.clients.ExternalOwner;
import pe.upc.pawfectcaremicroservices.petmanagement.infrastructure.persistence.jpa.repositories.PetRepository;

import java.util.Optional;

@Service
public class PetCommandServicelmpl implements PetCommandService {
    private final PetRepository petRepository;
    private final ExternalOwner externalOwner;

    public PetCommandServicelmpl(PetRepository petRepository, ExternalOwner externalOwner) {
        this.petRepository = petRepository;
        this.externalOwner = externalOwner;
    }

    @Override
    public Long handle(CreatePetCommand command) {
        var pet = new Pet(command);
        try {
            if (!externalOwner.existsOwnerById(command.ownerId()))
                throw new IllegalArgumentException("ownerId does not exist");
            //
            pet.setOwnerId(command.ownerId());
            petRepository.save(pet);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving pet: " + e.getMessage());
        }
        return pet.getId();
    }


    @Override
    public Optional<Pet> handle(UpdatePetCommand command) {
        if (!petRepository.existsById(command.id())) throw new IllegalArgumentException("petId does not exist");
        var result = petRepository.findById(command.id());
        if (result.isEmpty()) throw new IllegalArgumentException("Pet does not exist");
        var petToUpdate = result.get();
        try {
            var updatedPet = petRepository.save(petToUpdate.updateInformation(
                    command.petName(),
                    command.birthDate(),
                    command.registrationDate(),
                    command.animalType(),
                    command.animalBreed(),
                    command.petGender()));
            return Optional.of(updatedPet);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating pet: " + e.getMessage());
        }
    }


    @Override
    public void handle(DeletePetCommand command) {
        if (!petRepository.existsById(command.petId())) {
            throw new IllegalArgumentException("Pe does not exist");
        }
        try {
            petRepository.deleteById(command.petId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting pet: " + e.getMessage());
        }

    }
}
