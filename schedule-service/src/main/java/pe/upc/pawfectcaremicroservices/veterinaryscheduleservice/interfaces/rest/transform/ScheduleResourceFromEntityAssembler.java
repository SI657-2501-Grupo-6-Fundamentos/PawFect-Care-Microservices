package pe.upc.pawfectcaremicroservices.veterinaryscheduleservice.interfaces.rest.transform;


import pe.upc.pawfectcaremicroservices.veterinaryscheduleservice.domain.model.aggregates.Schedule;
import pe.upc.pawfectcaremicroservices.veterinaryscheduleservice.interfaces.rest.resources.ScheduleResource;

public class ScheduleResourceFromEntityAssembler {
    public static ScheduleResource toResourceFromEntity(Schedule entity) {
        return new ScheduleResource(
                entity.getId(),
                entity.getAvailableDays(),
                entity.getStartDateTime(),
                entity.getEndDateTime(),
                entity.getVeterinarianId());
    }
}
