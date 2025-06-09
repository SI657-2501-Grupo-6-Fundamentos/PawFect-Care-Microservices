package pe.upc.pawfectcaremicroservices.account_service.domain.services;

import org.springframework.stereotype.Service;
import pe.upc.pawfectcaremicroservices.account_service.domain.repository.UserRepository;

@Service
public class UserDomainServiceImpl implements UserDomainService {
    private final UserRepository userRepository;

    public UserDomainServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isEmailUnique(String email) {
        return !userRepository.existsByEmail(email);
    }

    @Override
    public boolean isPasswordValid(String password) {
        return password != null && password.length() >= 6;
    }
}