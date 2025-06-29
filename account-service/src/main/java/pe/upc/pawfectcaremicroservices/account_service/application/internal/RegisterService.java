package pe.upc.pawfectcaremicroservices.account_service.application.internal;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pe.upc.pawfectcaremicroservices.account_service.domain.model.aggregates.User;
import pe.upc.pawfectcaremicroservices.account_service.domain.model.aggregates.Role;
import pe.upc.pawfectcaremicroservices.account_service.domain.model.aggregates.UserVet;
import pe.upc.pawfectcaremicroservices.account_service.domain.model.valueobjects.RoleName;
import pe.upc.pawfectcaremicroservices.account_service.domain.model.valueobjects.VeterinarianSpeciality;
import pe.upc.pawfectcaremicroservices.account_service.domain.repository.UserRepository;
import pe.upc.pawfectcaremicroservices.account_service.domain.repository.UserVetRepository;
import pe.upc.pawfectcaremicroservices.account_service.infrastructure.persistence.jpa.repositories.JpaRoleRepository;
import pe.upc.pawfectcaremicroservices.account_service.interfaces.rest.resources.RegisterRequest;
import pe.upc.pawfectcaremicroservices.account_service.interfaces.rest.resources.RegisterVetRequest;

import java.util.Set;

@Service
public class RegisterService {
    private final UserRepository userRepository;
    private final JpaRoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    private final UserVetRepository userVetRepository;

    public RegisterService(UserRepository userRepository, UserVetRepository userVetRepository, JpaRoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;

        this.userVetRepository = userVetRepository;
    }
    public User register(RegisterRequest request) {
        if (userRepository.existsByUserName(request.getUserName())) {
            throw new IllegalArgumentException("Usuario ya registrado");
        }

        if (request.getPassword().length() < 6) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 6 caracteres");
        }

        RoleName enumRole;
        try {
            enumRole = RoleName.valueOf(request.getRole().toUpperCase());
        } catch (Exception e) {
            throw new IllegalArgumentException("Rol no válido");
        }

        Role role = roleRepository.findByName(enumRole)
                .orElseThrow(() -> new IllegalArgumentException("Rol no válido"));

        User user = User.builder()
                .userName(request.getUserName())
                .password(passwordEncoder.encode(request.getPassword()))
                .fullName(request.getFullName())
                .phoneNumber(request.getPhoneNumber())
                .email(request.getEmail())
                .address(request.getAddress()) // <- ¡MUY IMPORTANTE!
                .roles(Set.of(role))
                .build();

        return userRepository.save(user);
    }


    public UserVet registerVet(RegisterVetRequest request) {
        if (userVetRepository.existsByUserName(request.getUserName())) {
            throw new IllegalArgumentException("Usuario veterinario ya registrado");
        }

        if (request.getPassword().length() < 6) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 6 caracteres");
        }

        RoleName enumRole;
        try {
            enumRole = RoleName.valueOf(request.getRole().toUpperCase());
        } catch (Exception e) {
            throw new IllegalArgumentException("Rol no válido");
        }

        Role role = roleRepository.findByName(enumRole)
                .orElseThrow(() -> new IllegalArgumentException("Rol no válido"));

        UserVet userVet = UserVet.builder()
                .userName(request.getUserName())
                .password(passwordEncoder.encode(request.getPassword()))
                .fullName(request.getFullName())
                .phoneNumber(request.getPhoneNumber())
                .roles(Set.of(role))
                .email(request.getEmail())
                .dni(request.getDni())
                .veterinarianSpeciality(VeterinarianSpeciality.fromValue(request.getVeterinarianSpeciality()))
                .availableStartTime(request.getAvailableStartTime())
                .availableEndTime(request.getAvailableEndTime())
                .build();

        return userVetRepository.saveVet(userVet);
    }
}