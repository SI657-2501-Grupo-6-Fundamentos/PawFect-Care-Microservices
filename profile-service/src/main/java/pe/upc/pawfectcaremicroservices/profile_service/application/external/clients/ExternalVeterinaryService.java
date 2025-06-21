package pe.upc.pawfectcaremicroservices.profile_service.application.external.clients;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ExternalVeterinaryService {
    private final RestTemplate restTemplate;

    public ExternalVeterinaryService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void registerVeterinarian(Object vetData) {
        restTemplate.postForObject("http://localhost:8010/veterinarian-service/api/v1/veterinarians", vetData, Object.class);
    }
}