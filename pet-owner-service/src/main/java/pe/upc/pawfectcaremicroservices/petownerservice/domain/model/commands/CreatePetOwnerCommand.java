package pe.upc.pawfectcaremicroservices.petownerservice.domain.model.commands;

public record CreatePetOwnerCommand(
        Long userId,
        String fullName,
        String phoneNumber,
        String email,
        String address
) {

}
