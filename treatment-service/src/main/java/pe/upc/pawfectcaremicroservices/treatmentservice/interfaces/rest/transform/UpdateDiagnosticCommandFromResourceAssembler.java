package pe.upc.pawfectcaremicroservices.treatmentservice.interfaces.rest.transform;

import pe.upc.pawfectcaremicroservices.treatmentservice.domain.model.commands.UpdateDiagnosticCommand;
import pe.upc.pawfectcaremicroservices.treatmentservice.interfaces.rest.resources.UpdateDiagnosticResource;

public class UpdateDiagnosticCommandFromResourceAssembler {
    public static UpdateDiagnosticCommand toCommandFromResource(Long diagnosticId, UpdateDiagnosticResource resource) {
        return new UpdateDiagnosticCommand(
                diagnosticId,
                resource.diagnosticDate(),
                resource.description(),
                resource.diagnosticType());
    }
}
