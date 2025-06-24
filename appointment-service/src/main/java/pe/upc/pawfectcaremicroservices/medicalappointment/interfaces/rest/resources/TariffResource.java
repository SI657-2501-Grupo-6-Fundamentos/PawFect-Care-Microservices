package pe.upc.pawfectcaremicroservices.medicalappointment.interfaces.rest.resources;

import pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.valueobjects.ServiceName;

public record TariffResource(
        Long id,
        ServiceName serviceName,
        Float cost,
        Float minimumCost,
        Float maximumCost
) {
}
