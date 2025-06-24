package pe.upc.pawfectcaremicroservices.iam_service.interfaces.rest.transform;


import pe.upc.pawfectcaremicroservices.iam_service.domain.model.aggregates.UserAdmin;
import pe.upc.pawfectcaremicroservices.iam_service.interfaces.rest.resources.AuthenticatedUserAdminResource;

public class AuthenticatedUserAdminResourceFromEntityAssembler {
    public static AuthenticatedUserAdminResource toResourceFromEntity(UserAdmin userAdmin, String token) {
        return new AuthenticatedUserAdminResource(userAdmin.getId(), userAdmin.getUsername(), token);
    }
}
