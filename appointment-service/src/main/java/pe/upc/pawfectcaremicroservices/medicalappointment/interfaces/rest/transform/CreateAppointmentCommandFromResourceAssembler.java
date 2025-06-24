package pe.upc.pawfectcaremicroservices.medicalappointment.interfaces.rest.transform;

import pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.commands.CreateAppointmentCommand;
import pe.upc.pawfectcaremicroservices.medicalappointment.interfaces.rest.resources.CreateAppointmentResource;

public class CreateAppointmentCommandFromResourceAssembler {
    public static CreateAppointmentCommand toCommandFromResource(CreateAppointmentResource resource) {
        return new CreateAppointmentCommand(
                resource.appointmentName(),
                resource.registrationDate(),
                resource.endDate(),
                resource.status(),
                resource.tariffId(),
                resource.petId(),
                resource.veterinarianId()
        );
    }
}
