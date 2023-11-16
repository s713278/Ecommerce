package com.srtech.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.srtech.dto.ExcceptionDetails;
import com.srtech.entity.Product;
import com.srtech.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "Product Controller",description = "Product Rest Service")
@RestController
@RequestMapping("/product")
@Slf4j
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping("/{pid}")
	public ResponseEntity<Product> getProductById(@PathVariable("pid") Integer productId) {
		log.debug("Search for product id: {}",productId);
		Product product =productService.findById(productId);
		log.debug("Search resule for {} is : {}",productId,product);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}
	
	@GetMapping("/sku/{skuId}")
	public ResponseEntity<Product> getProductBySkuId(@PathVariable("skuId") Integer skuId) {
		log.debug("Search for product based on sku id: {}",skuId);
		Product product =productService.findById(skuId);
		log.debug("Search result for sku id {} is a product : {}",skuId,product);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}
	

	@GetMapping(value = "/{pageNo}/{pageSize}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Product>> getProducts(@PathVariable Integer pageNo,@PathVariable Integer pageSize) {
		log.debug("In getProducts() pageNo : {} and pageSize : {}",pageNo, pageSize);
		Pageable pageabl = PageRequest.of(pageNo, pageSize);
		Page<Product> products=productService.getfindAll(pageabl);
		
		
		log.debug("Total No Of Records :"+products.getSize());
		//log.debug("products.getNumber() :"+products.getNumber());
		log.debug("Out getProducts()");
		return new ResponseEntity<List<Product>>(products.get().collect(Collectors.toList()), HttpStatus.OK);
	}	
	
	@GetMapping("/list")
	public ResponseEntity<List<Product>> productsByOrder() {
		log.debug("In productsByOrder()");
		
		List<Product> items = new ArrayList<>();
		for (Iterator<Product> it = productService.findAll().iterator(); it.hasNext();) {
			items.add(it.next());
		}
		//log.debug("products.getNumber() :"+products.getNumber());
		log.debug("Out productsByOrder()");
		return new ResponseEntity<List<Product>>(items, HttpStatus.OK);
	}
	
	@GetMapping(value = "/exists/{name}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> existisProduct(@PathVariable String name){
		return new ResponseEntity<Boolean>(productService.existsByName(name),HttpStatus.OK);
	}
	
	@Operation(description = "Create Catalog Product ",method = "POST",summary = "Product Create API")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "Product Created"),
	        @ApiResponse(responseCode = "500", 
	        description = "Internal Server Exception",
	        content={
	        		@Content(
	        				mediaType = "",
	        				schema = @Schema(implementation = ExcceptionDetails.class)
	        				)
	        }
	        )})
	@PostMapping(value = "/create",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> create(@RequestBody Product product){
		log.debug("In create() for product : {}",product);
		Product p=productService.save(product);
		log.debug("Out create() for product : {}",product);
		return new ResponseEntity<Product>(p,HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/existsById/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> existisById(@PathVariable Integer id){
		return new ResponseEntity<Boolean>(productService.existsById(id),HttpStatus.OK);
	}

}
