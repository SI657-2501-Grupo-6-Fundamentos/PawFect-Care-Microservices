package pe.upc.pawfectcaremicroservices.review.interfaces.rest.transform;

import pe.upc.pawfectcaremicroservices.review.domain.model.commands.UpdateReviewCommand;
import pe.upc.pawfectcaremicroservices.review.interfaces.rest.resources.UpdateReviewResource;

public class UpdateReviewCommandFromResourceAssembler {
    public static UpdateReviewCommand toCommandFromResource(Long reviewId, UpdateReviewResource resource) {
        return new UpdateReviewCommand(
                reviewId,
                resource.content(),
                resource.rating());
    }
}
