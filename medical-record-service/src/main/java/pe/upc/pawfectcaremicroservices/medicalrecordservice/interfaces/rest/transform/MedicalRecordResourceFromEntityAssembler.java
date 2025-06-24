package pe.upc.pawfectcaremicroservices.medicalrecordservice.interfaces.rest.transform;

import pe.upc.pawfectcaremicroservices.medicalrecordservice.domain.model.aggregates.MedicalRecord;
import pe.upc.pawfectcaremicroservices.medicalrecordservice.interfaces.rest.resources.MedicalRecordResource;

public class MedicalRecordResourceFromEntityAssembler {
    public static MedicalRecordResource toResourceFromEntity(MedicalRecord entity) {
        return new MedicalRecordResource(
                entity.getId(),
                entity.getTitle(),
                entity.getNotes(),
                entity.getDiagnosticId(),
                entity.getMedicalAppointmentId(),
                entity.getRecordedAt()
        );
    }
}
