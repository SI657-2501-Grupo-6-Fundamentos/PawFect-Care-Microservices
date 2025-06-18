package pe.upc.pawfectcaremicroservices.clientservice.interfaces.rest.transform;

import pe.upc.pawfectcaremicroservices.clientservice.domain.model.commands.UpdateOwnerCommand;
import pe.upc.pawfectcaremicroservices.clientservice.interfaces.rest.resources.UpdateOwnerResource;

public class UpdateOwnerCommandFromResourceAssembler {
    public static UpdateOwnerCommand toCommandFromResource(Long ownerId, UpdateOwnerResource resource) {
        return new UpdateOwnerCommand(
                ownerId,
                resource.fullName(),
                resource.phoneNumber(),
                resource.email(),
                resource.address());
    }
}
