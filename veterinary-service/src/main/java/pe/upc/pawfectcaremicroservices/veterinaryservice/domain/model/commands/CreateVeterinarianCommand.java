package pe.upc.pawfectcaremicroservices.veterinaryservice.domain.model.commands;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record CreateVeterinarianCommand(
        Long userId,
        String fullName,
        String phoneNumber,
        String email,
        String dni,
        String veterinarianSpeciality,

        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime availableStartTime,

        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime availableEndTime
) {}
