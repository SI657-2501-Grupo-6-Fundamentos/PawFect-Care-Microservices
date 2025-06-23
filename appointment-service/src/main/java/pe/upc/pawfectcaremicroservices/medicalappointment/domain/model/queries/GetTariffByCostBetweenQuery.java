package pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.queries;

public record GetTariffByCostBetweenQuery(Float minimumCost, Float maximumCost) {
}
