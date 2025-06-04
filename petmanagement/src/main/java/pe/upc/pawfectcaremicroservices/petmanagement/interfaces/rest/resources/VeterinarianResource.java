package pe.upc.pawfectcaremicroservices.petmanagement.interfaces.rest.resources;

import pe.upc.pawfectcaremicroservices.petmanagement.domain.model.valueobjects.VeterinarianSpeciality;

public record VeterinarianResource(
        Long id,
        String fullName,
        String phoneNumber,
        String email,
        String dni,
        VeterinarianSpeciality specialization) {
}
