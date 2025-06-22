package pe.upc.pawfectcaremicroservices.review.application.internal;

import org.springframework.stereotype.Service;
import pe.upc.pawfectcaremicroservices.review.application.external.clients.ExternalMedicalAppointment;
import pe.upc.pawfectcaremicroservices.review.domain.model.aggregates.Review;
import pe.upc.pawfectcaremicroservices.review.domain.model.commands.CreateReviewCommand;
import pe.upc.pawfectcaremicroservices.review.domain.model.commands.UpdateReviewCommand;
import pe.upc.pawfectcaremicroservices.review.domain.services.ReviewCommandService;
import pe.upc.pawfectcaremicroservices.review.infrastructure.persistence.jpa.repositories.ReviewRepository;

import java.util.Optional;

@Service
public class ReviewCommandServiceImpl implements ReviewCommandService {
    private final ReviewRepository reviewRepository;
    private final ExternalMedicalAppointment externalMedicalAppointment;

    public ReviewCommandServiceImpl(ReviewRepository reviewRepository, ExternalMedicalAppointment externalMedicalAppointment) {
        this.reviewRepository = reviewRepository;
        this.externalMedicalAppointment = externalMedicalAppointment;
    }

    @Override
    public Long handle(CreateReviewCommand command) {
        var review = new Review(command);
        try {
            if (!externalMedicalAppointment.existsMedicalAppointmentById(command.medicalAppointmentId()))
                throw new IllegalArgumentException("medicalAppointmentId does not exist");
            review.setMedicalAppointmentId(command.medicalAppointmentId());
            reviewRepository.save(review);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving review: " + e.getMessage());
        }

        return review.getId();
    }

    @Override
    public Optional<Review> handle(UpdateReviewCommand command) {
        if (!reviewRepository.existsById(command.id()))
            throw new IllegalArgumentException("reviewId does not exist");
        var result = reviewRepository.findById(command.id());
        var reviewToUpdate = result.get();
        try {
            var updatedReview = reviewRepository.save(reviewToUpdate.updateInformation(command.content(), command.rating()));
            return Optional.of(updatedReview);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating review: " + e.getMessage());
        }
    }
}
