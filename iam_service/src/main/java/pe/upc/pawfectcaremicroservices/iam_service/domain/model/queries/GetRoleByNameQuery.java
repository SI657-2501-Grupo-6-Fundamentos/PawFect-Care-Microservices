package pe.upc.pawfectcaremicroservices.iam_service.domain.model.queries;

import pe.upc.pawfectcaremicroservices.iam_service.domain.model.valueobjects.Roles;

public record GetRoleByNameQuery(Roles name) {
}
