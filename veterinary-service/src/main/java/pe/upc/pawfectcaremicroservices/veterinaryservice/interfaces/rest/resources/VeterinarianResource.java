package pe.upc.pawfectcaremicroservices.veterinaryservice.interfaces.rest.resources;

import pe.upc.pawfectcaremicroservices.veterinaryservice.domain.model.valueobjects.VeterinarianSpeciality;

public record VeterinarianResource(
        Long id,
        Long userId,
        String fullName,
        String phoneNumber,
        String email,
        String dni,
        VeterinarianSpeciality specialization) {
}
