package pe.upc.pawfectcaremicroservices.account_service.interfaces.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.upc.pawfectcaremicroservices.account_service.application.internal.RegisterService;
import pe.upc.pawfectcaremicroservices.account_service.interfaces.rest.resources.RegisterRequest;
import pe.upc.pawfectcaremicroservices.account_service.interfaces.rest.transform.UserResourceAssembler;

@RestController
@RequestMapping("/api/auth")
public class RegisterController {
    private final RegisterService registerService;

    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        var user = registerService.register(request.getEmail(), request.getPassword(), request.getRole());
        return ResponseEntity.ok(UserResourceAssembler.toResource(user));
    }
}