package pe.upc.pawfectcaremicroservices.account_service.infrastructure.persistence.jpa.repositories;

import org.springframework.stereotype.Repository;
import pe.upc.pawfectcaremicroservices.account_service.domain.model.aggregates.UserVet;
import pe.upc.pawfectcaremicroservices.account_service.domain.repository.UserVetRepository;

import java.util.Optional;

@Repository
public class UserVetRepositoryImpl implements UserVetRepository {

    private final JpaUserVetRepositoryImpl jpaRepoVet;

    public UserVetRepositoryImpl(JpaUserVetRepositoryImpl jpaRepoVet) {
        this.jpaRepoVet = jpaRepoVet;
    }

    @Override
    public Optional<UserVet> findByUserName(String userName) {
        return jpaRepoVet.findByUserName(userName);
    }

    @Override
    public UserVet saveVet(UserVet userVet) {
        return jpaRepoVet.save(userVet);
    }

    @Override
    public boolean existsByUserName(String userName) {
        return jpaRepoVet.existsByUserName(userName);
    }
}
