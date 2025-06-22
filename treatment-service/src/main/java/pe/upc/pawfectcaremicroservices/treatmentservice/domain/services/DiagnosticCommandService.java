package pe.upc.pawfectcaremicroservices.treatmentservice.domain.services;

import pe.upc.pawfectcaremicroservices.treatmentservice.domain.model.aggregates.Diagnostic;
import pe.upc.pawfectcaremicroservices.treatmentservice.domain.model.commands.CreateDiagnosticCommand;
import pe.upc.pawfectcaremicroservices.treatmentservice.domain.model.commands.UpdateDiagnosticCommand;

import java.util.Optional;

public interface DiagnosticCommandService {
    Optional<Diagnostic> handle(UpdateDiagnosticCommand command);
    Long handle(CreateDiagnosticCommand command);
}
