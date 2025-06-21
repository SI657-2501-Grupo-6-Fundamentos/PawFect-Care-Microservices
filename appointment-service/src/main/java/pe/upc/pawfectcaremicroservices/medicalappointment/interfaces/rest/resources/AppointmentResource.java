package pe.upc.pawfectcaremicroservices.medicalappointment.interfaces.rest.resources;

import pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.valueobjects.AppointmentStatus;

import java.time.LocalDateTime;


public record AppointmentResource(
        Long id,
        String appointmentName,
        LocalDateTime registrationDate,
        LocalDateTime endDate,
        AppointmentStatus status,
        Float estimatedCost,
        Long petId,
        Long veterinarianId) {
}
