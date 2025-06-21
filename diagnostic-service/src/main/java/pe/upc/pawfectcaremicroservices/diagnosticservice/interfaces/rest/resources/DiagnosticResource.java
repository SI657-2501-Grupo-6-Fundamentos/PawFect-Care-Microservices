package pe.upc.pawfectcaremicroservices.diagnosticservice.interfaces.rest.resources;

import pe.upc.pawfectcaremicroservices.diagnosticservice.domain.model.valueobjects.DiagnosticType;

import java.time.LocalDateTime;

public record DiagnosticResource(
        Long id,
        String description,
        LocalDateTime diagnosticDate,
        DiagnosticType diagnosticType
) {
}
