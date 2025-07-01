package pe.upc.pawfectcaremicroservices.iam_service.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.upc.pawfectcaremicroservices.iam_service.domain.model.aggregates.UserAdmin;

import java.util.Optional;

public interface JpaUserAdminRepositoryImpl extends JpaRepository<UserAdmin, Long> {
    Optional<UserAdmin> findByUserName(String userName);
    boolean existsByUserName(String userName);
}
