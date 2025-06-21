package pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.queries;

public record GetAllAppointmentsByPetIdQuery(Long petId) {

    public GetAllAppointmentsByPetIdQuery {
        if (petId == null || petId <= 0) {
            throw new IllegalArgumentException("Pet ID must be a positive number.");
        }
    }

    public Long petId() {
        return petId;
    }
}
