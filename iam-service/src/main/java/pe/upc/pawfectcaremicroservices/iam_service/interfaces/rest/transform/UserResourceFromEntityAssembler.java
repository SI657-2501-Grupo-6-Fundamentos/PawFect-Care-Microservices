package pe.upc.pawfectcaremicroservices.iam_service.interfaces.rest.transform;

import pe.upc.pawfectcaremicroservices.iam_service.domain.model.aggregates.User;

import java.util.Optional;
import java.util.stream.Collectors;

public class UserResourceFromEntityAssembler {
    public static UserResource toResourceFromEntity(User user) {
        UserResource resource = new UserResource();
        resource.setId(user.getId());
        resource.setUserName(user.getUserName());
        resource.setFullName(user.getFullName());
        resource.setPhoneNumber(user.getPhoneNumber());
        resource.setEmail(user.getEmail());
        resource.setAddress(user.getAddress());
        resource.setRoles(
                user.getRoles().stream()
                        .map(r -> r.getName().name())
                        .collect(Collectors.toSet())
        );
        return resource;
    }
}
