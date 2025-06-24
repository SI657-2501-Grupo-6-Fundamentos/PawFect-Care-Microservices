package pe.upc.pawfectcaremicroservices.treatmentservice.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.upc.pawfectcaremicroservices.treatmentservice.domain.model.aggregates.Diagnostic;
import pe.upc.pawfectcaremicroservices.treatmentservice.domain.model.valueobjects.DiagnosticType;

import java.util.List;

@Repository
public interface DiagnosticRepository extends JpaRepository<Diagnostic, Long> {
    boolean existsById(Long id);
    List<Diagnostic> findAllByDiagnosticType(DiagnosticType diagnosticType);
}
