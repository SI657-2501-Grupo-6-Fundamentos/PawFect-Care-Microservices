package pe.upc.pawfectcaremicroservices.medicalappointment.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.aggregates.Appointment;

import java.util.List;

@Repository
public interface AppointmentRepository  extends JpaRepository<Appointment, Long>{
    boolean existsById(Long id);
    List<Appointment> findAllByVeterinarianId(Long veterinarianId);
    List<Appointment> findAllByPetId(Long petId);
}