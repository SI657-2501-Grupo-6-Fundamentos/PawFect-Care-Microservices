package pe.upc.pawfectcaremicroservices.veterinaryservice.domain.model.queries;

public record GetVeterinariansByIdQuery(Long veterinarianId) {
    // This record is used to encapsulate the query for retrieving a veterinarian by their ID.
    // It contains a single field, veterinarianId, which is the ID of the veterinarian to be retrieved.
}
