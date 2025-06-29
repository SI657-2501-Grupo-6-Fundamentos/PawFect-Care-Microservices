package pe.upc.pawfectcaremicroservices.treatmentservice.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pe.upc.pawfectcaremicroservices.treatmentservice.domain.model.commands.CreateDiagnosticCommand;
import pe.upc.pawfectcaremicroservices.treatmentservice.domain.model.valueobjects.DiagnosticType;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "diagnostics")
public class Diagnostic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime diagnosticDate;
    private String description;
    @Enumerated(EnumType.STRING)
    private DiagnosticType diagnosticType;

    public Diagnostic() {
        this.diagnosticDate = LocalDateTime.now();
        this.description = "";
        this.diagnosticType = DiagnosticType.GENERAL_MEDICINE;
    }

    public Diagnostic(CreateDiagnosticCommand command) {
        this();
        this.diagnosticDate = command.diagnosticDate();
        this.description = command.description();
        this.diagnosticType = DiagnosticType.GENERAL_MEDICINE;
    }

    public Diagnostic updateInformation(LocalDateTime diagnosticDate, String description, DiagnosticType diagnosticType) {
        this.diagnosticDate = diagnosticDate;
        this.description = description;
        this.diagnosticType = diagnosticType;
        return this;
    }
}
