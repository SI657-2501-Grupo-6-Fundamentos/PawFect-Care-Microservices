package pe.upc.pawfectcaremicroservices.iam_service.interfaces.rest.transform;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter @Setter
public class UserAdminResource {
    private Long id;
    private String userName;
    private String fullName;
    private String phoneNumber;
    private String email;
    private Set<String> roles;
    private String dni;
    private String veterinarianSpeciality;
    private String availableStartTime;
    private String availableEndTime;
}
