package com.srtech.dto;

import lombok.Data;

@Data
public class AddItemRequest {

	private Integer categoryId;
	private Integer productId;
	private Integer skuId;
	private Integer quantity;
	
}
