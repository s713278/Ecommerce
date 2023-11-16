package com.srtech.service;

import java.util.List;

import com.srtech.dto.AddItemRequest;
import com.srtech.dto.Order;

public interface CartService {

	public List<Order> addItemToOrder(AddItemRequest request);
}
