package com.example.springboot_gemini_api_consumer.service;


import com.example.springboot_gemini_api_consumer.dto.Prompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.swing.text.html.parser.Entity;
import java.net.http.HttpHeaders;

@Service
public class GeminiService {

    @Value("${gemini.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate;

    public GeminiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Object callGeminiAPI(Prompt prompt){

        HttpEntity<Prompt> requestEntity = new HttpEntity<>(prompt);

        ResponseEntity<Object> response = restTemplate.exchange(
                apiUrl,
                HttpMethod.POST,
                requestEntity,
                Object.class
        );
        return response.getBody();
    }
}
