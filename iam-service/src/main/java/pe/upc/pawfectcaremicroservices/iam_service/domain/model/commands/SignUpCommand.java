package pe.upc.pawfectcaremicroservices.iam_service.domain.model.commands;

import pe.upc.pawfectcaremicroservices.iam_service.domain.model.entities.Role;

import java.util.List;

public record SignUpCommand(String userName, String password, String role,
                            String fullName, String phoneNumber, String email, String address) {
}
