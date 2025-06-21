/**
 * Pet aggregate
 * @Summary
 * The petmanagement  class is an aggregate that represents a  petmanagement .
 */
package pe.upc.pawfectcaremicroservices.petmanagement.domain.model.aggregates;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;
import pe.upc.pawfectcaremicroservices.petmanagement.domain.model.commands.CreatePetCommand;
import pe.upc.pawfectcaremicroservices.petmanagement.domain.model.valueobjects.PetGender;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "pets")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String petName;
    private LocalDate birthDate;
    private LocalDate registrationDate;
    private String animalBreed;
    @Enumerated(EnumType.STRING)
    private PetGender petGender;
    private Long ownerId;


    public Pet() {
        this.petName = Strings.EMPTY;
        this.birthDate = LocalDate.now();
        this.registrationDate = LocalDate.now();
        this.animalBreed = Strings.EMPTY;
        this.petGender = PetGender.FEMALE;
    }

    public Pet updateInformation(String petName, LocalDate birthDate,LocalDate registrationDate, String animalBreed, PetGender petGender) {
        this.petName = petName;
        this.birthDate = birthDate;
        this.registrationDate = registrationDate;
        this.animalBreed = animalBreed;
        this.petGender = petGender;
        return this;
    }

    public Pet(CreatePetCommand command) {
        this.petName = command.petName();
        this.birthDate = command.birthDate();
        this.registrationDate = command.registrationDate();
        this.animalBreed = command.animalBreed();
        this.petGender = command.petGender();
    }


}