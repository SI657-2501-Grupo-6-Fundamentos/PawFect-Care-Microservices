package pe.upc.pawfectcaremicroservices.iam_service.infrastructure.persistence.jpa.repositories;

import org.springframework.stereotype.Repository;
import pe.upc.pawfectcaremicroservices.iam_service.domain.model.aggregates.User;
import pe.upc.pawfectcaremicroservices.iam_service.domain.model.aggregates.UserAdmin;
import pe.upc.pawfectcaremicroservices.iam_service.domain.repository.UserAdminRepository;

import java.util.Optional;

@Repository
public class UserAdminRepositoryImpl implements UserAdminRepository {

    private final JpaUserAdminRepositoryImpl jpaRepoAdmin;

    public UserAdminRepositoryImpl(JpaUserAdminRepositoryImpl jpaRepoAdmin) {
        this.jpaRepoAdmin = jpaRepoAdmin;
    }

    @Override
    public Optional<UserAdmin> findByUserName(String userName) {
        return jpaRepoAdmin.findByUserName(userName);
    }

    @Override
    public UserAdmin saveAdmin(UserAdmin userVet) {
        return jpaRepoAdmin.save(userVet);
    }

    @Override
    public boolean existsByUserName(String userName) {
        return jpaRepoAdmin.existsByUserName(userName);
    }

    @Override
    public Optional<UserAdmin> findByEmail(String email) {
        return jpaRepoAdmin.findByEmail(email);
    }
}
