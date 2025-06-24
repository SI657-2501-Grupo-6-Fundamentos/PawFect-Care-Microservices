package pe.upc.pawfectcaremicroservices.medicalappointment.interfaces.rest.transform;

import pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.aggregates.Tariff;
import pe.upc.pawfectcaremicroservices.medicalappointment.interfaces.rest.resources.TariffResource;

public class TariffResourceFromEntityAssembler {

    public static TariffResource toResourceFromEntity(Tariff entity) {
        return new TariffResource(
                entity.getId(),
                entity.getServiceName(),
                entity.getCost(),
                entity.getMinimumCost(),
                entity.getMaximumCost()
        );
    }
}
