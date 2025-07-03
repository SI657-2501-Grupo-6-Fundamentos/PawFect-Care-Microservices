package pe.upc.pawfectcaremicroservices.iam_service.interfaces.rest.resources;

public record SignUpResource(String username, String password, String role, String fullName, String phoneNumber, String email, String address) {

}
