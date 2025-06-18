package pe.upc.pawfectcaremicroservices.account_service.domain.services;

import pe.upc.pawfectcaremicroservices.account_service.domain.model.aggregates.User;

public interface UserDomainService {
    boolean isEmailUnique(String email);
    boolean isPasswordValid(String password);
}