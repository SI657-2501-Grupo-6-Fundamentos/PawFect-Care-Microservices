package pe.upc.pawfectcaremicroservices.feedback.application.external;


import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Veterinarian {
    private Long id;
    private String fullName;
    private String phoneNumber;
    private String email;
    private String dni;
    @Enumerated(EnumType.STRING)
    private VeterinarianSpeciality veterinarianSpeciality;

    public Veterinarian() {
        this.fullName = "";
        this.phoneNumber = "";
        this.dni = "";
        this.email = "";
        this.veterinarianSpeciality = VeterinarianSpeciality.GENERAL_MEDICINE;
    }
}