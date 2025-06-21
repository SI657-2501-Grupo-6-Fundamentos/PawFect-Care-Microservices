package pe.upc.pawfectcaremicroservices.profile_service.application.internal.commandservices;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.upc.pawfectcaremicroservices.profile_service.application.external.clients.ExternalClientService;
import pe.upc.pawfectcaremicroservices.profile_service.application.external.clients.ExternalVeterinaryService;
import pe.upc.pawfectcaremicroservices.profile_service.domain.model.aggregates.Profile;
import pe.upc.pawfectcaremicroservices.profile_service.domain.model.commands.CreateProfileCommand;
import pe.upc.pawfectcaremicroservices.profile_service.domain.model.valueobjects.ProfileType;
import pe.upc.pawfectcaremicroservices.profile_service.domain.services.ProfileCommandService;
import pe.upc.pawfectcaremicroservices.profile_service.infrastructure.persistence.jpa.repositories.ProfileRepository;

import java.util.Optional;

@Service
public class ProfileCommandServiceImpl implements ProfileCommandService {

    private final ProfileRepository profileRepository;
    private final ExternalClientService externalClientService;
    private final ExternalVeterinaryService externalVeterinaryService;

    public ProfileCommandServiceImpl(ProfileRepository profileRepository,
                                     ExternalClientService externalClientService,
                                     ExternalVeterinaryService externalVeterinaryService) {
        this.profileRepository = profileRepository;
        this.externalClientService = externalClientService;
        this.externalVeterinaryService = externalVeterinaryService;
    }

    @Override
    @Transactional
    public Optional<Profile> handle(CreateProfileCommand command) {
        // 1. Guardar perfil localmente
        Profile profile = new Profile(command.iamUserId(), command.type());
        profileRepository.save(profile);

        // 2. Llamar al microservicio correspondiente
        if (command.type() == ProfileType.USER) {
            externalClientService.registerClient(command.profileData());
        } else if (command.type() == ProfileType.ADMIN) {
            externalVeterinaryService.registerVeterinarian(command.profileData());
        }

        return Optional.of(profile);
    }
}