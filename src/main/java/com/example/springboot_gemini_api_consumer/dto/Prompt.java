package com.example.springboot_gemini_api_consumer.dto;




import java.util.List;

public class Prompt {
    List<Contents> contents;


    public List<Contents> getContents() {

        return contents;
    }

    public void setContents(List<Contents> contents) {

        this.contents = contents;
    }
}
