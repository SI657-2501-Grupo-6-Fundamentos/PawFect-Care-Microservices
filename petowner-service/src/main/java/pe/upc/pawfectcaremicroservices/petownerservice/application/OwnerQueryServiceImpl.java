package pe.upc.pawfectcaremicroservices.petownerservice.application;

import org.springframework.stereotype.Service;
import pe.upc.pawfectcaremicroservices.petownerservice.domain.model.aggregates.PetOwner;
import pe.upc.pawfectcaremicroservices.petownerservice.domain.model.queries.GetAllPetOwnersQuery;
import pe.upc.pawfectcaremicroservices.petownerservice.domain.model.queries.GetPetOwnerByIdQuery;
import pe.upc.pawfectcaremicroservices.petownerservice.domain.services.OwnerQueryService;
import pe.upc.pawfectcaremicroservices.petownerservice.infrastructure.persistence.jpa.repositories.PetOwnerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OwnerQueryServiceImpl implements OwnerQueryService {
    private final PetOwnerRepository petOwnerRepository;
    public OwnerQueryServiceImpl(PetOwnerRepository petOwnerRepository) {
        this.petOwnerRepository = petOwnerRepository;
    }

    @Override
    public Optional<PetOwner> handle(GetPetOwnerByIdQuery query) {
        return petOwnerRepository.findById(query.ownerId());
    }

    @Override
    public List<PetOwner> handle(GetAllPetOwnersQuery query) {
        return petOwnerRepository.findAll();
    }



}
