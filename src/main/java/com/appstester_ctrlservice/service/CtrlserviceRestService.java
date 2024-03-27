package com.appstester_ctrlservice.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class CtrlserviceRestService {

    RestTemplate restTemplate = new RestTemplate();
    Logger ctrlserviceRestServiceLogger = Logger.getLogger("CtrlserviceRestService");

    public String requestCommserviceToMoodle() {
        HttpEntity<String> requestEntity = new HttpEntity<>(new HttpHeaders());
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:8080/requesting", HttpMethod.GET, requestEntity, String.class);
        ctrlserviceRestServiceLogger.log(Level.INFO, "Connect Communication Service");
        return response.getBody();
    }

    public String requestCommserviceGetAttempts(String moodleResponse) {
        HttpEntity<String> requestEntity = new HttpEntity<>(moodleResponse, new HttpHeaders());
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:8080/processing", HttpMethod.GET, requestEntity, String.class);
        ctrlserviceRestServiceLogger.log(Level.INFO, "Request Communication Service to get attempts from Moodle");
        return response.getBody();
    }
}
