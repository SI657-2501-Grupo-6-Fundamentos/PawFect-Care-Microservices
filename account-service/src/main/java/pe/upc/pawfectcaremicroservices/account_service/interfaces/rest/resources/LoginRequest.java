package pe.upc.pawfectcaremicroservices.account_service.interfaces.rest.resources;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginRequest {
    private String email;
    private String password;
}