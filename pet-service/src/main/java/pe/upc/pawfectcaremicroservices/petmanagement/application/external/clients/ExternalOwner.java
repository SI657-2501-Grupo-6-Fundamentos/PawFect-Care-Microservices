package pe.upc.pawfectcaremicroservices.petmanagement.application.external.clients;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;

@Component
public class ExternalOwner {
    private final RestTemplate restTemplate;

    public ExternalOwner(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean existsOwnerById(Long ownerId) {
        try {
            restTemplate.getForObject("http://localhost:8010/pet-owner-service/api/v1/owners/{ownerId}", Object.class, ownerId);
            return true;
        } catch (HttpClientErrorException e) {
            System.err.println("HTTP Error: " + e.getStatusCode());
            return false;
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            return false;
        }
    }
}
