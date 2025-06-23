package pe.upc.pawfectcaremicroservices.petownerservice.interfaces.rest.transform;

import pe.upc.pawfectcaremicroservices.petownerservice.domain.model.commands.UpdatePetOwnerCommand;
import pe.upc.pawfectcaremicroservices.petownerservice.interfaces.rest.resources.UpdatePetOwnerResource;

public class UpdatePetOwnerCommandFromResourceAssembler {
    public static UpdatePetOwnerCommand toCommandFromResource(Long ownerId, UpdatePetOwnerResource resource) {
        return new UpdatePetOwnerCommand(
                ownerId,
                resource.fullName(),
                resource.phoneNumber(),
                resource.email(),
                resource.address());
    }
}
