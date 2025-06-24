package pe.upc.pawfectcaremicroservices.veterinaryservice.domain.model.commands;

import java.time.LocalDateTime;

public record UpdateVeterinarianAvailabilityCommand(
        Long id,
        LocalDateTime availableStartTime,
        LocalDateTime availableEndTime
) {
}
