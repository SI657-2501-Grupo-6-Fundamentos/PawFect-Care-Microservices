package pe.upc.pawfectcaremicroservices.clientservice.interfaces.rest.resources;

public record UpdateOwnerResource(
        String fullName,
        String phoneNumber,
        String email,
        String address
) {
}
