package pe.upc.pawfectcaremicroservices.medicalappointment.domain.services;

import pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.aggregates.Tariff;
import pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface TariffQueryService {
    Optional<Tariff> handle(GetTariffByIdQuery query);
    List<Tariff> handle(GetAllTariffsQuery query);
    List<Tariff> handle(GetTariffByServiceNameQuery query);
    List<Tariff> handle(GetTariffByCostBetweenQuery query);
}
