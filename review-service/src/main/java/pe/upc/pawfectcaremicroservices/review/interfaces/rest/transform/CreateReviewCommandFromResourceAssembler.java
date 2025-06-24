package pe.upc.pawfectcaremicroservices.review.interfaces.rest.transform;

import pe.upc.pawfectcaremicroservices.review.domain.model.commands.CreateReviewCommand;
import pe.upc.pawfectcaremicroservices.review.interfaces.rest.resources.CreateReviewResource;

public class CreateReviewCommandFromResourceAssembler {
    public static CreateReviewCommand toCommandFromResource(CreateReviewResource resource) {
        return new CreateReviewCommand(resource.content(), resource.rating(), resource.medicalAppointmentId());
    }
}
