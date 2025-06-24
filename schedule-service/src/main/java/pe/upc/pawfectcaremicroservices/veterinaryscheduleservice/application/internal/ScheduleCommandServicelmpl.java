package pe.upc.pawfectcaremicroservices.veterinaryscheduleservice.application.internal;

import org.springframework.stereotype.Service;
import pe.upc.pawfectcaremicroservices.veterinaryscheduleservice.application.external.veterinarians.ExternalVeterinarian;
import pe.upc.pawfectcaremicroservices.veterinaryscheduleservice.domain.model.aggregates.Schedule;
import pe.upc.pawfectcaremicroservices.veterinaryscheduleservice.domain.model.commands.CreateScheduleCommand;
import pe.upc.pawfectcaremicroservices.veterinaryscheduleservice.domain.model.commands.DeleteScheduleCommand;
import pe.upc.pawfectcaremicroservices.veterinaryscheduleservice.domain.model.commands.UpdateScheduleCommand;
import pe.upc.pawfectcaremicroservices.veterinaryscheduleservice.domain.services.ScheduleCommandService;
import pe.upc.pawfectcaremicroservices.veterinaryscheduleservice.infrastructure.persistence.jpa.repositories.ScheduleRepository;

import java.util.Optional;

@Service
public class ScheduleCommandServicelmpl implements ScheduleCommandService {
    private final ScheduleRepository scheduleRepository;
    private final ExternalVeterinarian externalVeterinarian;

    public ScheduleCommandServicelmpl(ScheduleRepository scheduleRepository, ExternalVeterinarian externalVeterinarian) {
        this.scheduleRepository = scheduleRepository;
        this.externalVeterinarian = externalVeterinarian;
    }

    /*
    @Override
    public Long handle(CreateScheduleCommand command) {
        var schedule = new Schedule(command);
        try {
            if (!externalVeterinarian.existsVeterinarianById(command.veterinarianId()))
                throw new IllegalArgumentException("veterinarianId does not exist");

            // Aquí podrías setear solo el veterinarianId, no el objeto Veterinarian
            schedule.setVeterinarianId(command.veterinarianId());
            scheduleRepository.save(schedule);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving schedule: " + e.getMessage());
        }
        return schedule.getId();
    }*/

    @Override
    public Long handle(CreateScheduleCommand command) {
        try {
            if (!externalVeterinarian.existsVeterinarianById(command.veterinarianId()))
                throw new IllegalArgumentException("veterinarianId does not exist");

            var schedule = new Schedule(command);
            schedule.setVeterinarianId(command.veterinarianId());

            // Guardar el schedule primero
            scheduleRepository.save(schedule);

            // Luego actualizar la disponibilidad del veterinario
            externalVeterinarian.updateVeterinarianAvailability(command.veterinarianId(), schedule);

            return schedule.getId();
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving schedule: " + e.getMessage());
        }
    }

    @Override
    public Optional<Schedule> handle(UpdateScheduleCommand command) {
        if (!scheduleRepository.existsById(command.id())) throw new IllegalArgumentException("scheduleId does not exist");
        var result = scheduleRepository.findById(command.id());
        if (result.isEmpty()) throw new IllegalArgumentException("Schedule does not exist");
        var scheduleToUpdate = result.get();
        try {
            var updatedSchedule = scheduleRepository.save(scheduleToUpdate.updateInformation(command.availableDays(),
                    command.startDateTime(), command.endDateTime()));
            return Optional.of(updatedSchedule);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating schedule: " + e.getMessage());
        }
    }


    @Override
    public void handle(DeleteScheduleCommand command) {
        if (!scheduleRepository.existsById(command.scheduleId())) {
            throw new IllegalArgumentException("Pe does not exist");
        }
        try {
            scheduleRepository.deleteById(command.scheduleId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting schedule: " + e.getMessage());
        }

    }
}
