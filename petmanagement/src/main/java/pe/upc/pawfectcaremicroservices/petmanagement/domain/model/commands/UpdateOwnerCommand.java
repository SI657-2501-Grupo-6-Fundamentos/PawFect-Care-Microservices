package pe.upc.pawfectcaremicroservices.petmanagement.domain.model.commands;

public record UpdateOwnerCommand( Long id,
                                  String fullName,
                                  String phoneNumber,
                                  String email,
                                  String address) {
}
