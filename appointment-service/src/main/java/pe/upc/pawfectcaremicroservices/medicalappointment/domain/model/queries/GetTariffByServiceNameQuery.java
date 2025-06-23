package pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.queries;

import pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.valueobjects.ServiceName;

public record GetTariffByServiceNameQuery(ServiceName serviceName) {
}
