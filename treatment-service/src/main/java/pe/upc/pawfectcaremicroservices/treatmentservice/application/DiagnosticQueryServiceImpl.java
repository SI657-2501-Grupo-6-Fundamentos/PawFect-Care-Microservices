package pe.upc.pawfectcaremicroservices.treatmentservice.application;

import org.springframework.stereotype.Service;
import pe.upc.pawfectcaremicroservices.treatmentservice.domain.model.aggregates.Diagnostic;
import pe.upc.pawfectcaremicroservices.treatmentservice.domain.model.queries.GetAllDiagnosticByDiagnosticTypeQuery;
import pe.upc.pawfectcaremicroservices.treatmentservice.domain.model.queries.GetAllDiagnosticQuery;
import pe.upc.pawfectcaremicroservices.treatmentservice.domain.model.queries.GetDiagnosticByIdQuery;
import pe.upc.pawfectcaremicroservices.treatmentservice.domain.services.DiagnosticQueryService;
import pe.upc.pawfectcaremicroservices.treatmentservice.infrastructure.persistence.jpa.repositories.DiagnosticRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DiagnosticQueryServiceImpl implements DiagnosticQueryService {
    private final DiagnosticRepository diagnosticRepository;
    public DiagnosticQueryServiceImpl(DiagnosticRepository diagnosticRepository) {
        this.diagnosticRepository = diagnosticRepository;
    }

    @Override
    public Optional<Diagnostic> handle(GetDiagnosticByIdQuery query) {
        return diagnosticRepository.findById(query.diagnosticId());
    }

    @Override
    public List<Diagnostic> handle(GetAllDiagnosticQuery query) {
        return diagnosticRepository.findAll();
    }

    @Override
    public List<Diagnostic> handle(GetAllDiagnosticByDiagnosticTypeQuery query) {
        return diagnosticRepository.findAllByDiagnosticType(query.diagnosticType());
    }
}
