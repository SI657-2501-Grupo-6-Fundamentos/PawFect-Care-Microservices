package pe.upc.pawfectcaremicroservices.profile_service.domain.services;

import pe.upc.pawfectcaremicroservices.profile_service.domain.model.aggregates.Profile;
import pe.upc.pawfectcaremicroservices.profile_service.domain.model.commands.CreateProfileCommand;

import java.util.Optional;

public interface ProfileCommandService {
    Optional<Profile> handle(CreateProfileCommand command);
}