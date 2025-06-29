package pe.upc.pawfectcaremicroservices.account_service.interfaces.rest.transform;

import pe.upc.pawfectcaremicroservices.account_service.domain.model.aggregates.UserVet;

import java.util.stream.Collectors;

public class UserVetResourceAssembler {
    public static UserVetResource toResource(UserVet userVet) {
        UserVetResource resource = new UserVetResource();
        resource.setId(userVet.getId());
        resource.setUserName(userVet.getUserName());
        resource.setFullName(userVet.getFullName());
        resource.setPhoneNumber(userVet.getPhoneNumber());
        resource.setEmail(userVet.getEmail());
        resource.setRoles(
                userVet.getRoles().stream()
                        .map(r -> r.getName().name())
                        .collect(Collectors.toSet())
        );
        resource.setDni(userVet.getDni());
        resource.setVeterinarianSpeciality(userVet.getVeterinarianSpeciality().getValue());
        resource.setAvailableStartTime(userVet.getAvailableStartTime().toString());
        resource.setAvailableEndTime(userVet.getAvailableEndTime().toString());

        return resource;
    }
}
