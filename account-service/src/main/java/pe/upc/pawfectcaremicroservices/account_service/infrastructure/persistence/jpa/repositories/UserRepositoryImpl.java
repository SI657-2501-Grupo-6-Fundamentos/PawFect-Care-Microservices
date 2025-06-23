package pe.upc.pawfectcaremicroservices.account_service.infrastructure.persistence.jpa.repositories;

import org.springframework.stereotype.Repository;
import pe.upc.pawfectcaremicroservices.account_service.domain.model.aggregates.User;
import pe.upc.pawfectcaremicroservices.account_service.domain.repository.UserRepository;

import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final JpaUserRepository jpaRepo;

    public UserRepositoryImpl(JpaUserRepository jpaRepo) {
        this.jpaRepo = jpaRepo;
    }

    @Override
    public Optional<User> findByUserName(String userName) {
        return jpaRepo.findByUserName(userName);
    }

    @Override
    public User save(User user) {
        return jpaRepo.save(user);
    }

    @Override
    public boolean existsByUserName(String userName) {
        return jpaRepo.existsByUserName(userName);
    }
}