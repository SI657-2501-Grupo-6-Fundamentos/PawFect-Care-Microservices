package pe.upc.pawfectcaremicroservices.account_service.application.internal;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pe.upc.pawfectcaremicroservices.account_service.domain.model.aggregates.User;
import pe.upc.pawfectcaremicroservices.account_service.domain.repository.UserRepository;
import pe.upc.pawfectcaremicroservices.account_service.infrastructure.security.JwtUtil;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public String login(String userName, String password) {
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("Contraseña incorrecta");
        }
        return jwtUtil.generateToken(userName);
    }
}