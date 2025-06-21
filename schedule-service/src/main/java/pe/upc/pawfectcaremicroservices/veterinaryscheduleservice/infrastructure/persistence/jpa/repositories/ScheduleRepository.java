package pe.upc.pawfectcaremicroservices.veterinaryscheduleservice.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.upc.pawfectcaremicroservices.veterinaryscheduleservice.domain.model.aggregates.Schedule;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
      boolean existsById(Long id);
      List<Schedule> findAllByVeterinarianId(Long veterinarianId);
}
