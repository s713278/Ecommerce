package com.srtech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.srtech.entity.Sku;
import com.srtech.service.SkuService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "Sku Controller",description = "Sku Rest Service")
@RestController
@RequestMapping("/sku")
@Slf4j
public class SkuController {

	
	@Autowired
	private SkuService skuService;
	
	@Operation(summary = "Get SKU by ID")
	@GetMapping("/{pid}")
	public ResponseEntity<Sku> getProductById(@PathVariable("pid") Integer skuId) {
		log.debug("Search for product id: {}",skuId);
		Sku sku =skuService.findById(skuId);
		log.debug("Search resule for {} is : {}",skuId,sku);
		return new ResponseEntity<Sku>(sku, HttpStatus.OK);
	}
	
}
