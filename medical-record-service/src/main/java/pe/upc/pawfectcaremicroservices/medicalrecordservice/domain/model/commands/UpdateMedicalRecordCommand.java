package pe.upc.pawfectcaremicroservices.medicalrecordservice.domain.model.commands;

import java.time.LocalDate;

public record UpdateMedicalRecordCommand(Long id,
                                         String title,
                                         String notes) {
}