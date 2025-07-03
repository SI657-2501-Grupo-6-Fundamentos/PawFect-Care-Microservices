package pe.upc.pawfectcaremicroservices.diagnosticservice.domain.model.queries;

import pe.upc.pawfectcaremicroservices.diagnosticservice.domain.model.valueobjects.DiagnosticSpecialty;

public record GetAllDiagnosticByDiagnosticSpecialtyQuery(DiagnosticSpecialty diagnosticSpecialty) {
    public GetAllDiagnosticByDiagnosticSpecialtyQuery {
        if (diagnosticSpecialty == null) {
            throw new IllegalArgumentException("Diagnostic type cannot be null");
        }
    }
}
