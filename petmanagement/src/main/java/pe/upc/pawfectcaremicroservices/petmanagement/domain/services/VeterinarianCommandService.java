package pe.upc.pawfectcaremicroservices.petmanagement.domain.services;

import pe.upc.pawfectcaremicroservices.petmanagement.domain.model.aggregates.Veterinarian;
import pe.upc.pawfectcaremicroservices.petmanagement.domain.model.commands.CreateVeterinarianCommand;
import pe.upc.pawfectcaremicroservices.petmanagement.domain.model.commands.UpdateVeterinarianCommand;

import java.util.Optional;

public interface VeterinarianCommandService {
    Optional<Veterinarian> handle(UpdateVeterinarianCommand command);
    Long handle(CreateVeterinarianCommand command);
}
