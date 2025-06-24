package pe.upc.pawfectcaremicroservices.veterinaryscheduleservice.interfaces.rest.resources;

import pe.upc.pawfectcaremicroservices.veterinaryscheduleservice.domain.model.valueobjects.AvailableDays;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record CreateScheduleResource(
        AvailableDays availableDays,
        LocalDateTime startDateTime,
        LocalDateTime endDateTime,
        Long veterinarianId
) {

}
