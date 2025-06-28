package pe.upc.pawfectcaremicroservices.diagnosticservice.domain.services;

import pe.upc.pawfectcaremicroservices.diagnosticservice.domain.model.aggregates.Diagnostic;
import pe.upc.pawfectcaremicroservices.diagnosticservice.domain.model.commands.CreateDiagnosticCommand;
import pe.upc.pawfectcaremicroservices.diagnosticservice.domain.model.commands.UpdateDiagnosticCommand;

import java.util.Optional;

public interface DiagnosticCommandService {
    Optional<Diagnostic> handle(UpdateDiagnosticCommand command);
    Long handle(CreateDiagnosticCommand command);
}
