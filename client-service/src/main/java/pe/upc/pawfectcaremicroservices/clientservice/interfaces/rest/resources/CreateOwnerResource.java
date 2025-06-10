package pe.upc.pawfectcaremicroservices.clientservice.interfaces.rest.resources;

public record CreateOwnerResource(
        String fullName,
        String phoneNumber,
        String email,
        String address) {
}
