package pe.upc.pawfectcaremicroservices.medicalappointment.application.internal;

import org.springframework.stereotype.Service;
import pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.aggregates.Tariff;
import pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.queries.*;
import pe.upc.pawfectcaremicroservices.medicalappointment.domain.services.TariffQueryService;
import pe.upc.pawfectcaremicroservices.medicalappointment.infrastructure.persistence.jpa.repositories.TariffRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TariffQueryServiceImpl implements TariffQueryService {
    private final TariffRepository tariffRepository;
    public TariffQueryServiceImpl(TariffRepository tariffRepository) {
        this.tariffRepository = tariffRepository;
    }

    @Override
    public Optional<Tariff> handle(GetTariffByIdQuery query) {
        return tariffRepository.findById(query.serviceCostId());
    }

    @Override
    public List<Tariff> handle(GetAllTariffsQuery query) {
        return tariffRepository.findAll();
    }

    @Override
    public List<Tariff> handle(GetTariffByCostBetweenQuery query) {
        return tariffRepository.findAllByCostBetween(query.minimumCost(), query.maximumCost());
    }

    @Override
    public List<Tariff> handle(GetTariffByServiceNameQuery query) {
        return tariffRepository.findByServiceName(query.serviceName());
    }
}
