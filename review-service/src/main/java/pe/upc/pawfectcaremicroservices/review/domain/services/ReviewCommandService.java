package pe.upc.pawfectcaremicroservices.review.domain.services;

import pe.upc.pawfectcaremicroservices.review.domain.model.aggregates.Review;
import pe.upc.pawfectcaremicroservices.review.domain.model.commands.CreateReviewCommand;
import pe.upc.pawfectcaremicroservices.review.domain.model.commands.UpdateReviewCommand;

import java.util.Optional;

public interface ReviewCommandService {
    Optional<Review> handle(UpdateReviewCommand command);
    Long handle(CreateReviewCommand command);
}
