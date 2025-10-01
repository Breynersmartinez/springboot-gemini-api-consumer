package com.example.springboot_gemini_api_consumer.controller;

import com.example.springboot_gemini_api_consumer.dto.Contents;
import com.example.springboot_gemini_api_consumer.dto.Parts;
import com.example.springboot_gemini_api_consumer.dto.Prompt;
import com.example.springboot_gemini_api_consumer.service.AppService;
import com.example.springboot_gemini_api_consumer.service.GeminiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")

public class ApiController {

    @Autowired
    private final AppService appService;
    public ApiController(AppService appService)
    {
        this.appService = appService;
    }

    @PostMapping("/get-result-BreinLogic")
    ResponseEntity<Object> getResponseFromGemini(@RequestBody Parts parts){

        Prompt prompt = new Prompt();
        Contents contents = new Contents();

        List<Contents> contentsList = new ArrayList<>();
        List<Parts> partsList = new ArrayList<>();


        /* CONTEXTOS DE EJEMPLO PARA QUE EL BOT RESPONDA SEGUN EL CONTEXTO
        * asistente especializado en backend:  "Eres un asistente especializado en backend. Responde siempre en español, con ejemplos en Java o Spring Boot, de forma clara y breve."
        *
        * Asistente jurídico especializado en las leyes de Colombia:
        * """
Eres un asistente jurídico especializado en las leyes de Colombia.
Responde siempre en español, de manera clara, concisa y didáctica.
Cuando cites leyes, menciona la norma correspondiente (ej: Código Civil Colombiano, Ley 100 de 1993, Constitución Política de 1991, etc.).
Aclara que la respuesta es de carácter informativo y no sustituye asesoría legal profesional.
"""
*
*
*  Eres un asistente super amable, empático y con un estilo cercano.
    Siempre respondes en español.
    Puedes hablar de temas generales (cultura, tecnología, vida diaria, etc.)
    pero también eres muy bueno explicando programación y backend con ejemplos en Java o Spring Boot.
    Si el usuario te pregunta cómo te llamas, responde siempre "Me llamo BreinLogic".
        * */

        //contexto que se manejara para las respuestas
        Parts contextParts= new Parts();
        // CONTEXT
        contextParts.setText(" ");
        partsList.add(contextParts);


        // Mensaje del usuario
        partsList.add(parts);



        contents.setParts(partsList);
        contentsList.add(contents);
        prompt.setContents(contentsList);



        return new ResponseEntity<>(appService.getResult(prompt, parts), HttpStatus.OK);
    }
}
