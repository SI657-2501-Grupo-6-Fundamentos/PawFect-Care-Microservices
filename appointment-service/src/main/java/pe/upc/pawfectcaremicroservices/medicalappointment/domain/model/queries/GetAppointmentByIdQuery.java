package pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.queries;

public record GetAppointmentByIdQuery(Long appointmentId) {
    public GetAppointmentByIdQuery {
        if (appointmentId == null || appointmentId <= 0) {
            throw new IllegalArgumentException("Appointment ID must be a positive number.");
        }
    }
}
