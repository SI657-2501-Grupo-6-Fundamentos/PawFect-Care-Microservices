package pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.commands;

import pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.valueobjects.AppointmentStatus;

import java.time.LocalDateTime;

public record UpdateAppointmentCommand(
        Long appointmentId,
        String appointmentName,
        LocalDateTime registrationDate,
        LocalDateTime endDate,
        AppointmentStatus status
) {
}
