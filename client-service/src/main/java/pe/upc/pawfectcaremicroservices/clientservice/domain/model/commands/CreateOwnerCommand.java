package pe.upc.pawfectcaremicroservices.clientservice.domain.model.commands;

public record CreateOwnerCommand(
        String fullName,
        String phoneNumber,
        String email,
        String address
) {

}
