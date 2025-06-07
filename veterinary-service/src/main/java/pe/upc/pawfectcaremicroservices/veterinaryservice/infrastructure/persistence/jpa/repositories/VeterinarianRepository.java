package pe.upc.pawfectcaremicroservices.veterinaryservice.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.upc.pawfectcaremicroservices.veterinaryservice.domain.model.aggregates.Veterinarian;
import pe.upc.pawfectcaremicroservices.veterinaryservice.domain.model.valueobjects.VeterinarianSpeciality;

import java.util.List;

@Repository
public interface VeterinarianRepository extends JpaRepository<Veterinarian, Long> {
    boolean existsById(Long id);
    boolean existsByDni(String dni);
    List<Veterinarian> findAllByVeterinarianSpeciality(VeterinarianSpeciality veterinarianSpeciality);
}
