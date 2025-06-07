package pe.upc.pawfectcaremicroservices.medicalappointment.interfaces.rest.resources;

public record MedicalAppointmentResource (
        Long id,
        String diagnosis,
        String notes,
        String treatment,
        Long appointmentId) {
}
