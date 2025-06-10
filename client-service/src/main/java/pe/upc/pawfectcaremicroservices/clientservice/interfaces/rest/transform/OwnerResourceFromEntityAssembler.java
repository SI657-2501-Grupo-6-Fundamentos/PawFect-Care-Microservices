package pe.upc.pawfectcaremicroservices.clientservice.interfaces.rest.transform;


import pe.upc.pawfectcaremicroservices.clientservice.domain.model.aggregates.Owner;
import pe.upc.pawfectcaremicroservices.clientservice.interfaces.rest.resources.OwnerResource;

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
