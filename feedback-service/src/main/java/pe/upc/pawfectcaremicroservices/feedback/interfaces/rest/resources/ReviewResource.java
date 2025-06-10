package pe.upc.pawfectcaremicroservices.feedback.interfaces.rest.resources;

public record ReviewResource(
        Long id,
        String content,
        Integer rating,
        Long veterinarianId) {
}
