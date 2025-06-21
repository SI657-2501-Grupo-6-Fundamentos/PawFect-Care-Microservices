package pe.upc.pawfectcaremicroservices.diagnosticservice.interfaces.rest.transform;

import pe.upc.pawfectcaremicroservices.diagnosticservice.domain.model.commands.UpdateDiagnosticCommand;
import pe.upc.pawfectcaremicroservices.diagnosticservice.interfaces.rest.resources.UpdateDiagnosticResource;

public class UpdateDiagnosticCommandFromResourceAssembler {
    public static UpdateDiagnosticCommand toCommandFromResource(Long diagnosticId, UpdateDiagnosticResource resource) {
        return new UpdateDiagnosticCommand(
                diagnosticId,
                resource.diagnosticDate(),
                resource.description(),
                resource.diagnosticType());
    }
}
