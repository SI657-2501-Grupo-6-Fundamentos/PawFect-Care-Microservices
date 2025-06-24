package pe.upc.pawfectcaremicroservices.treatmentservice.domain.services;

import pe.upc.pawfectcaremicroservices.treatmentservice.domain.model.aggregates.Diagnostic;
import pe.upc.pawfectcaremicroservices.treatmentservice.domain.model.queries.GetAllDiagnosticByDiagnosticTypeQuery;
import pe.upc.pawfectcaremicroservices.treatmentservice.domain.model.queries.GetAllDiagnosticQuery;
import pe.upc.pawfectcaremicroservices.treatmentservice.domain.model.queries.GetDiagnosticByIdQuery;

import java.util.List;
import java.util.Optional;

public interface DiagnosticQueryService {
    List<Diagnostic> handle(GetAllDiagnosticQuery query);
    Optional<Diagnostic> handle(GetDiagnosticByIdQuery query);
    List<Diagnostic> handle(GetAllDiagnosticByDiagnosticTypeQuery query);
}
