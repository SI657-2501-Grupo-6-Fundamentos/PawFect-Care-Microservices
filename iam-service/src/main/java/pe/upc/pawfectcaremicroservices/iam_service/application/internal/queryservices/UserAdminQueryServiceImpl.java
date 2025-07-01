package pe.upc.pawfectcaremicroservices.iam_service.application.internal.queryservices;

import pe.upc.pawfectcaremicroservices.iam_service.domain.model.aggregates.UserAdmin;
import pe.upc.pawfectcaremicroservices.iam_service.domain.model.queries.GetAllUsersAdminQuery;
import pe.upc.pawfectcaremicroservices.iam_service.domain.model.queries.GetUserAdminByIdQuery;
import pe.upc.pawfectcaremicroservices.iam_service.domain.model.queries.GetUserAdminByUserNameQuery;
import pe.upc.pawfectcaremicroservices.iam_service.domain.services.UserAdminQueryService;
import org.springframework.stereotype.Service;
import pe.upc.pawfectcaremicroservices.iam_service.infrastructure.persistence.jpa.repositories.JpaUserAdminRepositoryImpl;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of {@link UserAdminQueryService} interface.
 */
@Service
public class UserAdminQueryServiceImpl implements UserAdminQueryService {
    private final JpaUserAdminRepositoryImpl jpaUserAdminRepository;

    /**
     * Constructor.
     *
     * @param jpaUserAdminRepository {@link JpaUserAdminRepositoryImpl} instance.
     */
    public UserAdminQueryServiceImpl(JpaUserAdminRepositoryImpl jpaUserAdminRepository) {
        this.jpaUserAdminRepository = jpaUserAdminRepository;
    }

    /**
     * This method is used to handle {@link GetAllUsersAdminQuery} query.
     * @param query {@link GetAllUsersAdminQuery} instance.
     * @return {@link List} of {@link UserAdmin} instances.
     * @see GetAllUsersAdminQuery
     */
    @Override
    public List<UserAdmin> handle(GetAllUsersAdminQuery query) {
        return jpaUserAdminRepository.findAll();
    }

    /**
     * This method is used to handle {@link GetUserAdminByIdQuery} query.
     * @param query {@link GetUserAdminByIdQuery} instance.
     * @return {@link Optional} of {@link UserAdmin} instance.
     * @see GetUserAdminByIdQuery
     */
    @Override
    public Optional<UserAdmin> handle(GetUserAdminByIdQuery query) {
        return jpaUserAdminRepository.findById(query.userId());
    }

    /**
     * This method is used to handle {@link GetUserAdminByUserNameQuery} query.
     * @param query {@link GetUserAdminByUserNameQuery} instance.
     * @return {@link Optional} of {@link UserAdmin} instance.
     * @see GetUserAdminByUserNameQuery
     */
    @Override
    public Optional<UserAdmin> handle(GetUserAdminByUserNameQuery query) {
        return jpaUserAdminRepository.findByUserName(query.username());
    }
}
