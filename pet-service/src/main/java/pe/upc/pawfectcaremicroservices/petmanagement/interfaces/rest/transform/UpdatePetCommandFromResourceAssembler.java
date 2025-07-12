package pe.upc.pawfectcaremicroservices.petmanagement.interfaces.rest.transform;

import pe.upc.pawfectcaremicroservices.petmanagement.domain.model.commands.UpdatePetCommand;
import pe.upc.pawfectcaremicroservices.petmanagement.interfaces.rest.resources.UpdatePetResource;

public class UpdatePetCommandFromResourceAssembler {
    public static UpdatePetCommand toCommandFromResource(Long petId, UpdatePetResource resource) {
        return new UpdatePetCommand(
                petId,
                resource.petName(),
                resource.birthDate(),
                resource.registrationDate(),
                resource.animalType(),
                resource.animalBreed(),
                resource.petGender(),
                resource.imageUrl());
    }
}