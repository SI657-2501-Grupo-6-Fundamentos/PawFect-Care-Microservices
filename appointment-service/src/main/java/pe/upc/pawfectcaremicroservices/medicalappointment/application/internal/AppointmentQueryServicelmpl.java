package pe.upc.pawfectcaremicroservices.medicalappointment.application.internal;

import org.springframework.stereotype.Service;
import pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.aggregates.Appointment;
import pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.queries.GetAllAppointmentsByPetIdQuery;
import pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.queries.GetAllAppointmentsByVeterinarianIdQuery;
import pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.queries.GetAllAppointmentsQuery;
import pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.queries.GetAppointmentByIdQuery;
import pe.upc.pawfectcaremicroservices.medicalappointment.domain.services.AppointmentQueryService;
import pe.upc.pawfectcaremicroservices.medicalappointment.infrastructure.persistence.jpa.repositories.AppointmentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentQueryServicelmpl implements AppointmentQueryService{
    private final AppointmentRepository appointmentRepository;
    public AppointmentQueryServicelmpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public Optional<Appointment> handle(GetAppointmentByIdQuery query) {
        return appointmentRepository.findById(query.appointmentId());
    }

    @Override
    public List<Appointment> handle(GetAllAppointmentsQuery query) {
        return appointmentRepository.findAll();
    }

    public List<Appointment> handle(GetAllAppointmentsByVeterinarianIdQuery query) {
        return appointmentRepository.findAllByVeterinarianId(query.veterinarianId());
    }

    public List<Appointment> handle(GetAllAppointmentsByPetIdQuery query) {
        return appointmentRepository.findAllByPetId(query.petId());
    }
}
