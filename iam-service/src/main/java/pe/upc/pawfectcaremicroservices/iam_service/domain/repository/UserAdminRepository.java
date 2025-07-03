package pe.upc.pawfectcaremicroservices.iam_service.domain.repository;

import pe.upc.pawfectcaremicroservices.iam_service.domain.model.aggregates.User;
import pe.upc.pawfectcaremicroservices.iam_service.domain.model.aggregates.UserAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * This interface is responsible for providing the User entity related operations.
 * It extends the JpaRepository interface.
 */
@Repository
public interface UserAdminRepository
{
    /**
     * This method is responsible for finding the user by userName.
     * @param userName The userName.
     * @return The user object.
     */
    Optional<UserAdmin> findByUserName(String userName);

    /**
     * This method is responsible for checking if the user exists by userName.
     * @param userName The userName.
     * @return True if the user exists, false otherwise.
     */
    boolean existsByUserName(String userName);

    UserAdmin saveAdmin(UserAdmin user);

    Optional<UserAdmin> findByEmail(String email);

}
