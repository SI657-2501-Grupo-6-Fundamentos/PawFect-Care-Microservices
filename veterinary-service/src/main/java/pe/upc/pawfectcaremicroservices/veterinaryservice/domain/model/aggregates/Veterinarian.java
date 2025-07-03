package pe.upc.pawfectcaremicroservices.veterinaryservice.domain.model.aggregates;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pe.upc.pawfectcaremicroservices.veterinaryservice.domain.model.commands.CreateVeterinarianCommand;
import pe.upc.pawfectcaremicroservices.veterinaryservice.domain.model.valueobjects.VeterinarianSpeciality;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "veterinarians")
public class Veterinarian {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String fullName;
    private String phoneNumber;
    private String email;
    private String dni;
    @Enumerated(EnumType.STRING)
    private VeterinarianSpeciality veterinarianSpeciality;

    // Optional fields to be set automatically after schedule creation
    //private String availableDays;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime availableStartTime;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime availableEndTime;

    public Veterinarian() {
        this.fullName = "";
        this.phoneNumber = "";
        this.dni = "";
        this.email = "";
        this.veterinarianSpeciality = VeterinarianSpeciality.GENERAL_MEDICINE;
        this.availableStartTime = LocalDateTime.now();
        this.availableEndTime = LocalDateTime.now().plusHours(8);
    }

    public Veterinarian(CreateVeterinarianCommand createVeterinarianCommand) {
        this.userId = createVeterinarianCommand.userId();
        this.fullName = createVeterinarianCommand.fullName();
        this.phoneNumber = createVeterinarianCommand.phoneNumber();
        this.email = createVeterinarianCommand.email();
        this.dni = createVeterinarianCommand.dni();
        this.veterinarianSpeciality = VeterinarianSpeciality.fromValue(createVeterinarianCommand.veterinarianSpeciality());
        this.availableStartTime = createVeterinarianCommand.availableStartTime();
        this.availableEndTime = createVeterinarianCommand.availableEndTime();
    }

    public Veterinarian updateInformation(String fullName, String phoneNumber, String email, String dni, VeterinarianSpeciality veterinarianSpeciality) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.dni = dni;
        this.veterinarianSpeciality = veterinarianSpeciality;
        return this;
    }

    public Veterinarian updateAvailability(LocalDateTime start, LocalDateTime end) {
        this.availableStartTime = start;
        this.availableEndTime = end;
        return this;
    }
}
