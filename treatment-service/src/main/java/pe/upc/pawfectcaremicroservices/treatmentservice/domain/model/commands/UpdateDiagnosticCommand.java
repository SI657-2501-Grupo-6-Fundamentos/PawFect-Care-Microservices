package pe.upc.pawfectcaremicroservices.treatmentservice.domain.model.commands;

import pe.upc.pawfectcaremicroservices.treatmentservice.domain.model.valueobjects.DiagnosticType;

import java.time.LocalDateTime;

public record UpdateDiagnosticCommand(
        Long diagnosticId,
        LocalDateTime diagnosticDate,
        String description,
        DiagnosticType diagnosticType
) {
}
