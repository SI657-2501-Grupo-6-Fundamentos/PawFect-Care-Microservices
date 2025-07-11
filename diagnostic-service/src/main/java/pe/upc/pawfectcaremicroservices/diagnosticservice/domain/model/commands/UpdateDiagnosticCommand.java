package pe.upc.pawfectcaremicroservices.diagnosticservice.domain.model.commands;

import pe.upc.pawfectcaremicroservices.diagnosticservice.domain.model.valueobjects.DiagnosticSpecialty;

import java.time.LocalDateTime;

public record UpdateDiagnosticCommand(
        Long diagnosticId,
        LocalDateTime diagnosticDate,
        String description,
        DiagnosticSpecialty diagnosticSpecialty
) {
}
