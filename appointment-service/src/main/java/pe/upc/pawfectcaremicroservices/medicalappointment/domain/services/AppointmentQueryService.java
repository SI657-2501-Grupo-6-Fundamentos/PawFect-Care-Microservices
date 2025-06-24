package pe.upc.pawfectcaremicroservices.medicalappointment.domain.services;

import pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.aggregates.Appointment;
import pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.queries.GetAllAppointmentsByPetIdQuery;
import pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.queries.GetAllAppointmentsByVeterinarianIdQuery;
import pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.queries.GetAllAppointmentsQuery;
import pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.queries.GetAppointmentByIdQuery;

import java.util.List;
import java.util.Optional;

public interface AppointmentQueryService {
    Optional<Appointment> handle(GetAppointmentByIdQuery query);
    List<Appointment> handle(GetAllAppointmentsQuery query);
    List<Appointment> handle(GetAllAppointmentsByPetIdQuery query);
    List<Appointment> handle(GetAllAppointmentsByVeterinarianIdQuery query);
}
