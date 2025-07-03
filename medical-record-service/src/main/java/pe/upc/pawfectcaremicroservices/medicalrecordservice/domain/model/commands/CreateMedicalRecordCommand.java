package pe.upc.pawfectcaremicroservices.medicalrecordservice.domain.model.commands;

import java.time.LocalDateTime;

public record CreateMedicalRecordCommand(String title,
                                         String notes,
                                         Long diagnosticId,
                                         Long medicalAppointmentId,
                                         LocalDateTime recordedAt) {
}
