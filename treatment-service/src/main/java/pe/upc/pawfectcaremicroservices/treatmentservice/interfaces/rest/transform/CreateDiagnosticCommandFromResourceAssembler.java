package pe.upc.pawfectcaremicroservices.treatmentservice.interfaces.rest.transform;

import pe.upc.pawfectcaremicroservices.treatmentservice.domain.model.commands.CreateDiagnosticCommand;
import pe.upc.pawfectcaremicroservices.treatmentservice.interfaces.rest.resources.CreateDiagnosticResource;

public class CreateDiagnosticCommandFromResourceAssembler {
    public static CreateDiagnosticCommand toCommandFromResource(CreateDiagnosticResource resource) {
        return new CreateDiagnosticCommand(
                resource.diagnosticDate(),
                resource.description(),
                resource.diagnosticType());
    }
}
