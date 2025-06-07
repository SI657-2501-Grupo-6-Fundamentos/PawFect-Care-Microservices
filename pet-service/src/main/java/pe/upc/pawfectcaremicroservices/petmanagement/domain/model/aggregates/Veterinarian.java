package pe.upc.pawfectcaremicroservices.petmanagement.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pe.upc.pawfectcaremicroservices.petmanagement.domain.model.commands.CreateVeterinarianCommand;
import pe.upc.pawfectcaremicroservices.petmanagement.domain.model.valueobjects.VeterinarianSpeciality;

@Getter
@Setter
@Entity
@Table(name = "veterinarians")
public class Veterinarian {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public Veterinarian(CreateVeterinarianCommand createVeterinarianCommand) {
        this.fullName = createVeterinarianCommand.fullName();
        this.phoneNumber = createVeterinarianCommand.phoneNumber();
        this.email = createVeterinarianCommand.email();
        this.dni = createVeterinarianCommand.dni();
        this.veterinarianSpeciality = VeterinarianSpeciality.valueOf(createVeterinarianCommand.veterinarianSpeciality());
    }

    public Veterinarian updateInformation(String fullName, String phoneNumber, String email, String dni, VeterinarianSpeciality veterinarianSpeciality) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.dni = dni;
        this.veterinarianSpeciality = veterinarianSpeciality;
        return this;
    }
}
