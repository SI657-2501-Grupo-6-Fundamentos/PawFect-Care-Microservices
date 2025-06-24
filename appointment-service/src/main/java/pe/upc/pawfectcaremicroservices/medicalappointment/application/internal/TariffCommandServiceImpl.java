package pe.upc.pawfectcaremicroservices.medicalappointment.application.internal;

import org.springframework.stereotype.Service;
import pe.upc.pawfectcaremicroservices.medicalappointment.application.external.veterinarians.ExternalVeterinarian;
import pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.aggregates.Tariff;
import pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.commands.CreateTariffCommand;
import pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.commands.UpdateTariffCommand;
import pe.upc.pawfectcaremicroservices.medicalappointment.domain.services.TariffCommandService;
import pe.upc.pawfectcaremicroservices.medicalappointment.infrastructure.persistence.jpa.repositories.TariffRepository;

import java.util.Optional;

@Service
public class TariffCommandServiceImpl implements TariffCommandService {
    private final TariffRepository tariffRepository;

    public TariffCommandServiceImpl(TariffRepository tariffRepository, ExternalVeterinarian externalVeterinarian) {
        this.tariffRepository = tariffRepository;
    }

    @Override
    public Long handle(CreateTariffCommand command) {
        Tariff tariff = new Tariff(command);
        try {
            if (tariffRepository.existsByServiceName(command.serviceName()))
                throw new IllegalArgumentException("Tariff with this service name already exists");
            if (command.cost() < 0 || command.minimumCost() < 0 || command.maximumCost() < 0)
                throw new IllegalArgumentException("Cost values cannot be negative");
            if (command.minimumCost() > command.maximumCost())
                throw new IllegalArgumentException("Minimum cost cannot be greater than maximum cost");
            if (command.cost() < command.minimumCost() || command.cost() > command.maximumCost())
                throw new IllegalArgumentException("Cost must be within the range of minimum and maximum costs");
            tariff.setServiceName(command.serviceName());
            tariffRepository.save(tariff);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving tariff: " + e.getMessage());
        }

        return tariff.getId();
    }

    @Override
    public Optional<Tariff> handle(UpdateTariffCommand command) {
        if (!tariffRepository.existsById(command.serviceCostId()))
            throw new IllegalArgumentException("tariffId does not exist");
        if (command.minimumCost() < 0 || command.maximumCost() < 0)
            throw new IllegalArgumentException("Cost values cannot be negative");
        if (command.minimumCost() > command.maximumCost())
            throw new IllegalArgumentException("Minimum cost cannot be greater than maximum cost");
        if (command.cost() < command.minimumCost() || command.cost() > command.maximumCost())
            throw new IllegalArgumentException("Cost must be within the range of minimum and maximum costs");
        var result = tariffRepository.findById(command.serviceCostId());
        var tariffToUpdate = result.get();
        try {
            var updatedTariff = tariffRepository.save(
                    tariffToUpdate.updateInformation(
                            command.serviceName(),
                            command.cost(),
                            command.minimumCost(),
                            command.maximumCost()
                    )
            );
            return Optional.of(updatedTariff);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating tariff: " + e.getMessage());
        }
    }
}
