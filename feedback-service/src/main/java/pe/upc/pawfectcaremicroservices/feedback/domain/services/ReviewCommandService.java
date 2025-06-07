package pe.upc.pawfectcaremicroservices.feedback.domain.services;

import pe.upc.pawfectcaremicroservices.feedback.domain.model.aggregates.Review;
import pe.upc.pawfectcaremicroservices.feedback.domain.model.commands.CreateReviewCommand;
import pe.upc.pawfectcaremicroservices.feedback.domain.model.commands.UpdateReviewCommand;

import java.util.Optional;

public interface ReviewCommandService {
    Optional<Review> handle(UpdateReviewCommand command);
    Long handle(CreateReviewCommand command);
}
