package pe.upc.pawfectcaremicroservices.medicalappointment.interfaces.rest.transform;

import pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.commands.CreateTariffCommand;
import pe.upc.pawfectcaremicroservices.medicalappointment.interfaces.rest.resources.CreateTariffResource;

public class CreateTariffCommandFromResourceAssembler {
    public static CreateTariffCommand toCommandFromResource(CreateTariffResource resource) {
        return new CreateTariffCommand(
                resource.serviceName(),
                resource.cost(),
                resource.minimumCost(),
                resource.maximumCost()
        );
    }
}
