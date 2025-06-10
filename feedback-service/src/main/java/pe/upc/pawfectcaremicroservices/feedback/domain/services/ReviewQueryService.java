package pe.upc.pawfectcaremicroservices.feedback.domain.services;

import pe.upc.pawfectcaremicroservices.feedback.domain.model.aggregates.Review;
import pe.upc.pawfectcaremicroservices.feedback.domain.model.queries.GetAllReviewsByVeterinarianIdQuery;
import pe.upc.pawfectcaremicroservices.feedback.domain.model.queries.GetAllReviewsQuery;
import pe.upc.pawfectcaremicroservices.feedback.domain.model.queries.GetReviewByIdQuery;

import java.util.List;
import java.util.Optional;

public interface ReviewQueryService {
    Optional<Review> handle(GetReviewByIdQuery query);
    List<Review> handle(GetAllReviewsQuery query);
    List<Review> handle(GetAllReviewsByVeterinarianIdQuery query);
}
