package pe.upc.pawfectcaremicroservices.feedback.interfaces.rest.resources;

public record CreateReviewResource(
        String content,
        Integer rating,
        Long medicalAppointmentId
) {
}
