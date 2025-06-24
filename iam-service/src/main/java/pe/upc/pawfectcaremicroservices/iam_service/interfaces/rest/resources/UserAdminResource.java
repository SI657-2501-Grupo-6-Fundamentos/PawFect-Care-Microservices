package pe.upc.pawfectcaremicroservices.iam_service.interfaces.rest.resources;

import java.util.List;

public record UserAdminResource(Long id, String username, List<String> roles) {
}
