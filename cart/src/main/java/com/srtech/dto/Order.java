package com.srtech.dto;

import java.util.List;

import lombok.Data;

@Data
public class Order {

	private Long orderId;
	private List<CartItem> cartItems;
	private Double orderSubTotal;
}
