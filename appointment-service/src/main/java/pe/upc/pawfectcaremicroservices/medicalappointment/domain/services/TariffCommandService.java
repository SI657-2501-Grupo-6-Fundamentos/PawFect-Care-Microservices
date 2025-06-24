package pe.upc.pawfectcaremicroservices.medicalappointment.domain.services;

import pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.aggregates.Tariff;
import pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.commands.CreateTariffCommand;
import pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.commands.UpdateTariffCommand;

import java.util.Optional;

public interface TariffCommandService {
    Optional<Tariff> handle(UpdateTariffCommand command);
    Long handle(CreateTariffCommand command);
}
