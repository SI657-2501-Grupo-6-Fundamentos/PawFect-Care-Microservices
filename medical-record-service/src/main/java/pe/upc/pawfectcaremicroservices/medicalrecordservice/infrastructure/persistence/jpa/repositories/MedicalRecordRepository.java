package pe.upc.pawfectcaremicroservices.medicalrecordservice.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.upc.pawfectcaremicroservices.medicalrecordservice.domain.model.aggregates.MedicalRecord;

import java.util.List;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
      boolean existsById(Long id);
      List<MedicalRecord> findAllByDiagnosticId(Long diagnosticId);
      List<MedicalRecord> findAllByMedicalAppointmentId(Long medicalAppointmentId);
}
