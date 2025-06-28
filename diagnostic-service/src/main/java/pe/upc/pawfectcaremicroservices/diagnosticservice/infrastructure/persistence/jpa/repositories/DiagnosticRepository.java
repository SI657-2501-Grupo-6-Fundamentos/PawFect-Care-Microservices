package pe.upc.pawfectcaremicroservices.diagnosticservice.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.upc.pawfectcaremicroservices.diagnosticservice.domain.model.aggregates.Diagnostic;
import pe.upc.pawfectcaremicroservices.diagnosticservice.domain.model.valueobjects.DiagnosticSpecialty;

import java.util.List;

@Repository
public interface DiagnosticRepository extends JpaRepository<Diagnostic, Long> {
    boolean existsById(Long id);
    List<Diagnostic> findAllByDiagnosticSpecialty(DiagnosticSpecialty diagnosticSpecialty);
}
