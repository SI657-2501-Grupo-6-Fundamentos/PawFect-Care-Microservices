package pe.upc.pawfectcaremicroservices.review.domain.services;

import pe.upc.pawfectcaremicroservices.review.domain.model.aggregates.Review;
import pe.upc.pawfectcaremicroservices.review.domain.model.queries.GetAllReviewsByMedicalAppointmentIdQuery;
import pe.upc.pawfectcaremicroservices.review.domain.model.queries.GetAllReviewsQuery;
import pe.upc.pawfectcaremicroservices.review.domain.model.queries.GetReviewByIdQuery;

import java.util.List;
import java.util.Optional;

public interface ReviewQueryService {
    Optional<Review> handle(GetReviewByIdQuery query);
    List<Review> handle(GetAllReviewsQuery query);
    List<Review> handle(GetAllReviewsByMedicalAppointmentIdQuery query);
}
