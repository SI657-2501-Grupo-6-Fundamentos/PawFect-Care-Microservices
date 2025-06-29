package pe.upc.pawfectcaremicroservices.iam_service.application.internal.commandservices;


import pe.upc.pawfectcaremicroservices.iam_service.application.internal.outboundservices.hashing.HashingService;
import pe.upc.pawfectcaremicroservices.iam_service.application.internal.outboundservices.tokens.TokenService;
import pe.upc.pawfectcaremicroservices.iam_service.domain.model.aggregates.UserAdmin;
import pe.upc.pawfectcaremicroservices.iam_service.domain.model.commands.SignInCommand;
import pe.upc.pawfectcaremicroservices.iam_service.domain.model.commands.SignUpCommand;
import pe.upc.pawfectcaremicroservices.iam_service.domain.services.UserAdminCommandService;
import pe.upc.pawfectcaremicroservices.iam_service.infrastructure.persistence.jpa.repositories.RoleRepository;
import pe.upc.pawfectcaremicroservices.iam_service.infrastructure.persistence.jpa.repositories.UserAdminRepository;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * UserAdmin command service implementation
 * <p>
 *     This class implements the {@link UserAdminCommandService} interface and provides the implementation for the
 *     {@link SignInCommand} and {@link SignUpCommand} commands.
 * </p>
 */
@Service
public class UserAdminCommandServiceImpl implements UserAdminCommandService {

    private final UserAdminRepository userAdminRepository;
    private final HashingService hashingService;
    private final TokenService tokenService;

    private final RoleRepository roleRepository;

    public UserAdminCommandServiceImpl(
            UserAdminRepository userAdminRepository,
            HashingService hashingService,
            TokenService tokenService,
            RoleRepository roleRepository) {
        this.userAdminRepository = userAdminRepository;
        this.hashingService = hashingService;
        this.tokenService = tokenService;
        this.roleRepository = roleRepository;
    }

    /**
     * Handle the sign-in command
     * <p>
     *     This method handles the {@link SignInCommand} command and returns the user and the token.
     * </p>
     * @param command the sign-in command containing the username and password
     * @return and optional containing the user matching the username and the generated token
     * @throws RuntimeException if the user is not found or the password is invalid
     */
    @Override
    public Optional<ImmutablePair<UserAdmin, String>> handle(SignInCommand command) {
        var user = userAdminRepository.findByUsername(command.username());
        if (user.isEmpty())
            throw new RuntimeException("UserAdmin not found");
        if (!hashingService.matches(command.password(), user.get().getPassword()))
            throw new RuntimeException("Invalid password");
        var token = tokenService.generateToken(user.get().getUsername());
        return Optional.of(ImmutablePair.of(user.get(), token));
    }

    /**
     * Handle the sign-up command
     * <p>
     *     This method handles the {@link SignUpCommand} command and returns the user.
     * </p>
     * @param command the sign-up command containing the username and password
     * @return the created user
     */
    @Override
    public Optional<UserAdmin> handle(SignUpCommand command) {
        if (userAdminRepository.existsByUsername(command.username()))
            throw new RuntimeException("Username already exists");
        var roles = command.roles().stream().map(role -> roleRepository.findByName(role.getName()).orElseThrow(() -> new RuntimeException("Role name not found"))).toList();
        var user = new UserAdmin(command.username(), hashingService.encode(command.password()), roles);
        userAdminRepository.save(user);
        return userAdminRepository.findByUsername(command.username());
    }
}
