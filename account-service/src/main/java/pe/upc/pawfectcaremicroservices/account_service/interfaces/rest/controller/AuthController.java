package pe.upc.pawfectcaremicroservices.account_service.interfaces.rest.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.upc.pawfectcaremicroservices.account_service.application.internal.AuthService;
import pe.upc.pawfectcaremicroservices.account_service.interfaces.rest.resources.LoginRequest;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        String token = authService.login(request.getEmail(), request.getPassword());
        return ResponseEntity.ok().body(token);
    }
}