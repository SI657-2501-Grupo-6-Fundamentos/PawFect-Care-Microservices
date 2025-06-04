package pe.upc.pawfectcaremicroservices.medicalappointment.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.aggregates.MedicalAppointment;

import java.util.List;

@Repository
public interface MedicalAppointmentRepository extends JpaRepository<MedicalAppointment, Long> {
    List<MedicalAppointment> findAllByMedicalHistoryId(Long medicalHistoryId);
}
