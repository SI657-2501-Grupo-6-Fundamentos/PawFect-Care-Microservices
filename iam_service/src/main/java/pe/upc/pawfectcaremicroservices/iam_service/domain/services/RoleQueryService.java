package pe.upc.pawfectcaremicroservices.iam_service.domain.services;

import pe.upc.pawfectcaremicroservices.iam_service.domain.model.entities.Role;
import pe.upc.pawfectcaremicroservices.iam_service.domain.model.queries.GetAllRolesQuery;
import pe.upc.pawfectcaremicroservices.iam_service.domain.model.queries.GetRoleByNameQuery;

import java.util.List;
import java.util.Optional;

public interface RoleQueryService {
    List<Role> handle(GetAllRolesQuery query);
    Optional<Role> handle(GetRoleByNameQuery query);
}
