package pe.upc.pawfectcaremicroservices.review.domain.model.commands;

public record CreateReviewCommand(
        String content,
        Integer rating,
        Long medicalAppointmentId
) {
}
