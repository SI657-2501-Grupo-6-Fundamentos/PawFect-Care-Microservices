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
                entity.getEstimatedCost(),
                entity.getPetId(),
                entity.getVeterinarianId()
        );
    }
}
