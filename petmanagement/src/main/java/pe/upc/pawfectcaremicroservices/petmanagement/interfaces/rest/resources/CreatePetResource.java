package pe.upc.pawfectcaremicroservices.petmanagement.interfaces.rest.resources;

import pe.upc.pawfectcaremicroservices.petmanagement.domain.model.valueobjects.PetGender;

import java.time.LocalDate;

public record CreatePetResource(
        String petName,
        LocalDate birthDate,
        LocalDate registrationDate,
        String animalBreed,
        PetGender petGender,
        Long ownerId
) {

}
