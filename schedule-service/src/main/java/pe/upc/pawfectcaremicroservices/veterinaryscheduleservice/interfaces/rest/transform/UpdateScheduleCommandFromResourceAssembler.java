package pe.upc.pawfectcaremicroservices.veterinaryscheduleservice.interfaces.rest.transform;

import pe.upc.pawfectcaremicroservices.veterinaryscheduleservice.domain.model.commands.UpdateScheduleCommand;
import pe.upc.pawfectcaremicroservices.veterinaryscheduleservice.interfaces.rest.resources.UpdateScheduleResource;

public class UpdateScheduleCommandFromResourceAssembler {
    public static UpdateScheduleCommand toCommandFromResource(Long scheduleId, UpdateScheduleResource resource) {
        return new UpdateScheduleCommand(
                scheduleId,
                resource.availableDays(),
                resource.startDateTime(),
                resource.endDateTime());
    }
}
