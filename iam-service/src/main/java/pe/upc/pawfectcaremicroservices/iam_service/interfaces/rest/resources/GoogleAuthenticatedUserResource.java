package pe.upc.pawfectcaremicroservices.iam_service.interfaces.rest.resources;

public record GoogleAuthenticatedUserResource(Long id, String username, String token) {
}
