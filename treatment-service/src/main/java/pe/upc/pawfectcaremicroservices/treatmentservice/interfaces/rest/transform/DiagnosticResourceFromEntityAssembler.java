package pe.upc.pawfectcaremicroservices.treatmentservice.interfaces.rest.transform;

import pe.upc.pawfectcaremicroservices.treatmentservice.domain.model.aggregates.Diagnostic;
import pe.upc.pawfectcaremicroservices.treatmentservice.interfaces.rest.resources.DiagnosticResource;

public class DiagnosticResourceFromEntityAssembler {
    public static DiagnosticResource toResourceFromEntity(Diagnostic entity) {
        return new DiagnosticResource(
                entity.getId(),
                entity.getDiagnosticDate(),
                entity.getDescription(),
                entity.getDiagnosticType()
        );
    }
}
