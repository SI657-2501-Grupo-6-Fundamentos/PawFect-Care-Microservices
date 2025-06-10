package pe.upc.pawfectcaremicroservices.veterinaryscheduleservice.domain.model.commands;


import pe.upc.pawfectcaremicroservices.veterinaryscheduleservice.domain.model.valueobjects.AvailableDays;
import java.time.LocalDateTime;

public record UpdateScheduleCommand(Long id,
                                    AvailableDays availableDays,
                                    LocalDateTime startDateTime,
                                    LocalDateTime endDateTime) {
}