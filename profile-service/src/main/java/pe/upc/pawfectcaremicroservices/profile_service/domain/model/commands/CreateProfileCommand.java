package pe.upc.pawfectcaremicroservices.profile_service.domain.model.commands;

import pe.upc.pawfectcaremicroservices.profile_service.domain.model.valueobjects.ProfileType;

public record CreateProfileCommand(Long iamUserId, ProfileType type, Object profileData) {}