package pe.upc.pawfectcaremicroservices.medicalappointment.interfaces.rest.transform;

import pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.commands.UpdateAppointmentCommand;
import pe.upc.pawfectcaremicroservices.medicalappointment.interfaces.rest.resources.UpdateAppointmentResource;

public class UpdateAppointmentCommandFromResourceAssembler {
    public static UpdateAppointmentCommand toCommandFromResource(
            Long appointmentId,
            UpdateAppointmentResource resource) {
        return new UpdateAppointmentCommand(
                appointmentId,
                resource.appointmentName(),
                resource.registrationDate(),
                resource.endDate(),
                resource.status()
        );
    }
}
