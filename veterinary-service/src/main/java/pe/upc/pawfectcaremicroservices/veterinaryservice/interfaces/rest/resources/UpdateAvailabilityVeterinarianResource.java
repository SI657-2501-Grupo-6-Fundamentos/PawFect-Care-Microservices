package pe.upc.pawfectcaremicroservices.veterinaryservice.interfaces.rest.resources;

import java.time.LocalDateTime;

public record UpdateAvailabilityVeterinarianResource(
        //String availableDays,
        LocalDateTime availableStartTime,
        LocalDateTime availableEndTime
) {}
