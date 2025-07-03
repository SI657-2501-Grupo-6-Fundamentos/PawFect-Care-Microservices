package pe.upc.pawfectcaremicroservices.medicalrecordservice.interfaces.rest.transform;

import pe.upc.pawfectcaremicroservices.medicalrecordservice.domain.model.commands.CreateMedicalRecordCommand;
import pe.upc.pawfectcaremicroservices.medicalrecordservice.interfaces.rest.resources.CreateMedicalRecordResource;

public class CreateMedicalRecordCommandFromResourceAssembler {
    public static CreateMedicalRecordCommand toCommandFromResource(CreateMedicalRecordResource resource) {
        return new CreateMedicalRecordCommand(
                resource.title(),
                resource.notes(),
                resource.diagnosticId(),
                resource.medicalAppointmentId(),
                resource.recordedAt()
        );
    }
}
