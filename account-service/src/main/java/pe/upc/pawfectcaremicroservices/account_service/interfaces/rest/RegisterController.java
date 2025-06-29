package pe.upc.pawfectcaremicroservices.account_service.interfaces.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.upc.pawfectcaremicroservices.account_service.application.internal.RegisterService;
import pe.upc.pawfectcaremicroservices.account_service.interfaces.rest.resources.RegisterRequest;
import pe.upc.pawfectcaremicroservices.account_service.interfaces.rest.resources.RegisterVetRequest;
import pe.upc.pawfectcaremicroservices.account_service.interfaces.rest.transform.UserResourceAssembler;
import pe.upc.pawfectcaremicroservices.account_service.interfaces.rest.transform.UserVetResourceAssembler;


@RestController
@RequestMapping("/api/auth")
public class RegisterController {
    private final RegisterService registerService;

    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        var user = registerService.register(request);
        return ResponseEntity.ok(UserResourceAssembler.toResource(user));
    }

    @PostMapping("/register-vet")
    public ResponseEntity<?> registerVet(@RequestBody RegisterVetRequest request) {
        var userVet = registerService.registerVet(request);
        return ResponseEntity.ok(UserVetResourceAssembler.toResource(userVet));
    }
}