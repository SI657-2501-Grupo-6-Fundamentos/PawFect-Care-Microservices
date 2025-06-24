package pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.queries;

public record GetAllAppointmentsByVeterinarianIdQuery(Long veterinarianId) {

    public GetAllAppointmentsByVeterinarianIdQuery {
        if (veterinarianId == null || veterinarianId <= 0) {
            throw new IllegalArgumentException("Veterinarian ID must be a positive number.");
        }
    }

    public Long veterinarianId() {
        return veterinarianId;
    }
}
