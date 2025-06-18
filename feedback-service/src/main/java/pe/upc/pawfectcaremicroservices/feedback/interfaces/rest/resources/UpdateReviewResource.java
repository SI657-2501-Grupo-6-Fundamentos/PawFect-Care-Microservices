package pe.upc.pawfectcaremicroservices.feedback.interfaces.rest.resources;

public record UpdateReviewResource(
        String content,
        Integer rating) {
}
