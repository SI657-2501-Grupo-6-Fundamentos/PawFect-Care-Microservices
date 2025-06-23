package pe.upc.pawfectcaremicroservices.petownerservice.interfaces.rest.transform;

import pe.upc.pawfectcaremicroservices.petownerservice.domain.model.commands.CreatePetOwnerCommand;
import pe.upc.pawfectcaremicroservices.petownerservice.interfaces.rest.resources.CreatePetOwnerResource;

public class CreatePetOwnerCommandFromResourceAssembler {
    public static CreatePetOwnerCommand toCommandFromResource(CreatePetOwnerResource resource) {
        return new CreatePetOwnerCommand(
                resource.userId(),
                resource.fullName(),
                resource.phoneNumber(),
                resource.email(),
                resource.address());
    }
}

