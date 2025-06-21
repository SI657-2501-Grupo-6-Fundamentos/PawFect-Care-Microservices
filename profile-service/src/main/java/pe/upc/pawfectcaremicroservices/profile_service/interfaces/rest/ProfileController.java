package pe.upc.pawfectcaremicroservices.profile_service.interfaces.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.upc.pawfectcaremicroservices.profile_service.domain.services.ProfileCommandService;
import pe.upc.pawfectcaremicroservices.profile_service.interfaces.rest.resources.CreateProfileResource;
import pe.upc.pawfectcaremicroservices.profile_service.interfaces.rest.transform.CreateProfileCommandFromResourceAssembler;

@RestController
@RequestMapping("/api/v1/profiles")
public class ProfileController {

    private final ProfileCommandService profileCommandService;

    public ProfileController(ProfileCommandService profileCommandService) {
        this.profileCommandService = profileCommandService;
    }

    @PostMapping
    public ResponseEntity<?> createProfile(@RequestBody CreateProfileResource resource) {
        var command = CreateProfileCommandFromResourceAssembler.toCommandFromResource(resource);
        var profile = profileCommandService.handle(command);
        if (profile.isEmpty()) return ResponseEntity.badRequest().build();
        return new ResponseEntity<>(profile.get(), HttpStatus.CREATED);
    }
}