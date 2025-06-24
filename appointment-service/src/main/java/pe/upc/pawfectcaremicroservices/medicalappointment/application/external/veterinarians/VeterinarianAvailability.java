package pe.upc.pawfectcaremicroservices.medicalappointment.application.external.veterinarians;

import java.time.LocalDateTime;

public record VeterinarianAvailability(
        LocalDateTime availableStartTime,
        LocalDateTime availableEndTime
) {
}
