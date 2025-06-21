package pe.upc.pawfectcaremicroservices.profile_service.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.upc.pawfectcaremicroservices.profile_service.domain.model.aggregates.Profile;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Optional<Profile> findByIamUserId(Long iamUserId);
}