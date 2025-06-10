package pe.upc.pawfectcaremicroservices.veterinaryservice.interfaces.rest.transform;

import pe.upc.pawfectcaremicroservices.veterinaryservice.domain.model.commands.UpdateVeterinarianCommand;
import pe.upc.pawfectcaremicroservices.veterinaryservice.interfaces.rest.resources.UpdateVeterinarianResource;

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
