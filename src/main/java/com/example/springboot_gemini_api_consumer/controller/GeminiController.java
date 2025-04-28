package com.example.springboot_gemini_api_consumer.controller;

import com.example.springboot_gemini_api_consumer.service.GeminiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gemini")
public class GeminiController {

    @Autowired
    private GeminiService geminiService;
    public GeminiController(GeminiService geminiService)
    {
        this.geminiService = geminiService;
    }

    @PostMapping("/consulta")
    public String  ConsultGemini(@RequestBody String prompt)
    {
        return geminiService.generateAnswers(prompt);
    }
}
