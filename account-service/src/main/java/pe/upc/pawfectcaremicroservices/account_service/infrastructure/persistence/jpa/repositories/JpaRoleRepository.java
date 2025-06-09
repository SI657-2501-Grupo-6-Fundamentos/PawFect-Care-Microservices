package pe.upc.pawfectcaremicroservices.account_service.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.upc.pawfectcaremicroservices.account_service.domain.model.aggregates.Role;
import java.util.Optional;

public interface JpaRoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}