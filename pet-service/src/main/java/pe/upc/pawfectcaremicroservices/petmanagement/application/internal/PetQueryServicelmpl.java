package pe.upc.pawfectcaremicroservices.petmanagement.application.internal;

import org.springframework.stereotype.Service;
import pe.upc.pawfectcaremicroservices.petmanagement.domain.model.aggregates.Pet;
import pe.upc.pawfectcaremicroservices.petmanagement.domain.model.queries.GetAllPetsByOwnerIdQuery;
import pe.upc.pawfectcaremicroservices.petmanagement.domain.model.queries.GetAllPetsQuery;
import pe.upc.pawfectcaremicroservices.petmanagement.domain.model.queries.GetPetByIdQuery;
import pe.upc.pawfectcaremicroservices.petmanagement.domain.services.PetQueryService;
import pe.upc.pawfectcaremicroservices.petmanagement.infrastructure.persistence.jpa.repositories.PetRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PetQueryServicelmpl implements PetQueryService {

    private final PetRepository petRepository;
    public PetQueryServicelmpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public List<Pet> handle(GetAllPetsQuery query) {
        return petRepository.findAll();
    }

    @Override
    public Optional<Pet> handle(GetPetByIdQuery query) {
        if (!petRepository.existsById(query.petId())) {
            throw new IllegalArgumentException("petId not found");
        }
        return petRepository.findById(query.petId());
    }

    @Override
    public List<Pet> handle(GetAllPetsByOwnerIdQuery query) {
        return petRepository.findAllByOwnerId(query.ownerId());
    }



}
