package pe.upc.pawfectcaremicroservices.iam_service.interfaces.rest.transform;

import pe.upc.pawfectcaremicroservices.iam_service.domain.model.aggregates.UserAdmin;
import pe.upc.pawfectcaremicroservices.iam_service.domain.model.entities.Role;
import pe.upc.pawfectcaremicroservices.iam_service.interfaces.rest.resources.UserAdminResource;

public class UserAdminResourceFromEntityAssembler {
    public static UserAdminResource toResourceFromEntity(UserAdmin userAdmin) {
        var roles = userAdmin.getRoles().stream().map(Role::getStringName).toList();
        return new UserAdminResource(userAdmin.getId(), userAdmin.getUsername(), roles);
    }
}
