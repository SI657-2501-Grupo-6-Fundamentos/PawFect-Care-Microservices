package pe.upc.pawfectcaremicroservices.veterinaryservice.domain.services;

import pe.upc.pawfectcaremicroservices.veterinaryservice.domain.model.aggregates.Veterinarian;
import pe.upc.pawfectcaremicroservices.veterinaryservice.domain.model.commands.CreateVeterinarianCommand;
import pe.upc.pawfectcaremicroservices.veterinaryservice.domain.model.commands.UpdateVeterinarianAvailabilityCommand;
import pe.upc.pawfectcaremicroservices.veterinaryservice.domain.model.commands.UpdateVeterinarianCommand;

import java.util.Optional;

public interface VeterinarianCommandService {
    Optional<Veterinarian> handle(UpdateVeterinarianCommand command);
    Long handle(CreateVeterinarianCommand command);
    Optional<Veterinarian> handle(UpdateVeterinarianAvailabilityCommand command);
}
