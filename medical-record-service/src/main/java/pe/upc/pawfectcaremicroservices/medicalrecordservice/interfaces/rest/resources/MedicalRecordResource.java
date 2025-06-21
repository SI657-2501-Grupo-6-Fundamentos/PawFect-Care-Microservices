package pe.upc.pawfectcaremicroservices.medicalrecordservice.interfaces.rest.resources;

public record MedicalRecordResource(
        Long id,
        String title,
        String notes,
        Long diagnosticId,
        Long medicalAppointmentId) {
}
