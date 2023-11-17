package com.srtech.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.srtech.dto.AddItemRequest;
import com.srtech.dto.Order;

public interface CartService {

	public List<Order> addItemToOrder(AddItemRequest request);
	ResponseEntity<Object> getProduct(Integer productId);
}
