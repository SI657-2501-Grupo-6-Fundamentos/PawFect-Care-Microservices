package pe.upc.pawfectcaremicroservices.veterinaryservice.interfaces.rest.resources;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record CreateVeterinarianResource(
        Long userId,
        String fullName,
        String phoneNumber,
        String email,
        String dni,
        String speciality,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime availableStartTime,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime availableEndTime) {
}
