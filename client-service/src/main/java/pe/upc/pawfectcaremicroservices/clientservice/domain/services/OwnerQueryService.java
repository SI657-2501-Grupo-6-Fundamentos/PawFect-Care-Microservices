package pe.upc.pawfectcaremicroservices.clientservice.domain.services;

import pe.upc.pawfectcaremicroservices.clientservice.domain.model.aggregates.Owner;
import pe.upc.pawfectcaremicroservices.clientservice.domain.model.queries.GetAllOwnersQuery;
import pe.upc.pawfectcaremicroservices.clientservice.domain.model.queries.GetOwnerByIdQuery;

import java.util.List;
import java.util.Optional;

public interface OwnerQueryService {
  List<Owner> handle(GetAllOwnersQuery query);
  Optional<Owner> handle(GetOwnerByIdQuery query);
}
