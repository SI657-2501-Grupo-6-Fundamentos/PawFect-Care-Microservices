package pe.upc.pawfectcaremicroservices.feedback.domain.model.queries;

public record GetAllReviewsByVeterinarianIdQuery(Long veterinarianId) {
    // This record is used to encapsulate the query for retrieving all reviews by a specific veterinarian ID.
    // It contains a single field, veterinarianId, which is the ID of the veterinarian whose reviews are being queried.
}
