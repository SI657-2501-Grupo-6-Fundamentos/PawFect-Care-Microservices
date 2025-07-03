package pe.upc.pawfectcaremicroservices.iam_service.domain.model.commands;

public record SignInCommand(String userName, String password) {
}
