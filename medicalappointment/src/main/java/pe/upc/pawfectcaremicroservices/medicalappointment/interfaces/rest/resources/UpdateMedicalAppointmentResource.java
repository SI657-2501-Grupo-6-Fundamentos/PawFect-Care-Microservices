package pe.upc.pawfectcaremicroservices.medicalappointment.interfaces.rest.resources;

public record UpdateMedicalAppointmentResource(
        String diagnosis,
        String notes,
        String treatment
) {
}
