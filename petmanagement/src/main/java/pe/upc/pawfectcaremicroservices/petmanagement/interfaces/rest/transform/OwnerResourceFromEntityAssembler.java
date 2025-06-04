package pe.upc.pawfectcaremicroservices.petmanagement.interfaces.rest.transform;


import pe.upc.pawfectcaremicroservices.petmanagement.domain.model.aggregates.Owner;
import pe.upc.pawfectcaremicroservices.petmanagement.interfaces.rest.resources.OwnerResource;

public class OwnerResourceFromEntityAssembler {
    public static OwnerResource toResourceFromEntity(Owner entity) {
        return new OwnerResource(
                entity.getId(),
                entity.getFullName(),
                entity.getPhoneNumber(),
                entity.getEmail(),
                entity.getAddress());
    }
}
