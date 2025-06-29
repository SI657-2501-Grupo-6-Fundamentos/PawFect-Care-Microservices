package pe.upc.pawfectcaremicroservices.account_service.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.upc.pawfectcaremicroservices.account_service.domain.model.aggregates.UserVet;

import java.util.Optional;

public interface JpaUserVetRepositoryImpl extends JpaRepository<UserVet, Long> {
    Optional<UserVet> findByUserName(String userName);
    boolean existsByUserName(String userName);
}
