package pe.upc.pawfectcaremicroservices.veterinaryservice.domain.services;

import pe.upc.pawfectcaremicroservices.veterinaryservice.domain.model.aggregates.Veterinarian;
import pe.upc.pawfectcaremicroservices.veterinaryservice.domain.model.queries.GetAllVeterinariansBySpecialityQuery;
import pe.upc.pawfectcaremicroservices.veterinaryservice.domain.model.queries.GetAllVeterinariansQuery;
import pe.upc.pawfectcaremicroservices.veterinaryservice.domain.model.queries.GetVeterinariansByIdQuery;

import java.util.List;
import java.util.Optional;

public interface VeterinarianQueryService {
    Optional<Veterinarian> handle(GetVeterinariansByIdQuery query);
    List<Veterinarian> handle(GetAllVeterinariansQuery query);
    List<Veterinarian> handle(GetAllVeterinariansBySpecialityQuery query);
}
