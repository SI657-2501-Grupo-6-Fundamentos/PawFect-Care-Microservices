package pe.upc.pawfectcaremicroservices.review.domain.model.commands;

public record UpdateReviewCommand(Long id,
                                  String content,
                                  Integer rating) {
    // This record represents a command to update a review with an ID, content, and rating.
    // It can be used in the application layer to encapsulate the data needed for updating a review.
}
