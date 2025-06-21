package pe.upc.pawfectcaremicroservices.feedback.interfaces.rest.transform;

import pe.upc.pawfectcaremicroservices.feedback.domain.model.aggregates.Review;
import pe.upc.pawfectcaremicroservices.feedback.interfaces.rest.resources.ReviewResource;

public class ReviewResourceFromEntityAssembler {
    public static ReviewResource toResourceFromEntity(Review entity) {
        return new ReviewResource(
                entity.getId(),
                entity.getContent(),
                entity.getRating(),
                entity.getMedicalAppointmentId());
    }
}
