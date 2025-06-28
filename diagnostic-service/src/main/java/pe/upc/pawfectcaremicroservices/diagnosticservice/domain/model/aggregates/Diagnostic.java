package pe.upc.pawfectcaremicroservices.diagnosticservice.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pe.upc.pawfectcaremicroservices.diagnosticservice.domain.model.commands.CreateDiagnosticCommand;
import pe.upc.pawfectcaremicroservices.diagnosticservice.domain.model.valueobjects.DiagnosticSpecialty;

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
    private DiagnosticSpecialty diagnosticSpecialty;

    public Diagnostic() {
        this.diagnosticDate = LocalDateTime.now();
        this.description = "";
        this.diagnosticSpecialty = DiagnosticSpecialty.GENERAL_MEDICINE;
    }

    public Diagnostic(CreateDiagnosticCommand command) {
        this();
        this.diagnosticDate = command.diagnosticDate();
        this.description = command.description();
        this.diagnosticSpecialty = DiagnosticSpecialty.GENERAL_MEDICINE;
    }

    public Diagnostic updateInformation(LocalDateTime diagnosticDate, String description, DiagnosticSpecialty diagnosticSpecialty) {
        this.diagnosticDate = diagnosticDate;
        this.description = description;
        this.diagnosticSpecialty = diagnosticSpecialty;
        return this;
    }
}
