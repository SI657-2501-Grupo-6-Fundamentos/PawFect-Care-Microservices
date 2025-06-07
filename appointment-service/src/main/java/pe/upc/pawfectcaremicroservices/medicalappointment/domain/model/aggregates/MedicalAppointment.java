package pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "medical_appointments")
public class MedicalAppointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;

    private Long medicalHistoryId;

    private String diagnosis;
    private String treatment;
    private String notes;



    public MedicalAppointment updateInformation(String diagnosis, String notes, String treatment) {
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.notes = notes;
        return this;
    }

    public MedicalAppointment(String diagnosis, String treatment, String notes) {
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.notes = notes;
    }
    public MedicalAppointment() {
    }


}
