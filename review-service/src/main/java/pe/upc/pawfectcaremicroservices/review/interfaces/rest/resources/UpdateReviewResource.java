package pe.upc.pawfectcaremicroservices.review.interfaces.rest.resources;

public record UpdateReviewResource(
        String content,
        Integer rating) {
}
