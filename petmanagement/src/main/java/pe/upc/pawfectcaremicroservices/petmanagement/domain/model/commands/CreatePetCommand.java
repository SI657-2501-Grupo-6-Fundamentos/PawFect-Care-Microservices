/**
 * CreatePetCommand
 * @Summary
 *  CreatePetCommand is a record class that represents the command create
 **/

package pe.upc.pawfectcaremicroservices.petmanagement.domain.model.commands;


import pe.upc.pawfectcaremicroservices.petmanagement.domain.model.valueobjects.PetGender;

import java.time.LocalDate;

public record CreatePetCommand(String petName, LocalDate birthDate,LocalDate registrationDate,
                               String animalBreed, PetGender petGender,Long ownerId) {
}
