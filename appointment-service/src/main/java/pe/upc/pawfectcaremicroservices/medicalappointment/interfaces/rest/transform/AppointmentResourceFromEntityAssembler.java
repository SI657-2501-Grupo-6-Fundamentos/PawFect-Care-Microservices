package pe.upc.pawfectcaremicroservices.medicalappointment.interfaces.rest.transform;


import pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.aggregates.Appointment;
import pe.upc.pawfectcaremicroservices.medicalappointment.interfaces.rest.resources.AppointmentResource;

public class AppointmentResourceFromEntityAssembler {

    public static AppointmentResource toResourceFromEntity(Appointment entity) {
        return new AppointmentResource(
                entity.getId(),
                entity.getAppointmentName(),
                entity.getRegistrationDate(),
                entity.getEndDate(),
                entity.getStatus(),
                entity.getTariff() != null ? entity.getTariff().getId() : null,
                entity.getTariff() != null ? entity.getTariff().getServiceName().name() : null,
                entity.getTariff() != null ? entity.getTariff().getCost() : null,
                entity.getPetId(),
                entity.getVeterinarianId()
        );
    }
}
