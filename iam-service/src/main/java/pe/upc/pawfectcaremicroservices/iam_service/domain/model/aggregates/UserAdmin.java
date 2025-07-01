package pe.upc.pawfectcaremicroservices.iam_service.domain.model.aggregates;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import pe.upc.pawfectcaremicroservices.iam_service.domain.model.entities.Role;
import pe.upc.pawfectcaremicroservices.iam_service.domain.model.valueobjects.VeterinarianSpeciality;
import pe.upc.pawfectcaremicroservices.iam_service.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * User aggregate root
 * This class represents the aggregate root for the User entity.
 *
 * @see AuditableAbstractAggregateRoot
 */
@Entity
@Table(name = "users_admin", uniqueConstraints = @UniqueConstraint(columnNames = "userName"))
@Getter
@Setter
@AllArgsConstructor
@Builder
public class UserAdmin extends AuditableAbstractAggregateRoot<UserAdmin> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String userName;

    @NotBlank
    @JsonProperty("password")
    private String password;

    @NotBlank
    @JsonProperty("fullName")
    private String fullName;

    @NotBlank
    @JsonProperty("phoneNumber")
    private String phoneNumber;

    @NotBlank
    @JsonProperty("email")
    private String email;

    @NotBlank
    @JsonProperty("dni")
    private String dni;

    @Column(nullable = false)
    private VeterinarianSpeciality veterinarianSpeciality;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonProperty("availableStartTime")
    private LocalDateTime availableStartTime;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonProperty("availableEndTime")
    private LocalDateTime availableEndTime;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    public UserAdmin() {
        this.roles = new HashSet<>();
    }

    public UserAdmin(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.roles = new HashSet<>();
    }

    public UserAdmin(String userName, String password, List<Role> roles) {
        this(userName, password);
        //addRoles(roles);
    }

    /**
     * Add a role to the user
     * @param role the role to add
     * @return the user with the added role
     */
    public UserAdmin addRole(Role role) {
        this.roles.add(role);
        return this;
    }

    /**
     * Add a list of role to the user
     * @param roles the list of role to add
     * @return the user with the added role
     */
    public UserAdmin addRoles(List<Role> roles) {
        var validatedRoleSet = Role.validateRoleSet(roles);
        this.roles.addAll(validatedRoleSet);
        return this;
    }

}
