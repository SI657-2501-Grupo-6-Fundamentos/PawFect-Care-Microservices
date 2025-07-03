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
import pe.upc.pawfectcaremicroservices.iam_service.domain.services.UserAdminCommandService;
import pe.upc.pawfectcaremicroservices.iam_service.domain.services.UserCommandService;
import pe.upc.pawfectcaremicroservices.iam_service.interfaces.rest.resources.GoogleSignInResource;
import pe.upc.pawfectcaremicroservices.iam_service.interfaces.rest.resources.GoogleAuthenticatedUserResource;
import pe.upc.pawfectcaremicroservices.iam_service.interfaces.rest.transform.GoogleSignInCommandFromResourceAssembler;

@RestController
@RequestMapping(value = "/api/v1/auth/google")
@Tag(name = "Google Authentication", description = "Google Authentication Endpoints")
public class GoogleAuthenticationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GoogleAuthenticationController.class);

    private final UserAdminCommandService userAdminCommandService;
    private final UserCommandService userCommandService;

    public GoogleAuthenticationController(
            UserAdminCommandService userAdminCommandService,
            UserCommandService userCommandService) {
        this.userAdminCommandService = userAdminCommandService;
        this.userCommandService = userCommandService;
    }

    /**
     * Sign in with Google OAuth
     * @param resource Google sign-in resource containing the Google ID token
     * @return ResponseEntity with authentication result
     */
    @PostMapping("/sign-in-user-admin")
    @Operation(summary = "Sign in with Google", description = "Authenticate user using Google OAuth ID token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Authentication successful"),
            @ApiResponse(responseCode = "400", description = "Invalid Google token or authentication failed"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<?> signInUserAdminWithGoogle(@RequestBody GoogleSignInResource resource) {
        try {
            LOGGER.info("Processing Google User Admin sign-in request");

            // Convert resource to command
            var command = GoogleSignInCommandFromResourceAssembler.toCommandFromResource(resource);

            // Execute command
            var result = userAdminCommandService.handle(command);

            if (result.isEmpty()) {
                LOGGER.warn("Google authentication for user admin failed - empty result");
                return ResponseEntity.badRequest()
                        .body("Google authentication failed");
            }

            // Extract user and token from result
            var user = result.get().getLeft();
            var token = result.get().getRight();

            // Create response resource
            var authenticatedUser = new GoogleAuthenticatedUserResource(
                    user.getId(),
                    user.getUserName(),
                    token
            );

            LOGGER.info("Google authentication successful for user admin: {}", user.getUserName());
            return ResponseEntity.ok(authenticatedUser);

        } catch (IllegalArgumentException e) {
            LOGGER.error("Invalid request data for user admin: {}", e.getMessage());
            return ResponseEntity.badRequest()
                    .body("Invalid request: " + e.getMessage());

        } catch (Exception e) {
            LOGGER.error("Google authentication error for user admin: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Authentication failed: " + e.getMessage());
        }
    }

    /**
     * Sign in with Google OAuth
     * @param resource Google sign-in resource containing the Google ID token
     * @return ResponseEntity with authentication result
     */
    @PostMapping("/sign-in-user")
    @Operation(summary = "Sign in with Google", description = "Authenticate user using Google OAuth ID token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Authentication successful"),
            @ApiResponse(responseCode = "400", description = "Invalid Google token or authentication failed"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<?> signInUserWithGoogle(@RequestBody GoogleSignInResource resource) {
        try {
            LOGGER.info("Processing Google User sign-in request");

            // Convert resource to command
            var command = GoogleSignInCommandFromResourceAssembler.toCommandFromResource(resource);

            // Execute command
            var result = userCommandService.handle(command);

            if (result.isEmpty()) {
                LOGGER.warn("Google authentication for user failed - empty result");
                return ResponseEntity.badRequest()
                        .body("Google authentication failed");
            }

            // Extract user and token from result
            var user = result.get().getLeft();
            var token = result.get().getRight();

            // Create response resource
            var authenticatedUser = new GoogleAuthenticatedUserResource(
                    user.getId(),
                    user.getUserName(),
                    token
            );

            LOGGER.info("Google authentication successful for user: {}", user.getUserName());
            return ResponseEntity.ok(authenticatedUser);

        } catch (IllegalArgumentException e) {
            LOGGER.error("Invalid request data for user: {}", e.getMessage());
            return ResponseEntity.badRequest()
                    .body("Invalid request for user: " + e.getMessage());

        } catch (Exception e) {
            LOGGER.error("Google authentication error for user: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Authentication failed for user: " + e.getMessage());
        }
    }

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Google Auth service is running");
    }
}