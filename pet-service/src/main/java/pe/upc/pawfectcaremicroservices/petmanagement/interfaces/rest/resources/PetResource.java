/**
 * PetResource
 * @Summary
 *  PetResource is a record class that represents the resource response for a pet
 **/

package pe.upc.pawfectcaremicroservices.petmanagement.interfaces.rest.resources;

import pe.upc.pawfectcaremicroservices.petmanagement.domain.model.valueobjects.AnimalType;
import pe.upc.pawfectcaremicroservices.petmanagement.domain.model.valueobjects.Breed;
import pe.upc.pawfectcaremicroservices.petmanagement.domain.model.valueobjects.PetGender;

import java.time.LocalDate;

public record PetResource(Long id, String petName, LocalDate birthDate, LocalDate registrationDate,
                          AnimalType animalType, Breed animalBreed, PetGender petGender,
                          Long ownerId, String imageUrl) {
}