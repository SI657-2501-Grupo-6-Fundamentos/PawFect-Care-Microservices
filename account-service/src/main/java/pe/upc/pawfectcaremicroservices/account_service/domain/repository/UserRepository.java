package pe.upc.pawfectcaremicroservices.account_service.domain.repository;

import pe.upc.pawfectcaremicroservices.account_service.domain.model.aggregates.User;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findByUserName(String userName);
    User save(User user);
    boolean existsByUserName(String userName);
}