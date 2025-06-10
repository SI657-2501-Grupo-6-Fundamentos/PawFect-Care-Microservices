package pe.upc.pawfectcaremicroservices.veterinaryscheduleservice.interfaces.rest.transform;

import pe.upc.pawfectcaremicroservices.veterinaryscheduleservice.domain.model.commands.CreateScheduleCommand;
import pe.upc.pawfectcaremicroservices.veterinaryscheduleservice.interfaces.rest.resources.CreateScheduleResource;

public class CreateScheduleCommandFromResourceAssembler {
    public static CreateScheduleCommand toCommandFromResource(CreateScheduleResource resource) {
        return new CreateScheduleCommand(
                resource.availableDays(),
                resource.startDateTime(),
                resource.endDateTime(),
                resource.veterinarianId());
    }
}
