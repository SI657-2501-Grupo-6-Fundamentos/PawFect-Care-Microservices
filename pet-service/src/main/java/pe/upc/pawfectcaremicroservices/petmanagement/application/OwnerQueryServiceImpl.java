package pe.upc.pawfectcaremicroservices.petmanagement.application;

import org.springframework.stereotype.Service;
import pe.upc.pawfectcaremicroservices.petmanagement.domain.model.aggregates.Owner;
import pe.upc.pawfectcaremicroservices.petmanagement.domain.model.queries.GetAllOwnersQuery;
import pe.upc.pawfectcaremicroservices.petmanagement.domain.model.queries.GetOwnerByIdQuery;
import pe.upc.pawfectcaremicroservices.petmanagement.domain.services.OwnerQueryService;
import pe.upc.pawfectcaremicroservices.petmanagement.infrastructure.persistence.jpa.repositories.OwnerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OwnerQueryServiceImpl implements OwnerQueryService {
    private final OwnerRepository ownerRepository;
    public OwnerQueryServiceImpl(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public Optional<Owner> handle(GetOwnerByIdQuery query) {
        return ownerRepository.findById(query.ownerId());
    }

    @Override
    public List<Owner> handle(GetAllOwnersQuery query) {
        return ownerRepository.findAll();
    }



}
