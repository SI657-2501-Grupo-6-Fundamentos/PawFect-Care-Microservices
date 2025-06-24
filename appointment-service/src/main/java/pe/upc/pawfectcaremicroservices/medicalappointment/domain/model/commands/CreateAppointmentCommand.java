package pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.commands;

import pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.valueobjects.AppointmentStatus;
import pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.valueobjects.ServiceName;

import java.time.LocalDateTime;

public record CreateAppointmentCommand(
      String appointmentName,
      LocalDateTime registrationDate,
      LocalDateTime endDate,
      AppointmentStatus status,
      Long tariffId,
      Long petId,
      Long veterinarianId) {
}
