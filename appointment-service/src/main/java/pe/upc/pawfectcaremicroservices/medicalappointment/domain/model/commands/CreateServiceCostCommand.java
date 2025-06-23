package pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.commands;

public record CreateServiceCostCommand(
        String serviceName,
        Float cost,
        Float minimumCost,
        Float maximumCost
) { }
