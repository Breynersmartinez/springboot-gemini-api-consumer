package com.example.springboot_gemini_api_consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SpringbootGeminiApiConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootGeminiApiConsumerApplication.class, args);

	}

	@Bean
	public RestTemplate restTemplate()
	{
		return new RestTemplate();
	}

}
