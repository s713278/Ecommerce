package com.srtech.dto;

import lombok.Data;

@Data
public class CartItem {

	private Integer skuId;
	private Integer quantity;
	private Double itemsTotal; //qty&sku's list price
}
