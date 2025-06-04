package pe.upc.pawfectcaremicroservices.petmanagement.interfaces.rest.resources;

public record CreateVeterinarianResource(
        String fullName,
        String phoneNumber,
        String email,
        String dni,
        String speciality) {
}
