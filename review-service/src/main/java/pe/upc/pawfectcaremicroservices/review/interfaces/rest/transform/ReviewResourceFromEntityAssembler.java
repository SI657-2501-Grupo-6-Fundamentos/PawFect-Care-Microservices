package pe.upc.pawfectcaremicroservices.review.interfaces.rest.transform;

import pe.upc.pawfectcaremicroservices.review.domain.model.aggregates.Review;
import pe.upc.pawfectcaremicroservices.review.interfaces.rest.resources.ReviewResource;

public class ReviewResourceFromEntityAssembler {
    public static ReviewResource toResourceFromEntity(Review entity) {
        return new ReviewResource(
                entity.getId(),
                entity.getContent(),
                entity.getRating(),
                entity.getMedicalAppointmentId());
    }
}
