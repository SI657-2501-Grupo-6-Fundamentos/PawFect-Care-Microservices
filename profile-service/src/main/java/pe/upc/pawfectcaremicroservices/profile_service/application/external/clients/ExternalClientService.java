package pe.upc.pawfectcaremicroservices.profile_service.application.external.clients;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ExternalClientService {
    private final RestTemplate restTemplate;

    public ExternalClientService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void registerClient(Object clientData) {
        restTemplate.postForObject("http://localhost:8010/client-service/api/v1/owners", clientData, Object.class);
    }
}