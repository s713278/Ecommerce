package com.srtech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.srtech.dto.AddItemRequest;
import com.srtech.service.CartService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "Cart Controller",description = "Cart API")
@RestController
@RequestMapping("/cart")
@Slf4j
public class CartController {
	
	@Autowired
	private CartService cartService;

	@Operation(summary = "Add Item to Order",description = "Add Item To Order API")
	@PostMapping("/addToOrder")
	public ResponseEntity<Void> addItemToOrder(@RequestBody AddItemRequest request ,@RequestParam("orderId") Integer orderId){
		log.debug("AddItemRequest : {}",request);
		
		orderId=null;
		if(orderId ==null) {
			//Create Order and Persist the CartITem in DB
			cartService.addItemToOrder(request);
		}else {
			//Update the Order
		}
		//Get Order and Associated cart items and Return
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
}
