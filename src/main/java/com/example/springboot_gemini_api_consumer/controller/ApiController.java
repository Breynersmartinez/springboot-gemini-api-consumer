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
@CrossOrigin("http://localhost:3000")
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

        partsList.add(parts);

        contents.setParts(partsList);

        contentsList.add(contents);
        prompt.setContents(contentsList);


        return new ResponseEntity<>(appService.getResult(prompt, parts), HttpStatus.OK);
    }
}
