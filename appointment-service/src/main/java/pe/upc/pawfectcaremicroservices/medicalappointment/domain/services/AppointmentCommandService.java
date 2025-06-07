package pe.upc.pawfectcaremicroservices.medicalappointment.domain.services;

import pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.aggregates.Appointment;
import pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.commands.CreateAppointmentCommand;
import pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.commands.UpdateAppointmentCommand;

import java.util.Optional;

public interface AppointmentCommandService {
    Optional<Appointment> handle(UpdateAppointmentCommand command);
    Long handle(CreateAppointmentCommand command);
}
