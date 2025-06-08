package pe.upc.pawfectcaremicroservices.clientservice.application;

import org.springframework.stereotype.Service;
import pe.upc.pawfectcaremicroservices.clientservice.domain.model.aggregates.Owner;
import pe.upc.pawfectcaremicroservices.clientservice.domain.model.queries.GetAllOwnersQuery;
import pe.upc.pawfectcaremicroservices.clientservice.domain.model.queries.GetOwnerByIdQuery;
import pe.upc.pawfectcaremicroservices.clientservice.domain.services.OwnerQueryService;
import pe.upc.pawfectcaremicroservices.clientservice.infrastructure.persistence.jpa.repositories.OwnerRepository;

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
