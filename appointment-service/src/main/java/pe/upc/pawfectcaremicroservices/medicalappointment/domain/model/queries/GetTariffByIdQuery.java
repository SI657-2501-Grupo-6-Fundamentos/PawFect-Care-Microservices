package pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.queries;

public record GetTariffByIdQuery(Long serviceCostId) {
    public GetTariffByIdQuery {
        if (serviceCostId == null || serviceCostId <= 0) {
            throw new IllegalArgumentException("Service cost ID must be a positive number.");
        }
    }
}
