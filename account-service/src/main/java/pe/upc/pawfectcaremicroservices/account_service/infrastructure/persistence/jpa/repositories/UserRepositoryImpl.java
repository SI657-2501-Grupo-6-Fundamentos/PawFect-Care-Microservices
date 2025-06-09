package pe.upc.pawfectcaremicroservices.account_service.infrastructure.persistence.jpa;

import org.springframework.stereotype.Repository;
import pe.upc.pawfectcaremicroservices.account_service.domain.model.aggregates.User;
import pe.upc.pawfectcaremicroservices.account_service.domain.repository.UserRepository;
import pe.upc.pawfectcaremicroservices.account_service.infrastructure.persistence.jpa.repositories.JpaUserRepository;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final JpaUserRepository jpaRepo;

    public UserRepositoryImpl(JpaUserRepository jpaRepo) {
        this.jpaRepo = jpaRepo;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jpaRepo.findByEmail(email);
    }

    @Override
    public User save(User user) {
        return jpaRepo.save(user);
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaRepo.existsByEmail(email);
    }
}