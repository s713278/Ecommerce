package com.srtech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class CartApplication {

	public static void main(String[] args) {
		SpringApplication.run(CartApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		log.debug("########################## RestTemplate Instantiated .....");
		return new RestTemplate();
	}
	
	@Bean
	public ObjectMapper objectMapper() {
		log.debug("########################## ObjectMapper Instantiated .....");
		return new ObjectMapper();
	}
}
