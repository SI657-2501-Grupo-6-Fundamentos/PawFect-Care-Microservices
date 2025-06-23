package pe.upc.pawfectcaremicroservices.veterinaryservice.interfaces.rest.resources;

public record CreateVeterinarianResource(
        Long userId,
        String fullName,
        String phoneNumber,
        String email,
        String dni,
        String speciality) {
}
