package pe.upc.pawfectcaremicroservices.petmanagement.domain.model.commands;

public record CreateOwnerCommand(
        String fullName,
        String phoneNumber,
        String email,
        String address
) {

}
