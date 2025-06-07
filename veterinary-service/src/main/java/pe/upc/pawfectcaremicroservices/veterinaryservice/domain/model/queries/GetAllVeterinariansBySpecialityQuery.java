package pe.upc.pawfectcaremicroservices.veterinaryservice.domain.model.queries;

import pe.upc.pawfectcaremicroservices.veterinaryservice.domain.model.valueobjects.VeterinarianSpeciality;

public record GetAllVeterinariansBySpecialityQuery(VeterinarianSpeciality speciality) {
}
