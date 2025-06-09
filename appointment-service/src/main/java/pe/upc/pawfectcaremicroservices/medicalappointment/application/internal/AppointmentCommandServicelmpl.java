package pe.upc.pawfectcaremicroservices.medicalappointment.application.internal;

import org.springframework.stereotype.Service;
import pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.aggregates.Appointment;
import pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.commands.CreateAppointmentCommand;
import pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.commands.UpdateAppointmentCommand;
import pe.upc.pawfectcaremicroservices.medicalappointment.domain.services.AppointmentCommandService;
import pe.upc.pawfectcaremicroservices.medicalappointment.infrastructure.persistence.jpa.repositories.AppointmentRepository;
import pe.upc.pawfectcaremicroservices.medicalappointment.infrastructure.persistence.jpa.repositories.MedicalAppointmentRepository;

import java.util.Optional;

@Service
public class AppointmentCommandServicelmpl implements AppointmentCommandService {

    private final AppointmentRepository appointmentRepository;
    /*private final ExternalPetService externalPetService;*/

    public AppointmentCommandServicelmpl(
            AppointmentRepository appointmentRepository,
            /*ExternalPetService externalPetService,*/
            MedicalAppointmentRepository medicalAppointmentRepository

    ) {
        this.appointmentRepository = appointmentRepository;
        /*this.externalPetService = externalPetService;*/
    }

    @Override
    public Long handle(CreateAppointmentCommand command) {

        /*Pet pet = externalPetService.fetchPetById(command.petId()).orElseThrow(() -> new PetNotFoundException(command.petId()));*/
        Appointment appointment = new Appointment(command);
        /*appointment.setPet(pet);*/
        try {
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
            var updatedAppointment = appointmentRepository.save(appointmentToUpdate.updateInformation(command.appointmentName(), command.registrationDate(), command.endDate(), command.isMedical(), command.status()));
            return Optional.of(updatedAppointment);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating appointment: " + e.getMessage());
        }
    }
}
