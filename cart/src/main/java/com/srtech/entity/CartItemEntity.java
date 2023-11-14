package com.srtech.entity;

import lombok.Data;

@Data
public class CartItemEntity {

	private Integer skuId;
	private Integer quantity;
	private Double itemsTotal; //qty&sku's list price
}
