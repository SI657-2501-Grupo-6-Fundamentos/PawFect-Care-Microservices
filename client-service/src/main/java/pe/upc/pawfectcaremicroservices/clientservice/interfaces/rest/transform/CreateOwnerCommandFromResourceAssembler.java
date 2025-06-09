package pe.upc.pawfectcaremicroservices.clientservice.interfaces.rest.transform;

import pe.upc.pawfectcaremicroservices.clientservice.domain.model.commands.CreateOwnerCommand;
import pe.upc.pawfectcaremicroservices.clientservice.interfaces.rest.resources.CreateOwnerResource;

public class CreateOwnerCommandFromResourceAssembler {
    public static CreateOwnerCommand toCommandFromResource(CreateOwnerResource resource) {
        return new CreateOwnerCommand(
                resource.fullName(),
                resource.phoneNumber(),
                resource.email(),
                resource.address());
    }
}

