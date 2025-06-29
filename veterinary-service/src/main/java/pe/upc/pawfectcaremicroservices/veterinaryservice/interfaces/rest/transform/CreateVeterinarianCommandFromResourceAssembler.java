package pe.upc.pawfectcaremicroservices.veterinaryservice.interfaces.rest.transform;

import pe.upc.pawfectcaremicroservices.veterinaryservice.domain.model.commands.CreateVeterinarianCommand;
import pe.upc.pawfectcaremicroservices.veterinaryservice.interfaces.rest.resources.CreateVeterinarianResource;

public class CreateVeterinarianCommandFromResourceAssembler {
    public static CreateVeterinarianCommand toCommandFromResource(CreateVeterinarianResource resource) {
        return new CreateVeterinarianCommand(
                resource.userId(),
                resource.fullName(),
                resource.phoneNumber(),
                resource.email(),
                resource.dni(),
                resource.speciality(),
                resource.availableStartTime(),
                resource.availableEndTime());
    }
}
