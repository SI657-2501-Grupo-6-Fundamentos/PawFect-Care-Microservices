package pe.upc.pawfectcaremicroservices.veterinaryservice.interfaces.rest.resources;

public record CreateVeterinarianResource(
        String fullName,
        String phoneNumber,
        String email,
        String dni,
        String speciality) {
}
