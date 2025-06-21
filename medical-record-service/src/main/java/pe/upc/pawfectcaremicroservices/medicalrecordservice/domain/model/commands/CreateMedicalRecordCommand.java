package pe.upc.pawfectcaremicroservices.medicalrecordservice.domain.model.commands;

public record CreateMedicalRecordCommand(String title,
                                         String notes,
                                         Long diagnosticId,
                                         Long medicalAppointmentId) {
}
