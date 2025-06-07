package pe.upc.pawfectcaremicroservices.petmanagement.interfaces.rest.transform;

import pe.upc.pawfectcaremicroservices.petmanagement.domain.model.aggregates.Veterinarian;
import pe.upc.pawfectcaremicroservices.petmanagement.interfaces.rest.resources.VeterinarianResource;

public class VeterinarianResourceFromEntityAssembler {
    public static VeterinarianResource toResourceFromEntity(Veterinarian entity) {
        return new VeterinarianResource(
                entity.getId(),
                entity.getFullName(),
                entity.getPhoneNumber(),
                entity.getEmail(),
                entity.getDni(),
                entity.getVeterinarianSpeciality());
    }
}
