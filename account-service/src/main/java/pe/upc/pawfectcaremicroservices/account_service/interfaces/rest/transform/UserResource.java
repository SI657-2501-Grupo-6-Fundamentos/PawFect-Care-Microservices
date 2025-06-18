package pe.upc.pawfectcaremicroservices.account_service.interfaces.rest.transform;

import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Getter @Setter
public class UserResource {
    private Long id;
    private String email;
    private Set<String> roles;
}