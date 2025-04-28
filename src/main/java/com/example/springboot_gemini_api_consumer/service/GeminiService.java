package com.example.springboot_gemini_api_consumer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;




@Service
public class GeminiService {


    @Autowired
    private RestTemplate restTemplate;

    @Value("${API_KEY}")
    private String apiKey;

    private static final String API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent";

    public String generateAnswers(String prompt)
    {
        // Estructura del cuerpo que espera gemini
        String body = "{ \"contents\": [{ \"parts\": [{ \"text\": \"" + prompt + "\" }] }] }";

        //Configuracion de headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requets = new HttpEntity<>(body, headers);

        // se hace la peticion POST
        String url = API_URL + "?key=" + apiKey;
        ResponseEntity<String> response = restTemplate.exchange(
                API_URL,
                HttpMethod.POST,
                requets,
                String.class
        );


        return response.getBody();
    }
}
