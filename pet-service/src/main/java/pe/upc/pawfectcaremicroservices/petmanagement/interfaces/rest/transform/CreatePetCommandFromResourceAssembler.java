package pe.upc.pawfectcaremicroservices.petmanagement.interfaces.rest.transform;

import pe.upc.pawfectcaremicroservices.petmanagement.domain.model.commands.CreatePetCommand;
import pe.upc.pawfectcaremicroservices.petmanagement.interfaces.rest.resources.CreatePetResource;

public class CreatePetCommandFromResourceAssembler {
    public static CreatePetCommand toCommandFromResource(CreatePetResource resource) {
        return new CreatePetCommand(
                resource.petName(),
                resource.birthDate(),
                resource.registrationDate(),
                resource.animalType(),
                resource.animalBreed(),
                resource.petGender(),
                resource.ownerId());
    }
}
