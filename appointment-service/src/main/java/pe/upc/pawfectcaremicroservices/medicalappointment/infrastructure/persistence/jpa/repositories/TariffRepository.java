package pe.upc.pawfectcaremicroservices.medicalappointment.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.aggregates.Tariff;
import pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.valueobjects.ServiceName;

import java.util.List;

@Repository
public interface TariffRepository  extends JpaRepository<Tariff, Long> {
    boolean existsById(Long id);
    List<Tariff> findByServiceName(ServiceName serviceName);
    List<Tariff> findAllByCostBetween(Float minimumCost, Float maximumCost);
    boolean existsByServiceName(ServiceName serviceName);
}
