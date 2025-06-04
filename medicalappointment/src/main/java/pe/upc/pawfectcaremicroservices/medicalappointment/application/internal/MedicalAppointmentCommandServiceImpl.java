package pe.upc.pawfectcaremicroservices.medicalappointment.application.internal;

import org.springframework.stereotype.Service;
import pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.aggregates.MedicalAppointment;
import pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.commands.CreateMedicalAppointmentCommand;
import pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.commands.UpdateMedicalAppointmentCommand;
import pe.upc.pawfectcaremicroservices.medicalappointment.domain.services.MedicalAppointmentCommandService;
import pe.upc.pawfectcaremicroservices.medicalappointment.infrastructure.persistence.jpa.repositories.AppointmentRepository;
import pe.upc.pawfectcaremicroservices.medicalappointment.infrastructure.persistence.jpa.repositories.MedicalAppointmentRepository;

import java.util.Optional;

@Service
public class MedicalAppointmentCommandServiceImpl implements MedicalAppointmentCommandService {
    private final MedicalAppointmentRepository medicalAppointmentRepository;
    private final AppointmentRepository appointmentRepository;
    /*private final ExternalMedicalHistoryService externalMedicalHistoryService;*/

    public MedicalAppointmentCommandServiceImpl(
            MedicalAppointmentRepository medicalAppointmentRepository,
            AppointmentRepository appointmentRepository/*,
            ExternalMedicalHistoryService externalMedicalHistoryService*/
    ) {
        this.appointmentRepository = appointmentRepository;
        this.medicalAppointmentRepository = medicalAppointmentRepository;
        /*this.externalMedicalHistoryService = externalMedicalHistoryService;*/
    }

    @Override
    public Long handle(CreateMedicalAppointmentCommand command) {

        var appointment = appointmentRepository.findById(command.appointmentId());
        if (appointment.isEmpty()) throw new IllegalArgumentException("appointmentId does not exist");

        MedicalAppointment medicalAppointment = new MedicalAppointment(
                command.diagnosis(),
                command.treatment(),
                command.notes()
        );
        /*medicalAppointment.setMedicalHistory(appointment.get().getPet().getMedicalHistory());*/
        medicalAppointment.setAppointment(appointment.get());

        try {
            medicalAppointmentRepository.save(medicalAppointment);
            System.out.println("This is a New Line."+medicalAppointment.getAppointment().getPetId()/*.getMedicalHistory().getId()*/);
            /*externalMedicalHistoryService.AddMedicalAppointmentToMedicalHistory(medicalAppointment.getAppointment().getPetId()/*.getMedicalHistory().getId(),medicalAppointment.getId());*/
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving medicalAppointment: " + e.getMessage());
        }

        return medicalAppointment.getId();
    }

    @Override
    public Optional<MedicalAppointment> handle(UpdateMedicalAppointmentCommand command) {

        var result = medicalAppointmentRepository.findById(command.medicalAppointmentId());
        if (result.isEmpty()) throw new IllegalArgumentException("MedicalAppointment does not exist");
        var medicalAppointmentToUpdate = result.get();
        try {
            var updatedMedicalAppointment = medicalAppointmentRepository.save(medicalAppointmentToUpdate.updateInformation(command.diagnosis(),command.notes(),command.treatment()));
            return Optional.of(updatedMedicalAppointment);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating MedicalAppointment: " + e.getMessage());
        }
    }


}
