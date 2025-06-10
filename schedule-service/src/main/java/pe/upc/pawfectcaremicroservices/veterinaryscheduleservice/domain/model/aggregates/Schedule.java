package pe.upc.pawfectcaremicroservices.veterinaryscheduleservice.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pe.upc.pawfectcaremicroservices.veterinaryscheduleservice.domain.model.commands.CreateScheduleCommand;
import pe.upc.pawfectcaremicroservices.veterinaryscheduleservice.domain.model.valueobjects.AvailableDays;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "schedules")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Enumerated(EnumType.STRING)
    private AvailableDays availableDays;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private Long veterinarianId;

    public Schedule() {
        this.availableDays = AvailableDays.MONDAY;
        this.startDateTime = LocalDateTime.now();
        this.endDateTime = LocalDateTime.now().plusHours(1);
    }

    public Schedule updateInformation(AvailableDays availableDays, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.availableDays = availableDays;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        return this;
    }

    public Schedule(CreateScheduleCommand command) {
        this.availableDays = command.availableDays();
        this.startDateTime = command.startDateTime();
        this.endDateTime = command.endDateTime();
        this.veterinarianId = command.veterinarianId();
    }


}