package pe.upc.pawfectcaremicroservices.diagnosticservice.interfaces.rest.resources;

import pe.upc.pawfectcaremicroservices.diagnosticservice.domain.model.valueobjects.DiagnosticSpecialty;

import java.time.LocalDateTime;

public record CreateDiagnosticResource(LocalDateTime diagnosticDate,
                                       String description,
                                       DiagnosticSpecialty diagnosticSpecialty) {
}
