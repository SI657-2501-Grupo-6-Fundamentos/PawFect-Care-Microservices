package pe.upc.pawfectcaremicroservices.review.interfaces.rest.resources;

public record CreateReviewResource(
        String content,
        Integer rating,
        Long medicalAppointmentId
) {
}
