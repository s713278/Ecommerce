package com.srtech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.srtech.dto.AddItemRequest;
import com.srtech.dto.Order;
import com.srtech.repository.CartRepository;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

	@Autowired
	private RestTemplate restTemplate; 
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private CartRepository cartRepository;
	
	private static String PRODUCT_SERVICE_URI="http://localhost:9091/product";
	private static String SKU_SERVICE_URI="http://localhost:9091/sku";
	
	@Override
	public List<Order> addItemToOrder(AddItemRequest request) {
		Integer productId=request.getProductId();
		validateProductId(productId,request.getSkuId());
		//Integer sku=request.getProductId();
		return null;
	}

	private boolean validateProductId(Integer productId,Integer skuId) {
		ResponseEntity<?> responseEntity=restTemplate.getForEntity(PRODUCT_SERVICE_URI+"/"+productId, Object.class);
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
}
