package pe.upc.pawfectcaremicroservices.review.domain.model.queries;

public record GetAllReviewsByMedicalAppointmentIdQuery(Long medicalAppointmentId) {
    // This record is used to encapsulate the query for retrieving all reviews by a specific appointment ID.
    // It contains a single field, medicalAppointmentId, which is the ID of the appointment whose reviews are being queried.
}
