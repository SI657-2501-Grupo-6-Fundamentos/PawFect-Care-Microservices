package pe.upc.pawfectcaremicroservices.medicalrecordservice.interfaces.rest.transform;

import pe.upc.pawfectcaremicroservices.medicalrecordservice.domain.model.commands.UpdateMedicalRecordCommand;
import pe.upc.pawfectcaremicroservices.medicalrecordservice.interfaces.rest.resources.UpdateMedicalRecordResource;

public class UpdateMedicalRecordCommandFromResourceAssembler {
    public static UpdateMedicalRecordCommand toCommandFromResource(Long petId, UpdateMedicalRecordResource resource) {
        return new UpdateMedicalRecordCommand(
                petId,
                resource.title(),
                resource.notes());
    }
}
