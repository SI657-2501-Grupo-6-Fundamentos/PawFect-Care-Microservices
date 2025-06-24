package pe.upc.pawfectcaremicroservices.petownerservice.interfaces.rest.resources;

public record PetOwnerResource(
        Long id,
        Long userId,
        String fullName,
        String phoneNumber,
        String email,
        String address) {
}
