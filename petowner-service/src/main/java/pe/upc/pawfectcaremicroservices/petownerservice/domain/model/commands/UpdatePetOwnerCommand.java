package pe.upc.pawfectcaremicroservices.petownerservice.domain.model.commands;

public record UpdatePetOwnerCommand(Long id,
                                    String fullName,
                                    String phoneNumber,
                                    String email,
                                    String address) {
}
