package pe.upc.pawfectcaremicroservices.feedback.application.internal;

import org.springframework.stereotype.Service;
//import pe.upc.pawfectcaremicroservices.feedback.application.acl.ExternalVeterinarianService;
import pe.upc.pawfectcaremicroservices.feedback.domain.model.aggregates.Review;
import pe.upc.pawfectcaremicroservices.feedback.domain.model.commands.CreateReviewCommand;
import pe.upc.pawfectcaremicroservices.feedback.domain.model.commands.UpdateReviewCommand;
import pe.upc.pawfectcaremicroservices.feedback.domain.services.ReviewCommandService;
import pe.upc.pawfectcaremicroservices.feedback.infrastructure.persistence.jpa.repositories.ReviewRepository;

import java.util.Optional;

@Service
public class ReviewCommandServiceImpl implements ReviewCommandService {
    private final ReviewRepository reviewRepository;
    //private final ExternalVeterinarianService externalVeterinarianService;

    public ReviewCommandServiceImpl(ReviewRepository reviewRepository/*, ExternalVeterinarianService externalVeterinarianService*/) {
        this.reviewRepository = reviewRepository;
        //this.externalVeterinarianService = externalVeterinarianService;
    }

    @Override
    public Long handle(CreateReviewCommand command) {

        /*Veterinarian veterinarian = externalVeterinarianService.fetchVeterinarianById(command.veterinarianId()).orElseThrow(() -> new VeterinarianNotFoundException(command.veterinarianId()));*/
        Review review = new Review(command);
        /*review.setVeterinarian(veterinarian);*/
        try {
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
