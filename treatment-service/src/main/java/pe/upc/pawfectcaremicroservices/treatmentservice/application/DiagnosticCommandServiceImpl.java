package pe.upc.pawfectcaremicroservices.treatmentservice.application;

import org.springframework.stereotype.Service;
import pe.upc.pawfectcaremicroservices.treatmentservice.domain.model.aggregates.Diagnostic;
import pe.upc.pawfectcaremicroservices.treatmentservice.domain.model.commands.CreateDiagnosticCommand;
import pe.upc.pawfectcaremicroservices.treatmentservice.domain.model.commands.UpdateDiagnosticCommand;
import pe.upc.pawfectcaremicroservices.treatmentservice.domain.model.valueobjects.DiagnosticType;
import pe.upc.pawfectcaremicroservices.treatmentservice.domain.services.DiagnosticCommandService;
import pe.upc.pawfectcaremicroservices.treatmentservice.infrastructure.persistence.jpa.repositories.DiagnosticRepository;

import java.util.Optional;

@Service
public class DiagnosticCommandServiceImpl implements DiagnosticCommandService {
    private final DiagnosticRepository diagnosticRepository;

    public DiagnosticCommandServiceImpl(DiagnosticRepository diagnosticRepository) {
        this.diagnosticRepository = diagnosticRepository;
    }

    @Override
    public Long handle(CreateDiagnosticCommand command) {
        var diagnostic = new Diagnostic(command);
        try {
            diagnosticRepository.save(diagnostic);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving diagnostic: " + e.getMessage());
        }
        return diagnostic.getId();
    }

    @Override
    public Optional<Diagnostic> handle(UpdateDiagnosticCommand command) {
        if (!diagnosticRepository.existsById(command.diagnosticId())) throw new IllegalArgumentException("diagnosticId does not exist");
        var result = diagnosticRepository.findById(command.diagnosticId());
        var diagnosticToUpdate = result.get();
        try {
            var updatedDiagnostic = diagnosticRepository.save(diagnosticToUpdate
                    .updateInformation(
                            command.diagnosticDate(),
                            command.description(),
                            command.diagnosticType() != null ?
                                    DiagnosticType.valueOf(String.valueOf(command.diagnosticType())) :
                                    diagnosticToUpdate.getDiagnosticType()
                    )
            );
            return Optional.of(updatedDiagnostic);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating diagnostic: " + e.getMessage());
        }
    }
}
