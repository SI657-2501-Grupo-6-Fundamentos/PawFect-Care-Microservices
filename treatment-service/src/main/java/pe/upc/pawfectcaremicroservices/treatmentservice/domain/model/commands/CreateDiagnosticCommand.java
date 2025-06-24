package pe.upc.pawfectcaremicroservices.treatmentservice.domain.model.commands;

import pe.upc.pawfectcaremicroservices.treatmentservice.domain.model.valueobjects.DiagnosticType;

import java.time.LocalDateTime;

public record CreateDiagnosticCommand(
        LocalDateTime diagnosticDate,
        String description,
        DiagnosticType diagnosticType
) {
}
