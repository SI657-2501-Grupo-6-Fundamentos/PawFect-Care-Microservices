package pe.upc.pawfectcaremicroservices.iam_service.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.*;
import pe.upc.pawfectcaremicroservices.iam_service.domain.model.entities.Role;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "userName"))
@Getter
@Setter
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false)
    private String password;

    @Column()
    private String fullName;

    @Column()
    private String phoneNumber;

    @Column(nullable = false, unique = true)
    private String email;

    @Column()
    private String address;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    public User() {
        this.roles = new HashSet<>();
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.roles = new HashSet<>();
    }

    public User(String userName, String password, List<Role> roles) {
        this(userName, password);
        //addRoles(roles);
    }

    private User addRole(Role role) {
        this.roles.add(role);
        return this;
    }

    public User addRoles(List<Role> roles) {
        var validatedRoleSet = Role.validateRoleSet(roles);
        this.roles.addAll(validatedRoleSet);
        return this;
    }
}