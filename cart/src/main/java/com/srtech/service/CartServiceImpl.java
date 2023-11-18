package com.srtech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.srtech.dto.AddItemRequest;
import com.srtech.dto.Order;
import com.srtech.repository.CartRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
public  @Slf4j @AllArgsConstructor class CartServiceImpl implements CartService {

	@Autowired
	private RestTemplate restTemplate; 
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private CartRepository cartRepository;
	
	@Value("${catalog.service}")
	private String catalogUri;
	
	
	private static String SKU_SERVICE_URI="http://localhost:9091/sku";
	
	@Override
	public List<Order> addItemToOrder(AddItemRequest request) {
		Integer productId=request.getProductId();
		validateProductId(productId,request.getSkuId());
		//Integer sku=request.getProductId();
		return null;
	}

	private boolean validateProductId(Integer productId,Integer skuId) {
		log.debug("catalogUri from property file {}",catalogUri);
		ResponseEntity<?> responseEntity=restTemplate.getForEntity(catalogUri+"/"+productId, Object.class);
		if(responseEntity.getStatusCode()==HttpStatus.OK) {
			//Success
			//Get the body: responseEntity.getBody();
			ResponseEntity<?> skuResponseEntity=restTemplate.getForEntity(SKU_SERVICE_URI+"/"+skuId, Object.class);
			SkuRecord skuRecord= objectMapper.convertValue(skuResponseEntity.getBody(), SkuRecord.class);
		}else {
			//Failure
		}
		
		return true;
	}
	
	@Override
	@CircuitBreaker(fallbackMethod = "handleCatService",name = "catalogService")
	public ResponseEntity<Object> getProduct(Integer productId) {
		log.debug("catalogUri from property file {}",catalogUri);
		ResponseEntity<?> responseEntity=restTemplate.getForEntity(catalogUri+"/"+productId, Object.class);
		log.debug("Status : {}",responseEntity.getStatusCode());
		return new ResponseEntity<Object>(responseEntity.getBody(),HttpStatus.OK);
	}
	
	@CircuitBreaker(fallbackMethod = "fallBackForShipping",name = "shippingService")
	public ResponseEntity<Object> checkShippingZipCode(Integer zipCode) {
		log.debug("catalogUri from property file {}",catalogUri);
		ResponseEntity<?> responseEntity=restTemplate.getForEntity(catalogUri+"/"+zipCode, Object.class);
		log.debug("Status : {}",responseEntity.getStatusCode());
		return new ResponseEntity<Object>(responseEntity.getBody(),HttpStatus.OK);
	}
	public ResponseEntity<Object> handleCatService(Integer productId,Throwable throwable) {
		log.error("Unable to retrieve product info for id: {} , and Exception Details {}",productId,throwable.getMessage());
		return new ResponseEntity<Object>("Catalog service is down and Unable to fetch product info for id:"+productId,HttpStatus.OK);
	}
}
