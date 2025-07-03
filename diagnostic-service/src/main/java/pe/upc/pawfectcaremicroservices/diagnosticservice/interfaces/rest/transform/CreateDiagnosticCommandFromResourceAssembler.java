package pe.upc.pawfectcaremicroservices.diagnosticservice.interfaces.rest.transform;

import pe.upc.pawfectcaremicroservices.diagnosticservice.domain.model.commands.CreateDiagnosticCommand;
import pe.upc.pawfectcaremicroservices.diagnosticservice.interfaces.rest.resources.CreateDiagnosticResource;

public class CreateDiagnosticCommandFromResourceAssembler {
    public static CreateDiagnosticCommand toCommandFromResource(CreateDiagnosticResource resource) {
        return new CreateDiagnosticCommand(
                resource.diagnosticDate(),
                resource.description(),
                resource.diagnosticSpecialty());
    }
}
