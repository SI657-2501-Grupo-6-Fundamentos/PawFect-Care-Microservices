package pe.upc.pawfectcaremicroservices.treatmentservice.interfaces.rest.resources;

import pe.upc.pawfectcaremicroservices.treatmentservice.domain.model.valueobjects.DiagnosticType;

import java.time.LocalDateTime;

public record UpdateDiagnosticResource(
        LocalDateTime diagnosticDate,
        String description,
        DiagnosticType diagnosticType
) {
}
