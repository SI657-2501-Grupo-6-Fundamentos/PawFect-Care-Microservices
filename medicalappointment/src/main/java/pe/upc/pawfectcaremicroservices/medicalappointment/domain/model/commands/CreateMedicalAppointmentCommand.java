package pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.commands;

public record CreateMedicalAppointmentCommand(
         String diagnosis,
         String treatment,
         String notes,
         Long appointmentId,
         Long medicalHistoryId
) {
}
