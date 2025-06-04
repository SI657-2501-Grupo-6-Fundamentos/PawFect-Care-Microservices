package pe.upc.pawfectcaremicroservices.petmanagement.interfaces.rest.transform;

import pe.upc.pawfectcaremicroservices.petmanagement.domain.model.commands.CreateVeterinarianCommand;
import pe.upc.pawfectcaremicroservices.petmanagement.interfaces.rest.resources.CreateVeterinarianResource;

public class CreateVeterinarianCommandFromResourceAssembler {
    public static CreateVeterinarianCommand toCommandFromResource(CreateVeterinarianResource resource) {
        return new CreateVeterinarianCommand(
                resource.fullName(),
                resource.phoneNumber(),
                resource.email(),
                resource.dni(),
                resource.speciality());
    }
}
