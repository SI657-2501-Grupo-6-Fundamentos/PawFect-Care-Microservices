package pe.upc.pawfectcaremicroservices.iam_service.interfaces.rest.transform;

import pe.upc.pawfectcaremicroservices.iam_service.domain.model.aggregates.UserAdmin;
import pe.upc.pawfectcaremicroservices.iam_service.domain.model.entities.Role;
import pe.upc.pawfectcaremicroservices.iam_service.interfaces.rest.transform.UserAdminResource;

import java.util.stream.Collectors;

public class UserAdminResourceFromEntityAssembler {
    public static UserAdminResource toResourceFromEntity(UserAdmin userAdmin) {
        UserAdminResource resource = new UserAdminResource();
        resource.setId(userAdmin.getId());
        resource.setUserName(userAdmin.getUserName());
        resource.setFullName(userAdmin.getFullName());
        resource.setPhoneNumber(userAdmin.getPhoneNumber());
        resource.setEmail(userAdmin.getEmail());
        resource.setRoles(
                userAdmin.getRoles().stream()
                        .map(r -> r.getName().name())
                        .collect(Collectors.toSet())
        );
        resource.setDni(userAdmin.getDni());
        resource.setVeterinarianSpeciality(userAdmin.getVeterinarianSpeciality().getValue());
        resource.setAvailableStartTime(userAdmin.getAvailableStartTime().toString());
        resource.setAvailableEndTime(userAdmin.getAvailableEndTime().toString());

        return resource;
    }
}
