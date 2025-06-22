package pe.upc.pawfectcaremicroservices.petownerservice.interfaces.rest.resources;

public record UpdatePetOwnerResource(
        String fullName,
        String phoneNumber,
        String email,
        String address
) {
}
