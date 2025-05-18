package com.example.springboot_gemini_api_consumer.dto;

import lombok.Data;

import java.util.List;

@Data
public class Prompt {
    List<Contents> contents;

}
