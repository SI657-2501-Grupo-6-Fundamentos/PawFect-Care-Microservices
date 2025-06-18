package pe.upc.pawfectcaremicroservices.petmanagement.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.upc.pawfectcaremicroservices.petmanagement.domain.model.aggregates.Pet;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
      boolean existsById(Long id);
      List<Pet> findAllByOwnerId(Long ownerId);
}
