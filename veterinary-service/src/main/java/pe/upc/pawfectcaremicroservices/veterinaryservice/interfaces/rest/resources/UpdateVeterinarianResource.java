package pe.upc.pawfectcaremicroservices.veterinaryservice.interfaces.rest.resources;

public record UpdateVeterinarianResource(
        String fullName,
        String phoneNumber,
        String email,
        String dni,
        String speciality) {
}
