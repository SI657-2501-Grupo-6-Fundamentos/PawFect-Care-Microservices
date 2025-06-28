package pe.upc.pawfectcaremicroservices.diagnosticservice.interfaces.rest.transform;

import pe.upc.pawfectcaremicroservices.diagnosticservice.domain.model.aggregates.Diagnostic;
import pe.upc.pawfectcaremicroservices.diagnosticservice.interfaces.rest.resources.DiagnosticResource;

public class DiagnosticResourceFromEntityAssembler {
    public static DiagnosticResource toResourceFromEntity(Diagnostic entity) {
        return new DiagnosticResource(
                entity.getId(),
                entity.getDiagnosticDate(),
                entity.getDescription(),
                entity.getDiagnosticSpecialty()
        );
    }
}
