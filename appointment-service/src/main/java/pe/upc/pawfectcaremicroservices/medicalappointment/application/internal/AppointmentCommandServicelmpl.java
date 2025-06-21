package pe.upc.pawfectcaremicroservices.medicalappointment.application.internal;

import org.springframework.stereotype.Service;
import pe.upc.pawfectcaremicroservices.medicalappointment.application.external.pets.ExternalPet;
import pe.upc.pawfectcaremicroservices.medicalappointment.application.external.veterinarians.ExternalVeterinarian;
import pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.aggregates.Appointment;
import pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.commands.CreateAppointmentCommand;
import pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.commands.UpdateAppointmentCommand;
import pe.upc.pawfectcaremicroservices.medicalappointment.domain.services.AppointmentCommandService;
import pe.upc.pawfectcaremicroservices.medicalappointment.infrastructure.persistence.jpa.repositories.AppointmentRepository;

import java.util.Optional;

@Service
public class AppointmentCommandServicelmpl implements AppointmentCommandService {

    private final AppointmentRepository appointmentRepository;
    private final ExternalPet externalPet;
    private final ExternalVeterinarian externalVeterinarian;

    public AppointmentCommandServicelmpl(
            AppointmentRepository appointmentRepository,
            ExternalPet externalPet,
            ExternalVeterinarian externalVeterinarian

    ) {
        this.appointmentRepository = appointmentRepository;
        this.externalPet = externalPet;
        this.externalVeterinarian = externalVeterinarian;
    }

    @Override
    public Long handle(CreateAppointmentCommand command) {
        Appointment appointment = new Appointment(command);
        try {
            if (!externalPet.existsPetById(command.petId()))
                throw new IllegalArgumentException("petId does not exist");
            appointment.setPetId(command.petId());
            if (!externalVeterinarian.existsVeterinarianById(command.veterinarianId()))
                throw new IllegalArgumentException("veterinarianId does not exist");
            appointment.setVeterinarianId(command.veterinarianId());
            appointmentRepository.save(appointment);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving appointment: " + e.getMessage());
        }

        return appointment.getId();
    }

    @Override
    public Optional<Appointment> handle(UpdateAppointmentCommand command) {
        if (!appointmentRepository.existsById(command.appointmentId()))
            throw new IllegalArgumentException("appointmentId does not exist");
        var result = appointmentRepository.findById(command.appointmentId());
        var appointmentToUpdate = result.get();
        try {
            var updatedAppointment = appointmentRepository.save(
                    appointmentToUpdate.updateInformation(
                            command.appointmentName(),
                            command.registrationDate(),
                            command.endDate(),
                            command.status(),
                            command.estimatedCost()
                    )
            );
            return Optional.of(updatedAppointment);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating appointment: " + e.getMessage());
        }
    }
}
