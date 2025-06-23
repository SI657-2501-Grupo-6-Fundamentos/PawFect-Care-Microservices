package pe.upc.pawfectcaremicroservices.petownerservice.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.upc.pawfectcaremicroservices.petownerservice.domain.model.aggregates.PetOwner;
@Repository
public interface PetOwnerRepository extends JpaRepository<PetOwner, Long> {
    boolean existsByEmail(String email);
    boolean existsById(Long id);
}
