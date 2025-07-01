package pe.upc.pawfectcaremicroservices.iam_service.infrastructure.authorization.sfs.model;

import pe.upc.pawfectcaremicroservices.iam_service.domain.model.aggregates.UserAdmin;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * This class is responsible for providing the user details to the Spring Security framework.
 * It implements the UserDetails interface.
 */
@Getter
@EqualsAndHashCode
public class UserAdminDetailsImpl implements UserDetails {

    private final String username;
    @JsonIgnore
    private final String password;
    private final boolean accountNonExpired;
    private final boolean accountNonLocked;
    private final boolean credentialsNonExpired;
    private final boolean enabled;
    private final Collection<? extends GrantedAuthority> authorities;

    public UserAdminDetailsImpl(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
    }

    /**
     * This method is responsible for building the UserAdminDetailsImpl object from the User object.
     * @param userAdmin The userAdmin object.
     * @return The UserAdminDetailsImpl object.
     */
    public static UserAdminDetailsImpl build(UserAdmin userAdmin) {
        var authorities = userAdmin.getRoles().stream()
                .map(role -> role.getName().name())
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return new UserAdminDetailsImpl(
                userAdmin.getUserName(),
                userAdmin.getPassword(),
                authorities);
    }

}
