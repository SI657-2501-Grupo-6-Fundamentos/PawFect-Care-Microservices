package pe.upc.pawfectcaremicroservices.petownerservice.domain.services;

import pe.upc.pawfectcaremicroservices.petownerservice.domain.model.aggregates.PetOwner;
import pe.upc.pawfectcaremicroservices.petownerservice.domain.model.commands.CreatePetOwnerCommand;
import pe.upc.pawfectcaremicroservices.petownerservice.domain.model.commands.UpdatePetOwnerCommand;

import java.util.Optional;

public interface PetOwnerCommandService {
    Optional<PetOwner> handle(UpdatePetOwnerCommand command);
    Long handle(CreatePetOwnerCommand command);

}
