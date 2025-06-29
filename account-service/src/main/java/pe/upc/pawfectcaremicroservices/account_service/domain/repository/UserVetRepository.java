package pe.upc.pawfectcaremicroservices.account_service.domain.repository;

import pe.upc.pawfectcaremicroservices.account_service.domain.model.aggregates.UserVet;

import java.util.Optional;

public interface UserVetRepository {
    Optional<UserVet> findByUserName(String userName);
    UserVet saveVet(UserVet user);
    boolean existsByUserName(String userName);
}
