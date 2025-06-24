package pe.upc.pawfectcaremicroservices.treatmentservice.domain.model.queries;

import pe.upc.pawfectcaremicroservices.treatmentservice.domain.model.valueobjects.DiagnosticType;

public record GetAllDiagnosticByDiagnosticTypeQuery(DiagnosticType diagnosticType) {
    public GetAllDiagnosticByDiagnosticTypeQuery {
        if (diagnosticType == null) {
            throw new IllegalArgumentException("Diagnostic type cannot be null");
        }
    }
}
