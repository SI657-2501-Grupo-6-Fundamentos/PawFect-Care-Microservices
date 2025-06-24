package pe.upc.pawfectcaremicroservices.medicalappointment.application.internal;

import org.springframework.stereotype.Service;
import pe.upc.pawfectcaremicroservices.medicalappointment.application.external.pets.ExternalPet;
import pe.upc.pawfectcaremicroservices.medicalappointment.application.external.veterinarians.ExternalVeterinarian;
import pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.aggregates.Appointment;
import pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.aggregates.Tariff;
import pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.commands.CreateAppointmentCommand;
import pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.commands.UpdateAppointmentCommand;
import pe.upc.pawfectcaremicroservices.medicalappointment.domain.services.AppointmentCommandService;
import pe.upc.pawfectcaremicroservices.medicalappointment.infrastructure.persistence.jpa.repositories.AppointmentRepository;
import pe.upc.pawfectcaremicroservices.medicalappointment.infrastructure.persistence.jpa.repositories.TariffRepository;

import java.util.Optional;

@Service
public class AppointmentCommandServicelmpl implements AppointmentCommandService {

    private final AppointmentRepository appointmentRepository;
    private final TariffRepository tariffRepository;
    private final ExternalPet externalPet;
    private final ExternalVeterinarian externalVeterinarian;

    public AppointmentCommandServicelmpl(
            AppointmentRepository appointmentRepository,
            TariffRepository tariffRepository,
            ExternalPet externalPet,
            ExternalVeterinarian externalVeterinarian

    ) {
        this.appointmentRepository = appointmentRepository;
        this.tariffRepository = tariffRepository;
        this.externalPet = externalPet;
        this.externalVeterinarian = externalVeterinarian;
    }

    @Override
    public Long handle(CreateAppointmentCommand command) {
        Tariff tariff = tariffRepository.findById(command.tariffId())
                .orElseThrow(() -> new IllegalArgumentException("Tariff not found"));
        Appointment appointment = new Appointment(command, tariff);
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
                            command.status()
                    )
            );
            return Optional.of(updatedAppointment);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating appointment: " + e.getMessage());
        }
    }
}
