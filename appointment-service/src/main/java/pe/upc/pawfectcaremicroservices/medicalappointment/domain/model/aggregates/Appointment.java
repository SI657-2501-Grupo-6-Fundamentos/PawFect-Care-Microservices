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

    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    @ManyToOne
    @JoinColumn(name = "tariff_id")
    private Tariff tariff;

    private Long petId;

    private Long veterinarianId;


    public Appointment() {
        this.appointmentName = Strings.EMPTY;
        this.registrationDate = LocalDateTime.now();
        this.endDate = LocalDateTime.now();
        this.status = AppointmentStatus.SCHEDULED;
        this.tariff = new Tariff();
    }

    public Appointment(CreateAppointmentCommand command, Tariff tariff) {
        this();
        this.appointmentName = command.appointmentName();
        this.registrationDate = command.registrationDate();
        this.endDate = command.endDate();
        this.status = command.status() != null
                ? command.status()
                : AppointmentStatus.SCHEDULED;
        this.tariff = tariff;
    }

    public Appointment updateInformation(
            String appointmentName,
            LocalDateTime registrationDate,
            LocalDateTime endDate,
            AppointmentStatus status
    ) {
        this.appointmentName = appointmentName;
        this.registrationDate = registrationDate;
        this.endDate = endDate;
        this.status = status;
        return this;
    }
}
