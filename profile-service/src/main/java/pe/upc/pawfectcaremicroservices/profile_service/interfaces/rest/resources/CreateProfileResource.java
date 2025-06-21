package pe.upc.pawfectcaremicroservices.profile_service.interfaces.rest.resources;

import pe.upc.pawfectcaremicroservices.profile_service.domain.model.valueobjects.ProfileType;

public record CreateProfileResource(Long iamUserId, ProfileType type, Object profileData) {}