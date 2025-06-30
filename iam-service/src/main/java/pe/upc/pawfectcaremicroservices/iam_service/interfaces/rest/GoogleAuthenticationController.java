package pe.upc.pawfectcaremicroservices.iam_service.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.upc.pawfectcaremicroservices.iam_service.domain.model.queries.GetUserAdminByUsernameQuery;
import pe.upc.pawfectcaremicroservices.iam_service.domain.services.UserAdminCommandService;
import pe.upc.pawfectcaremicroservices.iam_service.domain.services.UserAdminQueryService;
import pe.upc.pawfectcaremicroservices.iam_service.infrastructure.tokens.jwt.BearerTokenService;
import pe.upc.pawfectcaremicroservices.iam_service.interfaces.rest.resources.GoogleSignInResource;
import pe.upc.pawfectcaremicroservices.iam_service.interfaces.rest.resources.GoogleAuthenticatedUserResource;
import pe.upc.pawfectcaremicroservices.iam_service.interfaces.rest.transform.GoogleSignInCommandFromResourceAssembler;

@RestController
@RequestMapping(value = "/api/v1/auth/google")
@Tag(name = "Google Authentication", description = "Google Authentication Endpoints")
public class GoogleAuthenticationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GoogleAuthenticationController.class);

    private final UserAdminCommandService userAdminCommandService;
    private final BearerTokenService tokenService;
    private final UserAdminQueryService userAdminQueryService;

    public GoogleAuthenticationController(
            UserAdminCommandService userAdminCommandService,
            BearerTokenService tokenService,
            UserAdminQueryService userAdminQueryService) {
        this.userAdminCommandService = userAdminCommandService;
        this.tokenService = tokenService;
        this.userAdminQueryService = userAdminQueryService;
    }

    /**
     * Sign in with Google OAuth
     * @param resource Google sign-in resource containing the Google ID token
     * @return ResponseEntity with authentication result
     */
    @PostMapping("/sign-in")
    @Operation(summary = "Sign in with Google", description = "Authenticate user using Google OAuth ID token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Authentication successful"),
            @ApiResponse(responseCode = "400", description = "Invalid Google token or authentication failed"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<?> signInWithGoogle(@RequestBody GoogleSignInResource resource) {
        try {
            LOGGER.info("Processing Google sign-in request");

            // Convert resource to command
            var command = GoogleSignInCommandFromResourceAssembler.toCommandFromResource(resource);

            // Execute command
            var result = userAdminCommandService.handle(command);

            if (result.isEmpty()) {
                LOGGER.warn("Google authentication failed - empty result");
                return ResponseEntity.badRequest()
                        .body("Google authentication failed");
            }

            // Extract user and token from result
            var user = result.get().getLeft();
            var token = result.get().getRight();

            // Create response resource
            var authenticatedUser = new GoogleAuthenticatedUserResource(
                    user.getId(),
                    user.getUsername(),
                    token
            );

            LOGGER.info("Google authentication successful for user: {}", user.getUsername());
            return ResponseEntity.ok(authenticatedUser);

        } catch (IllegalArgumentException e) {
            LOGGER.error("Invalid request data: {}", e.getMessage());
            return ResponseEntity.badRequest()
                    .body("Invalid request: " + e.getMessage());

        } catch (Exception e) {
            LOGGER.error("Google authentication error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Authentication failed: " + e.getMessage());
        }
    }

    /*
    @GetMapping("/me")
    @Operation(summary = "Get authenticated user's info", description = "Returns info about the authenticated user using the JWT token")
    public ResponseEntity<?> getAuthenticatedUser(@RequestHeader("Authorization") String authHeader) {
        try {
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Missing or invalid Authorization header");
            }

            String token = authHeader.substring(7);
            String username = tokenService.getUsernameFromToken(token);

            var userOpt = userAdminQueryService.handle(new GetUserAdminByUsernameQuery(username));
            if (userOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }

            var user = userOpt.get();
            return ResponseEntity.ok(new GoogleAuthenticatedUserResource(
                    user.getId(),
                    user.getUsername(),
                    token
            ));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to parse token: " + e.getMessage());
        }
    }*/

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Google Auth service is running");
    }
}