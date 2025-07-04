package pe.upc.pawfectcaremicroservices.iam_service.domain.services;

import pe.upc.pawfectcaremicroservices.iam_service.domain.model.aggregates.UserAdmin;
import pe.upc.pawfectcaremicroservices.iam_service.domain.model.queries.GetAllUsersAdminQuery;
import pe.upc.pawfectcaremicroservices.iam_service.domain.model.queries.GetUserAdminByIdQuery;
import pe.upc.pawfectcaremicroservices.iam_service.domain.model.queries.GetUserAdminByUserNameQuery;

import java.util.List;
import java.util.Optional;

public interface UserAdminQueryService {
    List<UserAdmin> handle(GetAllUsersAdminQuery query);
    Optional<UserAdmin> handle(GetUserAdminByIdQuery query);
    Optional<UserAdmin> handle(GetUserAdminByUserNameQuery query);

}
