package pe.upc.pawfectcaremicroservices.medicalrecordservice.interfaces.rest.resources;

public record CreateMedicalRecordResource(
        String title,
        String notes,
        Long diagnosticId,
        Long medicalAppointmentId
) { }
