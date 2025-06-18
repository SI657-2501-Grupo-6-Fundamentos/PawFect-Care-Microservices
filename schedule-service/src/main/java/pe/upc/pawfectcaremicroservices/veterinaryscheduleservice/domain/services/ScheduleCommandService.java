package pe.upc.pawfectcaremicroservices.veterinaryscheduleservice.domain.services;

import pe.upc.pawfectcaremicroservices.veterinaryscheduleservice.domain.model.aggregates.Schedule;
import pe.upc.pawfectcaremicroservices.veterinaryscheduleservice.domain.model.commands.CreateScheduleCommand;
import pe.upc.pawfectcaremicroservices.veterinaryscheduleservice.domain.model.commands.DeleteScheduleCommand;
import pe.upc.pawfectcaremicroservices.veterinaryscheduleservice.domain.model.commands.UpdateScheduleCommand;

import java.util.Optional;

public interface ScheduleCommandService {
    Optional<Schedule> handle(UpdateScheduleCommand command);
    Long handle(CreateScheduleCommand command);
    void handle(DeleteScheduleCommand command);
}
