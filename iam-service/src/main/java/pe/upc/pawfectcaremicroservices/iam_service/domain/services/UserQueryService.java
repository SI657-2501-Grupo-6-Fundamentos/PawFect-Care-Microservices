package pe.upc.pawfectcaremicroservices.iam_service.domain.services;

import org.apache.commons.lang3.tuple.ImmutablePair;
import pe.upc.pawfectcaremicroservices.iam_service.domain.model.aggregates.User;
import pe.upc.pawfectcaremicroservices.iam_service.domain.model.aggregates.UserAdmin;
import pe.upc.pawfectcaremicroservices.iam_service.domain.model.commands.GoogleCallbackCommand;
import pe.upc.pawfectcaremicroservices.iam_service.domain.model.commands.GoogleSignInCommand;
import pe.upc.pawfectcaremicroservices.iam_service.domain.model.commands.SignInCommand;
import pe.upc.pawfectcaremicroservices.iam_service.domain.model.commands.SignUpCommand;
import pe.upc.pawfectcaremicroservices.iam_service.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface UserQueryService {
    List<User> handle(GetAllUsersQuery query);
    Optional<User> handle(GetUserByIdQuery query);
    Optional<User> handle(GetUserByUserNameQuery query);
}