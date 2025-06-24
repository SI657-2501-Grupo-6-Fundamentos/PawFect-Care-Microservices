package pe.upc.pawfectcaremicroservices.veterinaryservice.interfaces.rest.transform;

import pe.upc.pawfectcaremicroservices.veterinaryservice.domain.model.aggregates.Veterinarian;
import pe.upc.pawfectcaremicroservices.veterinaryservice.interfaces.rest.resources.VeterinarianResource;

public class VeterinarianResourceFromEntityAssembler {
    public static VeterinarianResource toResourceFromEntity(Veterinarian entity) {
        return new VeterinarianResource(
                entity.getId(),
                entity.getUserId(),
                entity.getFullName(),
                entity.getPhoneNumber(),
                entity.getEmail(),
                entity.getDni(),
                entity.getVeterinarianSpeciality(),
                entity.getAvailableStartTime(),
                entity.getAvailableEndTime());
    }
}
