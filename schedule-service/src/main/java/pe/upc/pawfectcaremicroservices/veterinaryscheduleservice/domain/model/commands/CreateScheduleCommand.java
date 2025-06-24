package pe.upc.pawfectcaremicroservices.veterinaryscheduleservice.domain.model.commands;


import pe.upc.pawfectcaremicroservices.veterinaryscheduleservice.domain.model.valueobjects.AvailableDays;

import java.time.LocalDateTime;

public record CreateScheduleCommand(AvailableDays availableDays,
                                    LocalDateTime startDateTime,
                                    LocalDateTime endDateTime,
                                    Long veterinarianId) {
}
