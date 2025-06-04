package pe.upc.pawfectcaremicroservices.petmanagement.domain.services;

import pe.upc.pawfectcaremicroservices.petmanagement.domain.model.aggregates.Pet;
import pe.upc.pawfectcaremicroservices.petmanagement.domain.model.queries.GetAllPetsByOwnerIdQuery;
import pe.upc.pawfectcaremicroservices.petmanagement.domain.model.queries.GetAllPetsQuery;
import pe.upc.pawfectcaremicroservices.petmanagement.domain.model.queries.GetPetByIdQuery;

import java.util.List;
import java.util.Optional;

public interface PetQueryService {
   Optional<Pet> handle(GetPetByIdQuery query);
   List<Pet> handle(GetAllPetsQuery query);
   List<Pet> handle(GetAllPetsByOwnerIdQuery query);
}
