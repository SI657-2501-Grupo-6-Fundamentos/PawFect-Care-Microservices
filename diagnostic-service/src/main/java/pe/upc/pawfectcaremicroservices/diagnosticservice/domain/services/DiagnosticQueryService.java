package pe.upc.pawfectcaremicroservices.diagnosticservice.domain.services;

import pe.upc.pawfectcaremicroservices.diagnosticservice.domain.model.aggregates.Diagnostic;
import pe.upc.pawfectcaremicroservices.diagnosticservice.domain.model.queries.GetAllDiagnosticByDiagnosticSpecialtyQuery;
import pe.upc.pawfectcaremicroservices.diagnosticservice.domain.model.queries.GetAllDiagnosticQuery;
import pe.upc.pawfectcaremicroservices.diagnosticservice.domain.model.queries.GetDiagnosticByIdQuery;

import java.util.List;
import java.util.Optional;

public interface DiagnosticQueryService {
    List<Diagnostic> handle(GetAllDiagnosticQuery query);
    Optional<Diagnostic> handle(GetDiagnosticByIdQuery query);
    List<Diagnostic> handle(GetAllDiagnosticByDiagnosticSpecialtyQuery query);
}
