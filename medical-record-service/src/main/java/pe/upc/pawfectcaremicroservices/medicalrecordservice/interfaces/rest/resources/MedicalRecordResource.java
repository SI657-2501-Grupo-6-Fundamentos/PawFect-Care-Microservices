package pe.upc.pawfectcaremicroservices.medicalrecordservice.interfaces.rest.resources;

import java.time.LocalDateTime;

public record MedicalRecordResource(
        Long id,
        String title,
        String notes,
        Long diagnosticId,
        Long medicalAppointmentId,
        LocalDateTime recordedAt) {
}
