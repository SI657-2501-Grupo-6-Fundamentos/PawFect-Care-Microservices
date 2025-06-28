package pe.upc.pawfectcaremicroservices.diagnosticservice.domain.model.commands;

import pe.upc.pawfectcaremicroservices.diagnosticservice.domain.model.valueobjects.DiagnosticSpecialty;

import java.time.LocalDateTime;

public record CreateDiagnosticCommand(
        LocalDateTime diagnosticDate,
        String description,
        DiagnosticSpecialty diagnosticSpecialty
) {
}
