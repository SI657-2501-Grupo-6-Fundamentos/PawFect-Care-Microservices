package pe.upc.pawfectcaremicroservices.iam_service.interfaces.rest.resources;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class RegisterAdminRequest {
    private String userName;
    private String password;
    private String role;
    private String fullName;
    private String phoneNumber;
    private String email;
    private String dni;

    @JsonProperty("speciality")
    private String veterinarianSpeciality;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime availableStartTime;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime availableEndTime;
}
