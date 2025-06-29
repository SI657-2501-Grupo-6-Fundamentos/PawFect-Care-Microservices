package pe.upc.pawfectcaremicroservices.iam_service.interfaces.rest.transform;

import pe.upc.pawfectcaremicroservices.iam_service.domain.model.commands.GoogleSignInCommand;
import pe.upc.pawfectcaremicroservices.iam_service.interfaces.rest.resources.GoogleSignInResource;

public class GoogleSignInCommandFromResourceAssembler {

    public static GoogleSignInCommand toCommandFromResource(GoogleSignInResource resource) {
        return new GoogleSignInCommand(resource.googleToken());
    }
}
