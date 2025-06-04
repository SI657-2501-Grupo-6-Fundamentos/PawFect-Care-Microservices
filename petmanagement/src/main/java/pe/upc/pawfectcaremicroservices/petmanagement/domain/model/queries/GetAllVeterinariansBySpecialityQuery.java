package pe.upc.pawfectcaremicroservices.petmanagement.domain.model.queries;

import pe.upc.pawfectcaremicroservices.petmanagement.domain.model.valueobjects.VeterinarianSpeciality;

public record GetAllVeterinariansBySpecialityQuery(VeterinarianSpeciality speciality) {
}
