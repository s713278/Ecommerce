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

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/dummy")
@Slf4j
public class RemoteController {

	private static String BASE_URI = "https://dummyjson.com";

	@Autowired
	private RestTemplate restTemplate;

	private static String PRODUCTS = "/products";

	@GetMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getExternalProducts() {
		log.debug("Getting PRoducts from {}", BASE_URI + PRODUCTS);
		ResponseEntity<Object> objectsMap = restTemplate.getForEntity(BASE_URI + PRODUCTS, Object.class);
		Map objects = (LinkedHashMap) objectsMap.getBody();
		
		objects.keySet().forEach(t ->log.debug("{}",t) );
		
		List<Object> list=(List<Object>) objects.get("products");
		
		ObjectMapper objectMapper=new ObjectMapper();
		
		List<ProductDTO> products= 
			list.stream()
			.map(obj->objectMapper.convertValue(obj, ProductDTO.class))
			.collect(Collectors.toList());
		
		return new ResponseEntity<Object>(products, HttpStatus.OK);

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
