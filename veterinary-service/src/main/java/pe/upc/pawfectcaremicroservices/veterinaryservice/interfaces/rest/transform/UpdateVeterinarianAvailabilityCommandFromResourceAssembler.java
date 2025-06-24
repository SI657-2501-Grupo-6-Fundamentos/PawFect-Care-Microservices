package pe.upc.pawfectcaremicroservices.veterinaryservice.interfaces.rest.transform;

import pe.upc.pawfectcaremicroservices.veterinaryservice.domain.model.commands.UpdateVeterinarianAvailabilityCommand;
import pe.upc.pawfectcaremicroservices.veterinaryservice.interfaces.rest.resources.UpdateAvailabilityVeterinarianResource;

public class UpdateVeterinarianAvailabilityCommandFromResourceAssembler {
    public static UpdateVeterinarianAvailabilityCommand toCommandFromResource(Long id, UpdateAvailabilityVeterinarianResource resource) {
        return new UpdateVeterinarianAvailabilityCommand(
                id,
                resource.availableStartTime(),
                resource.availableEndTime()
        );
    }
}
