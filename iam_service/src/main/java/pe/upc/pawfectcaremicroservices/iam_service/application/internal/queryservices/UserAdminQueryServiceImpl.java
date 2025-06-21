package pe.upc.pawfectcaremicroservices.iam_service.application.internal.queryservices;

import pe.upc.pawfectcaremicroservices.iam_service.domain.model.aggregates.UserAdmin;
import pe.upc.pawfectcaremicroservices.iam_service.domain.model.queries.GetAllUsersAdminQuery;
import pe.upc.pawfectcaremicroservices.iam_service.domain.model.queries.GetUserAdminByIdQuery;
import pe.upc.pawfectcaremicroservices.iam_service.domain.model.queries.GetUserAdminByUsernameQuery;
import pe.upc.pawfectcaremicroservices.iam_service.domain.services.UserAdminQueryService;
import pe.upc.pawfectcaremicroservices.iam_service.infrastructure.persistence.jpa.repositories.UserAdminRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of {@link UserAdminQueryService} interface.
 */
@Service
public class UserAdminQueryServiceImpl implements UserAdminQueryService {
    private final UserAdminRepository userAdminRepository;

    /**
     * Constructor.
     *
     * @param userAdminRepository {@link UserAdminRepository} instance.
     */
    public UserAdminQueryServiceImpl(UserAdminRepository userAdminRepository) {
        this.userAdminRepository = userAdminRepository;
    }

    /**
     * This method is used to handle {@link GetAllUsersAdminQuery} query.
     * @param query {@link GetAllUsersAdminQuery} instance.
     * @return {@link List} of {@link UserAdmin} instances.
     * @see GetAllUsersAdminQuery
     */
    @Override
    public List<UserAdmin> handle(GetAllUsersAdminQuery query) {
        return userAdminRepository.findAll();
    }

    /**
     * This method is used to handle {@link GetUserAdminByIdQuery} query.
     * @param query {@link GetUserAdminByIdQuery} instance.
     * @return {@link Optional} of {@link UserAdmin} instance.
     * @see GetUserAdminByIdQuery
     */
    @Override
    public Optional<UserAdmin> handle(GetUserAdminByIdQuery query) {
        return userAdminRepository.findById(query.userId());
    }

    /**
     * This method is used to handle {@link GetUserAdminByUsernameQuery} query.
     * @param query {@link GetUserAdminByUsernameQuery} instance.
     * @return {@link Optional} of {@link UserAdmin} instance.
     * @see GetUserAdminByUsernameQuery
     */
    @Override
    public Optional<UserAdmin> handle(GetUserAdminByUsernameQuery query) {
        return userAdminRepository.findByUsername(query.username());
    }
}
