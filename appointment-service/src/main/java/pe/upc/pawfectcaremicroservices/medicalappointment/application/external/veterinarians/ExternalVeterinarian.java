package pe.upc.pawfectcaremicroservices.medicalappointment.application.external.veterinarians;

import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class ExternalVeterinarian {
    private final RestTemplate restTemplate;

    public ExternalVeterinarian(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean existsVeterinarianById(Long veterinarianId) {
        try {
            restTemplate.getForObject("http://localhost:8010/veterinarian-service/api/v1/veterinarians/{veterinarianId}", Object.class, veterinarianId);
            return true;
        } catch (HttpClientErrorException.NotFound e) {
            return false;
        }
    }
}
