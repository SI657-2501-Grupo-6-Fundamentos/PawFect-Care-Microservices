package pe.upc.pawfectcaremicroservices.medicalappointment.interfaces.rest.resources;

import pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.valueobjects.AppointmentStatus;

import java.time.LocalDateTime;

public record CreateAppointmentResource(String appointmentName,
                                        LocalDateTime registrationDate,
                                        LocalDateTime endDate,
                                        AppointmentStatus status,
                                        float estimatedCost,
                                        Long petId,
                                        Long veterinarianId) {
}
