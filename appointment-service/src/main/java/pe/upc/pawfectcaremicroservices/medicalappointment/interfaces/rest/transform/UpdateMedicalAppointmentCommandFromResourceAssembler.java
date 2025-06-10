package pe.upc.pawfectcaremicroservices.medicalappointment.interfaces.rest.transform;


import pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.commands.UpdateMedicalAppointmentCommand;
import pe.upc.pawfectcaremicroservices.medicalappointment.interfaces.rest.resources.UpdateMedicalAppointmentResource;

public class UpdateMedicalAppointmentCommandFromResourceAssembler {
    public static UpdateMedicalAppointmentCommand toCommandFromResource(
            Long medicalAppointmentId,
            UpdateMedicalAppointmentResource resource) {
        return new UpdateMedicalAppointmentCommand(
                medicalAppointmentId,
                resource.diagnosis(),
                resource.notes(),
                resource.treatment());
    }
}
