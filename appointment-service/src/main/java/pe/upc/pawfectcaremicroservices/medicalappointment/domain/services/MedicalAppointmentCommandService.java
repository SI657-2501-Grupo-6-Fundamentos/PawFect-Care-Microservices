package pe.upc.pawfectcaremicroservices.medicalappointment.domain.services;

import pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.aggregates.MedicalAppointment;
import pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.commands.CreateMedicalAppointmentCommand;
import pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.commands.UpdateMedicalAppointmentCommand;

import java.util.Optional;

public interface MedicalAppointmentCommandService {
    Long handle(CreateMedicalAppointmentCommand command);
    Optional<MedicalAppointment> handle(UpdateMedicalAppointmentCommand command);
}
