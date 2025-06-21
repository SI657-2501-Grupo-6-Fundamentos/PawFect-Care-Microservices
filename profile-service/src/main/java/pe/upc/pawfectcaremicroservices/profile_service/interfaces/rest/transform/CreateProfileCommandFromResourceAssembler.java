package pe.upc.pawfectcaremicroservices.profile_service.interfaces.rest.transform;

import pe.upc.pawfectcaremicroservices.profile_service.domain.model.commands.CreateProfileCommand;
import pe.upc.pawfectcaremicroservices.profile_service.interfaces.rest.resources.CreateProfileResource;

public class CreateProfileCommandFromResourceAssembler {
    public static CreateProfileCommand toCommandFromResource(CreateProfileResource resource) {
        return new CreateProfileCommand(
                resource.iamUserId(),
                resource.type(),
                resource.profileData()
        );
    }
}