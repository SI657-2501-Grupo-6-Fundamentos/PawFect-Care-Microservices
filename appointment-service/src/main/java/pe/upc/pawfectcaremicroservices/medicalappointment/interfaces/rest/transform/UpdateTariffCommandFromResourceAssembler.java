package pe.upc.pawfectcaremicroservices.medicalappointment.interfaces.rest.transform;

import pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.commands.UpdateTariffCommand;
import pe.upc.pawfectcaremicroservices.medicalappointment.interfaces.rest.resources.UpdateTariffResource;

public class UpdateTariffCommandFromResourceAssembler {
    public static UpdateTariffCommand toCommandFromResource(
            Long tariffId,
            UpdateTariffResource resource) {
        return new UpdateTariffCommand(
                tariffId,
                resource.serviceName(),
                resource.cost(),
                resource.minimumCost(),
                resource.maximumCost()
        );
    }
}
