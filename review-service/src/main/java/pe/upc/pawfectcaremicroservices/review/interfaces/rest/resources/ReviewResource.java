package pe.upc.pawfectcaremicroservices.review.interfaces.rest.resources;

public record ReviewResource(
        Long id,
        String content,
        Integer rating,
        Long medicalAppointmentId) {
}
