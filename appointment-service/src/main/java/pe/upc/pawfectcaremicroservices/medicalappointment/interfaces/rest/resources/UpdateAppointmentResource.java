package pe.upc.pawfectcaremicroservices.medicalappointment.interfaces.rest.resources;

import pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.valueobjects.AppointmentStatus;

import java.time.LocalDateTime;


public record UpdateAppointmentResource(
        String appointmentName,
        LocalDateTime registrationDate,
        LocalDateTime endDate,
        AppointmentStatus status
) {
}
