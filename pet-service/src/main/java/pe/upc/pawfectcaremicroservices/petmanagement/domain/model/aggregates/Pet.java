/**
 * Pet aggregate
 * @Summary
 * The pet class is an aggregate root that represents a pet in the system.
 */
package pe.upc.pawfectcaremicroservices.petmanagement.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;
import pe.upc.pawfectcaremicroservices.petmanagement.domain.model.commands.CreatePetCommand;
import pe.upc.pawfectcaremicroservices.petmanagement.domain.model.valueobjects.AnimalType;
import pe.upc.pawfectcaremicroservices.petmanagement.domain.model.valueobjects.Breed;
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

    @Enumerated(EnumType.STRING)
    private AnimalType animalType;

    @Enumerated(EnumType.STRING)
    private Breed animalBreed;

    @Enumerated(EnumType.STRING)
    private PetGender petGender;

    private Long ownerId;


    public Pet() {
        this.petName = Strings.EMPTY;
        this.birthDate = LocalDate.now();
        this.registrationDate = LocalDate.now();
        this.animalType = AnimalType.DOG;
        this.animalBreed = Breed.LABRADOR;
        this.petGender = PetGender.FEMALE;
    }

    public Pet updateInformation(String petName, LocalDate birthDate,LocalDate registrationDate, AnimalType animalType, Breed animalBreed, PetGender petGender) {
        this.petName = petName;
        this.birthDate = birthDate;
        this.registrationDate = registrationDate;
        this.animalType = animalType;
        this.animalBreed = animalBreed;
        this.petGender = petGender;
        return this;
    }

    public Pet(CreatePetCommand command) {
        this.petName = command.petName();
        this.birthDate = command.birthDate();
        this.registrationDate = command.registrationDate();
        this.animalType = command.animalType();
        this.animalBreed = command.animalBreed();
        this.petGender = command.petGender();
    }


}