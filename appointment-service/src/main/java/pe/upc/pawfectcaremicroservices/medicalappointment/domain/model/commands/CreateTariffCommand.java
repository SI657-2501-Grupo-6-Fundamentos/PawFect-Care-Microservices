package pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.commands;

import pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.valueobjects.ServiceName;

public record CreateTariffCommand(
        ServiceName serviceName,
        Float cost,
        Float minimumCost,
        Float maximumCost
) { }
