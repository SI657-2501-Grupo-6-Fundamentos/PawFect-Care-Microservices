package pe.upc.pawfectcaremicroservices.account_service.interfaces.rest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.upc.pawfectcaremicroservices.account_service.application.internal.AuthService;
import pe.upc.pawfectcaremicroservices.account_service.interfaces.rest.resources.LoginRequest;
import pe.upc.pawfectcaremicroservices.account_service.interfaces.rest.resources.JwtResponse;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest request) {
        String token = authService.login(request.getUserName(), request.getPassword());
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("/login-vet")
    public ResponseEntity<JwtResponse> loginVet(@RequestBody LoginRequest request) {
        String token = authService.loginVet(request.getUserName(), request.getPassword());
        return ResponseEntity.ok(new JwtResponse(token));
    }
}