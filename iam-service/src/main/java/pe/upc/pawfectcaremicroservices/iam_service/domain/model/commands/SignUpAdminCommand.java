package pe.upc.pawfectcaremicroservices.iam_service.domain.model.commands;

import java.time.LocalDateTime;

public record SignUpAdminCommand(String userName, String password, String role,
                                 String fullName, String phoneNumber, String email, String dni, String veterinarianSpeciality,
                                 LocalDateTime availableStartTime, LocalDateTime availableEndTime) {
}
