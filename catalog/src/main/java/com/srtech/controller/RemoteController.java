package com.srtech.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.srtech.dto.ProductDTO;
import com.srtech.service.IntegrationService;
import com.srtech.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/dummy")
@Slf4j
public class RemoteController {

	private static String BASE_URI = "https://dummyjson.com";

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private IntegrationService integrationService;

	private static String PRODUCTS = "/products";

	@GetMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> getExternalProducts() {
		log.debug("Getting Products from {}", BASE_URI + PRODUCTS);
		return integrationService.createDataFromAPI();

	}

	@GetMapping(value = "/products/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getExternalProductById(@PathVariable String id) {
		log.debug("Getting PRoducts from {}", BASE_URI + PRODUCTS);
		Map<String, String> params = new HashMap<>();
		params.put("id", id);
		Object data = restTemplate.getForEntity(BASE_URI + PRODUCTS, Object.class, params);
		return new ResponseEntity<Object>(data, HttpStatus.OK);
	}

}
