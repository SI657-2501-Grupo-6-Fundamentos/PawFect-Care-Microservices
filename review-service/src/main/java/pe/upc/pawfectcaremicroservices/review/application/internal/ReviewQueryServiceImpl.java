package pe.upc.pawfectcaremicroservices.review.application.internal;

import org.springframework.stereotype.Service;
import pe.upc.pawfectcaremicroservices.review.domain.model.aggregates.Review;
import pe.upc.pawfectcaremicroservices.review.domain.model.queries.GetAllReviewsByMedicalAppointmentIdQuery;
import pe.upc.pawfectcaremicroservices.review.domain.model.queries.GetAllReviewsQuery;
import pe.upc.pawfectcaremicroservices.review.domain.model.queries.GetReviewByIdQuery;
import pe.upc.pawfectcaremicroservices.review.domain.services.ReviewQueryService;
import pe.upc.pawfectcaremicroservices.review.infrastructure.persistence.jpa.repositories.ReviewRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewQueryServiceImpl implements ReviewQueryService {
    private final ReviewRepository reviewRepository;
    public ReviewQueryServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Optional<Review> handle(GetReviewByIdQuery query) {
        return reviewRepository.findById((query.id()));
    }

    @Override
    public List<Review> handle(GetAllReviewsQuery query) {
        return reviewRepository.findAll();
    }

    @Override
    public List<Review> handle(GetAllReviewsByMedicalAppointmentIdQuery query) {
        var reviews = reviewRepository.findAllByMedicalAppointmentId(query.medicalAppointmentId());
        return reviews != null ? reviews : List.of();
    }
}
