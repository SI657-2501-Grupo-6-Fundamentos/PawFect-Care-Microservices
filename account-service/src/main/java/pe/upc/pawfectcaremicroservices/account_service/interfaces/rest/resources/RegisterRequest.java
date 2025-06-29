package pe.upc.pawfectcaremicroservices.account_service.interfaces.rest.resources;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RegisterRequest {
    private String userName;
    private String password;
    private String role;
    private String fullName;
    private String phoneNumber;
    private String email;
    private String address;
}