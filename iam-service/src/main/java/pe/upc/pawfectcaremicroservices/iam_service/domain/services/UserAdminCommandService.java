package pe.upc.pawfectcaremicroservices.iam_service.domain.services;

import pe.upc.pawfectcaremicroservices.iam_service.domain.model.aggregates.UserAdmin;
import pe.upc.pawfectcaremicroservices.iam_service.domain.model.commands.*;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Optional;

public interface UserAdminCommandService {
    Optional<ImmutablePair<UserAdmin, String>> handle(SignInCommand command);
    Optional<UserAdmin> handle(SignUpAdminCommand command);
    Optional<ImmutablePair<UserAdmin, String>> handle(GoogleSignInCommand command);
    Optional<ImmutablePair<UserAdmin, String>> handle(GoogleCallbackCommand command);
}
