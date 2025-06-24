package pe.upc.pawfectcaremicroservices.medicalappointment.application.external.pets;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;

@Component
public class ExternalPet {
    private final RestTemplate restTemplate;

    public ExternalPet(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean existsPetById(Long petId) {
        try {
            restTemplate.getForObject("http://localhost:8010/pet-service/api/v1/pets/{petId}", Object.class, petId);
            return true;
        } catch (HttpClientErrorException.NotFound e) {
            return false; // Pet does not exist
        }
    }
}
