package com.example.demo.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import static com.example.demo.config.Constants.devProperties.temp_auth_token;

@Component
public class KeyclockAuthProvider {

    @Value("${auth.keycloak.url}")
    private String authService;
    private RestTemplate restTemplate = new RestTemplate();

    private Logger logger = LoggerFactory.getLogger(getClass());

    public String registerKeycloakUser(){
        // External API call to Keycloak user creation.
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(temp_auth_token);
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(authService);

        logger.info("KEY CLOAK USER RETRIEVAL API CALL: {}",uriBuilder.toUriString());

        ResponseEntity<String> response = restTemplate.exchange(uriBuilder.toUriString(),
                HttpMethod.GET, requestEntity, String.class);
        return response.toString();
    }
}
