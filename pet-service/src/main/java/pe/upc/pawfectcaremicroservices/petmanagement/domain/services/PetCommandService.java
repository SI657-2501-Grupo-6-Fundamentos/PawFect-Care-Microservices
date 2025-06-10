package pe.upc.pawfectcaremicroservices.petmanagement.domain.services;

import pe.upc.pawfectcaremicroservices.petmanagement.domain.model.aggregates.Pet;
import pe.upc.pawfectcaremicroservices.petmanagement.domain.model.commands.CreatePetCommand;
import pe.upc.pawfectcaremicroservices.petmanagement.domain.model.commands.DeletePetCommand;
import pe.upc.pawfectcaremicroservices.petmanagement.domain.model.commands.UpdatePetCommand;

import java.util.Optional;

public interface PetCommandService {
    Optional<Pet> handle(UpdatePetCommand command);
    Long handle(CreatePetCommand command);
    void handle(DeletePetCommand command);
}
