package pe.upc.pawfectcaremicroservices.iam_service.interfaces.rest.transform;


import pe.upc.pawfectcaremicroservices.iam_service.domain.model.aggregates.User;
import pe.upc.pawfectcaremicroservices.iam_service.domain.model.aggregates.UserAdmin;
import pe.upc.pawfectcaremicroservices.iam_service.interfaces.rest.resources.AuthenticatedGeneralUserResource;

public class AuthenticatedGeneralUserResourceFromEntityAssembler {
    public static AuthenticatedGeneralUserResource toResourceFromEntity(User user, String token) {
        return new AuthenticatedGeneralUserResource(user.getId(), user.getUserName(), token);
    }

    public static AuthenticatedGeneralUserResource toResourceAdminFromEntity(UserAdmin admin, String token) {
        return new AuthenticatedGeneralUserResource(admin.getId(), admin.getUserName(), token);
    }
}
