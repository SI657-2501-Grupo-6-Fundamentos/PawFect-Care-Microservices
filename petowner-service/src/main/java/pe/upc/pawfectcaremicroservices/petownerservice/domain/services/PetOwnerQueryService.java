package pe.upc.pawfectcaremicroservices.petownerservice.domain.services;

import pe.upc.pawfectcaremicroservices.petownerservice.domain.model.aggregates.PetOwner;
import pe.upc.pawfectcaremicroservices.petownerservice.domain.model.queries.GetAllPetOwnersQuery;
import pe.upc.pawfectcaremicroservices.petownerservice.domain.model.queries.GetPetOwnerByIdQuery;

import java.util.List;
import java.util.Optional;

public interface PetOwnerQueryService {
  List<PetOwner> handle(GetAllPetOwnersQuery query);
  Optional<PetOwner> handle(GetPetOwnerByIdQuery query);
}
