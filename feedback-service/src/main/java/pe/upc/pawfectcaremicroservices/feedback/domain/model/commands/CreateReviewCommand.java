package pe.upc.pawfectcaremicroservices.feedback.domain.model.commands;

public record CreateReviewCommand(
        String content,
        Integer rating,
        Long medicalAppointmentId
) {
}
