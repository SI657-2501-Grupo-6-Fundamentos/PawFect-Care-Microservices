package pe.upc.pawfectcaremicroservices.veterinaryservice.interfaces.rest.resources;

import java.time.LocalDateTime;

public record CreateVeterinarianResource(
        Long userId,
        String fullName,
        String phoneNumber,
        String email,
        String dni,
        String speciality,
        LocalDateTime availableStartTime,
        LocalDateTime availableEndTime) {
}
