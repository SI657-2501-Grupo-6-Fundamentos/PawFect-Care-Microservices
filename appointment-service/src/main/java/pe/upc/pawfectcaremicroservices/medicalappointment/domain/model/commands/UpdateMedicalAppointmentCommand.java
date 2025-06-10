package pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.commands;

public record UpdateMedicalAppointmentCommand(
        Long medicalAppointmentId,
        String diagnosis,
        String notes,
        String treatment
) {
}
