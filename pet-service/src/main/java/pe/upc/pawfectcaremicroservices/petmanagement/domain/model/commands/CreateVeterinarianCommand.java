package pe.upc.pawfectcaremicroservices.petmanagement.domain.model.commands;

public record CreateVeterinarianCommand(
        String fullName,
        String phoneNumber,
        String email,
        String dni,
        String veterinarianSpeciality) {
}
