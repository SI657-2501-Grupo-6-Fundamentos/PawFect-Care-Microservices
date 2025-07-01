package pe.upc.pawfectcaremicroservices.iam_service.interfaces.rest.resources;

import java.util.List;

public record UserAdminResource(String username, List<String> roles) {

}
