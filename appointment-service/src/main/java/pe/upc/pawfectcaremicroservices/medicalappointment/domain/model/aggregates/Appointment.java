package pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;
import pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.commands.CreateAppointmentCommand;
import pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.valueobjects.AppointmentStatus;

import java.time.LocalDateTime;


@Getter
@Setter
@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String appointmentName;
    private LocalDateTime registrationDate;
    private LocalDateTime endDate;
    private boolean isMedical;
    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    private Long petId;

    @Setter
    @OneToOne(mappedBy = "appointment")
    private MedicalAppointment medicalAppointment;



    public Appointment() {
        this.appointmentName = Strings.EMPTY;
        this.registrationDate = LocalDateTime.now();
        this.endDate = LocalDateTime.now();
        this.isMedical = false;
        this.status = AppointmentStatus.SCHEDULED;
    }

    public Appointment(CreateAppointmentCommand command) {
        this();
        this.appointmentName = command.appointmentName();
        this.registrationDate = command.registrationDate();
        this.endDate = command.endDate();
        this.isMedical = command.isMedical();
        this.status = AppointmentStatus.SCHEDULED;
    }

    public Appointment updateInformation(String appointmentName,LocalDateTime registrationDate,LocalDateTime endDate, boolean isMedical,AppointmentStatus status ) {
        this.appointmentName = appointmentName;
        this.registrationDate = registrationDate;
        this.endDate = endDate;
        this.isMedical = isMedical;
        this.status = status;
        return this;
    }

}
