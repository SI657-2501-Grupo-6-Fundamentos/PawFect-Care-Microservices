package pe.upc.pawfectcaremicroservices.petmanagement.interfaces.rest.transform;

import pe.upc.pawfectcaremicroservices.petmanagement.domain.model.commands.UpdateVeterinarianCommand;
import pe.upc.pawfectcaremicroservices.petmanagement.interfaces.rest.resources.UpdateVeterinarianResource;

public class UpdateVeterinarianCommandFromResourceAssembler {
    public static UpdateVeterinarianCommand toCommandFromResource(Long veterinarianId, UpdateVeterinarianResource resource) {
        return new UpdateVeterinarianCommand(
                veterinarianId,
                resource.fullName(),
                resource.phoneNumber(),
                resource.email(),
                resource.dni(),
                resource.speciality());
    }
}
