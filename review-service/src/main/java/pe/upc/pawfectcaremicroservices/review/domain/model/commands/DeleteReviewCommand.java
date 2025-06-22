package pe.upc.pawfectcaremicroservices.review.domain.model.commands;

public record DeleteReviewCommand(Long id) {
    // This record is used to encapsulate the command for deleting a review by its ID.
    // It contains a single field, id, which is the identifier of the review to be deleted.
}
