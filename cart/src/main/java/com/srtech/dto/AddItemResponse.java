package com.srtech.dto;

import lombok.Data;

@Data
public class AddItemResponse {
	private Boolean status;
	private Order order;
}
