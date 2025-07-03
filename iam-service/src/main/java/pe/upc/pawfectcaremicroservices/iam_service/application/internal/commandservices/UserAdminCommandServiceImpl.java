package pe.upc.pawfectcaremicroservices.iam_service.application.internal.commandservices;

import pe.upc.pawfectcaremicroservices.iam_service.application.internal.outboundservices.hashing.HashingService;
import pe.upc.pawfectcaremicroservices.iam_service.application.internal.outboundservices.tokens.TokenService;
import pe.upc.pawfectcaremicroservices.iam_service.domain.model.aggregates.UserAdmin;
import pe.upc.pawfectcaremicroservices.iam_service.domain.model.commands.*;
import pe.upc.pawfectcaremicroservices.iam_service.domain.model.valueobjects.Roles;
import pe.upc.pawfectcaremicroservices.iam_service.domain.model.valueobjects.VeterinarianSpeciality;
import pe.upc.pawfectcaremicroservices.iam_service.domain.services.UserAdminCommandService;
import pe.upc.pawfectcaremicroservices.iam_service.infrastructure.oauth.google.services.GoogleOAuthService;
import pe.upc.pawfectcaremicroservices.iam_service.infrastructure.persistence.jpa.repositories.RoleRepository;
import pe.upc.pawfectcaremicroservices.iam_service.domain.repository.UserAdminRepository;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;

/**
 * User command service implementation
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
     * @param command the sign-in command containing the userName and password
     * @return and optional containing the user matching the userName and the generated token
     * @throws RuntimeException if the user is not found or the password is invalid
     */
    @Override
    public Optional<ImmutablePair<UserAdmin, String>> handle(SignInCommand command) {
        var userAdmin = userAdminRepository.findByUserName(command.userName());
        if (userAdmin.isEmpty())
            throw new RuntimeException("userAdmin not found");
        if (!hashingService.matches(command.password(), userAdmin.get().getPassword()))
            throw new RuntimeException("Invalid password");
        var token = tokenService.generateToken(userAdmin.get().getUserName());
        return Optional.of(ImmutablePair.of(userAdmin.get(), token));
    }

    /**
     * Handle the sign-up command
     * <p>
     *     This method handles the {@link SignUpCommand} command and returns the userAdmin.
     * </p>
     * @param command the sign-up command containing the userName and password
     * @return the created userAdmin
     */
    @Override
    public Optional<UserAdmin> handle(SignUpAdminCommand command) {
        if (userAdminRepository.existsByUserName(command.userName()))
            throw new RuntimeException("Username already exists");

        var UserAdminRoles = roleRepository.findAll().stream()
                .filter(role -> command.role().contains(role.getName().name()))
                .toList();

        var userAdmin = UserAdmin.builder()
                .userName(command.userName())
                .password(hashingService.encode(command.password()))
                .fullName(command.fullName())
                .phoneNumber(command.phoneNumber())
                .email(command.email())
                .dni(command.dni())
                .veterinarianSpeciality(VeterinarianSpeciality.fromValue(command.veterinarianSpeciality()))
                .availableStartTime(command.availableStartTime())
                .availableEndTime(command.availableEndTime())
                .roles(new HashSet<>(UserAdminRoles))
                .build();

        userAdminRepository.saveAdmin(userAdmin);
        return userAdminRepository.findByUserName(command.userName());
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

            // Buscar usuario existente por EMAIL
            var existingUser = userAdminRepository.findByEmail(email);
            UserAdmin userAdmin;

            if (existingUser.isEmpty()) {
                // Crear nuevo usuario con rol por defecto
                var defaultRoles = roleRepository.findAll().stream()
                        .filter(role -> role.getName().name().equals("ROLE_ADMIN"))
                        .toList();

                if (defaultRoles.isEmpty()) {
                    throw new RuntimeException("Default role not found");
                }

                // Generar una contraseña aleatoria (no se usará para login con Google)
                String randomPassword = java.util.UUID.randomUUID().toString();

                userAdmin = UserAdmin.builder()
                        .userName(email)  // Usar el email completo como username
                        .password(hashingService.encode(randomPassword))
                        .fullName(name != null ? name : "Google User")
                        .phoneNumber("N/A")
                        .email(email)
                        .dni("N/A")
                        .veterinarianSpeciality(VeterinarianSpeciality.GENERAL_MEDICINE)
                        .availableStartTime(LocalDateTime.now())
                        .availableEndTime(LocalDateTime.now().plusHours(8))
                        .roles(new HashSet<>(defaultRoles))
                        .build();

                userAdminRepository.saveAdmin(userAdmin);
            } else {
                // Usuario ya existe, usar el existente
                userAdmin = existingUser.get();
            }

            var token = tokenService.generateToken(userAdmin.getUserName());
            return Optional.of(ImmutablePair.of(userAdmin, token));

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
        return Roles.ROLE_ADMIN;
    }
}