package pe.upc.pawfectcaremicroservices.petownerservice.interfaces.rest.transform;


import pe.upc.pawfectcaremicroservices.petownerservice.domain.model.aggregates.PetOwner;
import pe.upc.pawfectcaremicroservices.petownerservice.interfaces.rest.resources.PetOwnerResource;

public class PetOwnerResourceFromEntityAssembler {
    public static PetOwnerResource toResourceFromEntity(PetOwner entity) {
        return new PetOwnerResource(
                entity.getId(),
                entity.getUserId(),
                entity.getFullName(),
                entity.getPhoneNumber(),
                entity.getEmail(),
                entity.getAddress());
    }
}
