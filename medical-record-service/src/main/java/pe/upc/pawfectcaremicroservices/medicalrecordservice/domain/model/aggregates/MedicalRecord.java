package pe.upc.pawfectcaremicroservices.medicalrecordservice.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;
import pe.upc.pawfectcaremicroservices.medicalrecordservice.domain.model.commands.CreateMedicalRecordCommand;


@Getter
@Setter
@Entity
@Table(name = "medical_records")
public class MedicalRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String notes;
    private Long diagnosticId;

    private Long medicalAppointmentId;

    public MedicalRecord() {
        this.title = Strings.EMPTY;
        this.notes = Strings.EMPTY;
    }

    public MedicalRecord updateInformation(String title, String notes) {
        this.title = title;
        this.notes = notes;
        return this;
    }

    public MedicalRecord(CreateMedicalRecordCommand command) {
        this.title = command.title();
        this.notes = command.notes();
    }
}