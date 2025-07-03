package pe.upc.pawfectcaremicroservices.iam_service.application.internal.commandservices;


import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;
import pe.upc.pawfectcaremicroservices.iam_service.application.internal.outboundservices.hashing.HashingService;
import pe.upc.pawfectcaremicroservices.iam_service.application.internal.outboundservices.tokens.TokenService;
import pe.upc.pawfectcaremicroservices.iam_service.domain.model.aggregates.User;
import pe.upc.pawfectcaremicroservices.iam_service.domain.model.commands.*;
import pe.upc.pawfectcaremicroservices.iam_service.domain.model.valueobjects.Roles;
import pe.upc.pawfectcaremicroservices.iam_service.domain.repository.UserRepository;
import pe.upc.pawfectcaremicroservices.iam_service.domain.services.UserCommandService;
import pe.upc.pawfectcaremicroservices.iam_service.infrastructure.oauth.google.services.GoogleOAuthService;
import pe.upc.pawfectcaremicroservices.iam_service.infrastructure.persistence.jpa.repositories.RoleRepository;

import java.util.HashSet;
import java.util.Optional;

import static org.hibernate.sql.ast.SqlTreeCreationLogger.LOGGER;

/**
 * User command service implementation
 * <p>
 *     This class implements the {@link UserCommandService} interface and provides the implementation for the
 *     {@link SignInCommand} and {@link SignUpCommand} commands.
 * </p>
 */
@Service
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final HashingService hashingService;
    private final TokenService tokenService;
    private final RoleRepository roleRepository;
    private final GoogleOAuthService googleOAuthService;

    public UserCommandServiceImpl(
            UserRepository userRepository,
            HashingService hashingService,
            TokenService tokenService,
            RoleRepository roleRepository,
            GoogleOAuthService googleOAuthService) {
        this.userRepository = userRepository;
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
     * @param command the sign-in command containing the userName and password
     * @return and optional containing the user matching the userName and the generated token
     * @throws RuntimeException if the user is not found or the password is invalid
     */
    @Override
    public Optional<ImmutablePair<User, String>> handle(SignInCommand command) {
        var user = userRepository.findByUserName(command.userName());
        if (user.isEmpty())
        {
            LOGGER.warn("No se encontró al usuario con userName: '{}'" + command.userName());
            throw new RuntimeException("User not found");
        }
        if (!hashingService.matches(command.password(), user.get().getPassword()))
            throw new RuntimeException("Invalid password");
        var token = tokenService.generateToken(user.get().getUserName());
        return Optional.of(ImmutablePair.of(user.get(), token));
    }

    /**
     * Handle the sign-up command
     * <p>
     *     This method handles the {@link SignUpCommand} command and returns the user.
     * </p>
     * @param command the sign-up command containing the userName and password
     * @return the created user
     */
    @Override
    public Optional<User> handle(SignUpCommand command) {
        if (userRepository.existsByUserName(command.userName()))
            throw new RuntimeException("Username already exists");

        var userRoles = roleRepository.findAll().stream()
                .filter(role -> command.role().contains(role.getName().name()))
                .toList();

        var user = User.builder()
                .userName(command.userName())
                .password(hashingService.encode(command.password()))
                .fullName(command.fullName())
                .phoneNumber(command.phoneNumber())
                .email(command.email())
                .address(command.address())
                .roles(new HashSet<>(userRoles))  // ← Asigna roles directamente
                .build();

        userRepository.save(user);
        return userRepository.findByUserName(command.userName());
    }


    /**
     * Handle Google Sign In command
     * @param command GoogleSignInCommand containing the Google ID token
     * @return Optional containing User and JWT token pair
     */
    public Optional<ImmutablePair<User, String>> handle(GoogleSignInCommand command) {
        try {
            var payload = googleOAuthService.verifyToken(command.googleToken());
            String email = payload.getEmail();
            String name = (String) payload.get("name");

            // Buscar usuario existente por EMAIL
            var existingUser = userRepository.findByEmail(email);
            User user;

            if (existingUser.isEmpty()) {
                // Crear nuevo usuario con rol por defecto
                var defaultRoles = roleRepository.findAll().stream()
                        .filter(role -> role.getName().name().equals("ROLE_USER"))
                        .toList();

                if (defaultRoles.isEmpty()) {
                    throw new RuntimeException("Default role not found");
                }

                // Generar una contraseña aleatoria (no se usará para login con Google)
                String randomPassword = java.util.UUID.randomUUID().toString();

                user = User.builder()
                        .userName(email)  // Usar el email completo como username
                        .password(hashingService.encode(randomPassword))
                        .fullName(name != null ? name : "Google User")
                        .phoneNumber("N/A")
                        .email(email)
                        .address("N/A")
                        .roles(new HashSet<>(defaultRoles))
                        .build();

                userRepository.save(user);
            } else {
                // Usuario ya existe, usar el existente
                user = existingUser.get();
            }

            var token = tokenService.generateToken(user.getUserName());
            return Optional.of(ImmutablePair.of(user, token));

        } catch (Exception e) {
            throw new RuntimeException("Google authentication failed: " + e.getMessage());
        }
    }


    @Override
    public Optional<ImmutablePair<User, String>> handle(GoogleCallbackCommand command) {
        // Implementar si necesitas el flujo completo de OAuth2 con callback
        // Por ahora, usando solo token directo
        throw new UnsupportedOperationException("Google callback not implemented yet");
    }

    @Override
    public boolean isEmailUnique(String email) {
        return !userRepository.existsByUserName(email);
    }

    @Override
    public boolean isPasswordValid(String password) {
        return password != null && password.length() >= 6;
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
