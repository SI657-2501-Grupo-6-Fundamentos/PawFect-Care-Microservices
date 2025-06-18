package pe.upc.pawfectcaremicroservices.account_service.domain.repository;

import pe.upc.pawfectcaremicroservices.account_service.domain.model.aggregates.User;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findByEmail(String email);
    User save(User user);
    boolean existsByEmail(String email);
}