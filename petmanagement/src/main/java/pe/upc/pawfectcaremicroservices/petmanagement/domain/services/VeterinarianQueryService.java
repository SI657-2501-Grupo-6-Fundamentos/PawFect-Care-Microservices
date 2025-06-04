package pe.upc.pawfectcaremicroservices.petmanagement.domain.services;

import pe.upc.pawfectcaremicroservices.petmanagement.domain.model.aggregates.Veterinarian;
import pe.upc.pawfectcaremicroservices.petmanagement.domain.model.queries.GetAllVeterinariansBySpecialityQuery;
import pe.upc.pawfectcaremicroservices.petmanagement.domain.model.queries.GetAllVeterinariansQuery;
import pe.upc.pawfectcaremicroservices.petmanagement.domain.model.queries.GetVeterinariansByIdQuery;

import java.util.List;
import java.util.Optional;

public interface VeterinarianQueryService {
    Optional<Veterinarian> handle(GetVeterinariansByIdQuery query);
    List<Veterinarian> handle(GetAllVeterinariansQuery query);
    List<Veterinarian> handle(GetAllVeterinariansBySpecialityQuery query);
}
