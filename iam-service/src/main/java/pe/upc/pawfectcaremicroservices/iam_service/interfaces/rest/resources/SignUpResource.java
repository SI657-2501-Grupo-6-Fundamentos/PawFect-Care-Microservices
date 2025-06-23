package pe.upc.pawfectcaremicroservices.iam_service.interfaces.rest.resources;

import java.util.List;

public record SignUpResource(String username, String password, List<String> roles) {
}
