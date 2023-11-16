package com.srtech.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.srtech.dto.ProductDTO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class IntegrationService {
	
	private static String BASE_URI = "https://dummyjson.com";
	private static String PRODUCTS = "/products";
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private ProductService productService;

	public ResponseEntity<Void> createDataFromAPI() {
		log.debug("Getting Products from {}", BASE_URI + PRODUCTS);
		if(productService.count()>0) {
			throw new RuntimeException("Already products are inserted/updated in database.");
		}
		//Remote Call
		ResponseEntity<Object> responseEntity = restTemplate.getForEntity(BASE_URI + PRODUCTS, Object.class); 
		
		//Check the status
		if(responseEntity.getStatusCode()==HttpStatus.OK) {
			log.debug("API call is success");
		}
		
		//GEt the products
		Map objects = (LinkedHashMap) responseEntity.getBody();
		
		
		objects.keySet().forEach(t ->log.debug("{}",t) );
		
		//List Of PRoducts
		List<Object> list=(List<Object>) objects.get("products");
		
		
		ObjectMapper objectMapper=new ObjectMapper();
		
		
		List<ProductDTO> products= 
			list.stream()
			.map(obj->objectMapper.convertValue(obj, ProductDTO.class))
			.collect(Collectors.toList());
		
		productService.saveAll(products);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	
}
