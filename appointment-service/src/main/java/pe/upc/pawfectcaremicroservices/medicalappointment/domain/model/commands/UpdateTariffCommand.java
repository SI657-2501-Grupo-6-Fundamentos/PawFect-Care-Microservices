package pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.commands;

import pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.valueobjects.ServiceName;

public record UpdateTariffCommand(
        Long serviceCostId,
        ServiceName serviceName,
        Float cost,
        Float minimumCost,
        Float maximumCost
) {
}
