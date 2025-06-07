package pe.upc.pawfectcaremicroservices.petmanagement.interfaces.rest.resources;

public record CreateOwnerResource(
        String fullName,
        String phoneNumber,
        String email,
        String address) {
}
