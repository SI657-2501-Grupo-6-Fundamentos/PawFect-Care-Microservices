package pe.upc.pawfectcaremicroservices.account_service.interfaces.rest.transform;

import pe.upc.pawfectcaremicroservices.account_service.domain.model.aggregates.User;
import java.util.stream.Collectors;

public class UserResourceAssembler {
    public static UserResource toResource(User user) {
        UserResource resource = new UserResource();
        resource.setId(user.getId());
        resource.setUserName(user.getUserName());
        resource.setRoles(
                user.getRoles().stream()
                        .map(r -> r.getName().name())
                        .collect(Collectors.toSet())
        );
        return resource;
    }
}