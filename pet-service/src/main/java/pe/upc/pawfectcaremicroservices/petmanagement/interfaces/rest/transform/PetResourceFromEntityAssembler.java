package pe.upc.pawfectcaremicroservices.petmanagement.interfaces.rest.transform;

import pe.upc.pawfectcaremicroservices.petmanagement.domain.model.aggregates.Pet;
import pe.upc.pawfectcaremicroservices.petmanagement.interfaces.rest.resources.PetResource;

public class PetResourceFromEntityAssembler {
    public static PetResource toResourceFromEntity(Pet entity) {
        return new PetResource(
                entity.getId(),
                entity.getPetName(),
                entity.getBirthDate(),
                entity.getRegistrationDate(),
                entity.getAnimalBreed(),
                entity.getPetGender(),
                entity.getMedicalHistoryId(),
                entity.getOwner().getId());
    }
}
