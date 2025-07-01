package pe.upc.pawfectcaremicroservices.iam_service.infrastructure.authorization.sfs.services;

import pe.upc.pawfectcaremicroservices.iam_service.infrastructure.authorization.sfs.model.UserAdminDetailsImpl;
import pe.upc.pawfectcaremicroservices.iam_service.domain.repository.UserAdminRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * This class is responsible for providing the user details to the Spring Security framework.
 * It implements the UserDetailsService interface.
 */
@Service(value = "defaultUserDetailsService")
public class UserAdminDetailsServiceImpl implements UserDetailsService {

    private final UserAdminRepository userAdminRepository;

    public UserAdminDetailsServiceImpl(UserAdminRepository userAdminRepository) {
        this.userAdminRepository = userAdminRepository;
    }

    /**
     * This method is responsible for loading the user details from the database.
     * @param username The userName.
     * @return The UserDetails object.
     * @throws UsernameNotFoundException If the user is not found.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userAdminRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with userName: " + username));
        return UserAdminDetailsImpl.build(user);
    }
}
