package pe.upc.pawfectcaremicroservices.iam_service.interfaces.rest.resources;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginRequest {
    private String userName;
    private String password;
}