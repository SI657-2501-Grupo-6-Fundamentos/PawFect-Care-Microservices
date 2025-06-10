package pe.upc.pawfectcaremicroservices.medicalappointment.application.external.pets;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;

@Component
public class ExternalPet {
    /*
    La connexion del PetId (FK) al microservice de Appointment todavia no la hago
    para evitar conflictos al hacer merge */
    private final RestTemplate restTemplate;

    public ExternalPet(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean existsPetById(Long petId) {
        try {
            restTemplate.getForObject("http://localhost:8093/api/v1/pets/{petId}", Object.class, petId);
            return true;
        } catch (HttpClientErrorException.NotFound e) {
            return false; // Pet does not exist
        }
    }
}
