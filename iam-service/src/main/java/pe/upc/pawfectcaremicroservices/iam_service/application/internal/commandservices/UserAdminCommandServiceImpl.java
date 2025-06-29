package pe.upc.pawfectcaremicroservices.iam_service.application.internal.commandservices;


import pe.upc.pawfectcaremicroservices.iam_service.application.internal.outboundservices.hashing.HashingService;
import pe.upc.pawfectcaremicroservices.iam_service.application.internal.outboundservices.tokens.TokenService;
import pe.upc.pawfectcaremicroservices.iam_service.domain.model.aggregates.UserAdmin;
import pe.upc.pawfectcaremicroservices.iam_service.domain.model.commands.GoogleCallbackCommand;
import pe.upc.pawfectcaremicroservices.iam_service.domain.model.commands.GoogleSignInCommand;
import pe.upc.pawfectcaremicroservices.iam_service.domain.model.commands.SignInCommand;
import pe.upc.pawfectcaremicroservices.iam_service.domain.model.commands.SignUpCommand;
import pe.upc.pawfectcaremicroservices.iam_service.domain.model.valueobjects.Roles;
import pe.upc.pawfectcaremicroservices.iam_service.domain.services.UserAdminCommandService;
import pe.upc.pawfectcaremicroservices.iam_service.infrastructure.oauth.google.services.GoogleOAuthService;
import pe.upc.pawfectcaremicroservices.iam_service.infrastructure.persistence.jpa.repositories.RoleRepository;
import pe.upc.pawfectcaremicroservices.iam_service.infrastructure.persistence.jpa.repositories.UserAdminRepository;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.hibernate.sql.ast.SqlTreeCreationLogger.LOGGER;

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
    private final GoogleOAuthService googleOAuthService;

    public UserAdminCommandServiceImpl(
            UserAdminRepository userAdminRepository,
            HashingService hashingService,
            TokenService tokenService,
            RoleRepository roleRepository,
            GoogleOAuthService googleOAuthService) {
        this.userAdminRepository = userAdminRepository;
        this.hashingService = hashingService;
        this.tokenService = tokenService;
        this.roleRepository = roleRepository;
        this.googleOAuthService = googleOAuthService;
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

    /**
     * Handle Google Sign In command
     * @param command GoogleSignInCommand containing the Google ID token
     * @return Optional containing UserAdmin and JWT token pair
     */
    public Optional<ImmutablePair<UserAdmin, String>> handle(GoogleSignInCommand command) {
        try {
            var payload = googleOAuthService.verifyToken(command.googleToken());
            String email = payload.getEmail();
            String name = (String) payload.get("name");

            // Buscar usuario existente o crear uno nuevo
            var existingUser = userAdminRepository.findByUsername(email);
            UserAdmin user;

            if (existingUser.isEmpty()) {
                // Crear nuevo usuario con rol por defecto (asume que tienes un rol ROLE_USER)
                // Ajusta este código según tus roles disponibles
                var defaultRoles = roleRepository.findAll().stream()
                        .filter(role -> role.getName().name().equals("ROLE_USER"))
                        .toList();

                if (defaultRoles.isEmpty()) {
                    throw new RuntimeException("Default role not found");
                }

                // Generar una contraseña aleatoria (no se usará para login con Google)
                String randomPassword = java.util.UUID.randomUUID().toString();
                user = new UserAdmin(email, hashingService.encode(randomPassword), defaultRoles);
                userAdminRepository.save(user);
            } else {
                user = existingUser.get();
            }

            var token = tokenService.generateToken(user.getUsername());
            return Optional.of(ImmutablePair.of(user, token));

        } catch (Exception e) {
            throw new RuntimeException("Google authentication failed: " + e.getMessage());
        }
    }

    @Override
    public Optional<ImmutablePair<UserAdmin, String>> handle(GoogleCallbackCommand command) {
        // Implementar si necesitas el flujo completo de OAuth2 con callback
        // Por ahora, usando solo token directo
        throw new UnsupportedOperationException("Google callback not implemented yet");
    }

    /**
     * Determina el rol por defecto para usuarios de Google OAuth
     * Puedes personalizar esta lógica según tus necesidades
     */
    private Roles getDefaultRoleForGoogleUser() {
        // Retorna el rol por defecto - ajusta según tu enum Roles
        return Roles.ROLE_USER;
    }
}
