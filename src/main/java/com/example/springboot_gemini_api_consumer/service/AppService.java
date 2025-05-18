package com.example.springboot_gemini_api_consumer.service;

import com.example.springboot_gemini_api_consumer.dto.Parts;
import com.example.springboot_gemini_api_consumer.dto.Prompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AppService {
    @Autowired
    GeminiService geminiService;

    public ResponseEntity<Object> getResult(Prompt prompt, Parts parts){
        Object response = geminiService.callGeminiAPI(prompt);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
