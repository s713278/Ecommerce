package com.srtech.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "Cart Controller",description = "Cart API")
@RestController
@RequestMapping("/cart")
@Slf4j
public class CartController {

	@Operation(summary = "Add Item to Order",description = "Add Item To Order API")
	@PostMapping("/addToOrder")
	public ResponseEntity<Void> addItemToOrder(Integer productId,Integer skuId,int quantity,Integer orderId){
		log.debug("Add item productId: {}, skuId:{}, Quantity :{}, OrderId : {}",productId,skuId,quantity,orderId);
		if(orderId ==null) {
			//Create Order
		}else {
			
		}
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
}
