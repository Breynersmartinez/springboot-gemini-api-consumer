package com.example.springboot_gemini_api_consumer.dto;




import java.util.List;


public class Contents {
    List<Parts> parts;


    public List<Parts> getParts() {
        return parts;
    }

    public void setParts(List<Parts> parts) {
        this.parts = parts;
    }
}
