package pe.upc.pawfectcaremicroservices.veterinaryservice.interfaces.rest.resources;

import java.time.LocalDateTime;

public record VeterinarianAvailabilityResource(
        LocalDateTime availableStartTime,
        LocalDateTime availableEndTime
) {
}
