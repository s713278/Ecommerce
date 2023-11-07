package com.srtech.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.srtech.entity.Product;
import com.srtech.exception.ProductNotFoundException;
import com.srtech.record.ProductRecord;
import com.srtech.repository.ProductRepository;
import com.srtech.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/product")
@Slf4j
public class ProductController {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductService productService;

	@GetMapping(value = "/{pageNo}/{pageSize}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Product>> getProducts(@PathVariable Integer pageNo,@PathVariable Integer pageSize) {
		log.debug("In getProducts() pageNo : {} and pageSize : {}",pageNo, pageSize);
		Pageable pageabl = PageRequest.of(pageNo, pageSize);
		Page<Product> products=productRepository.findAll(pageabl);
		log.debug("Total No Of Records :"+products.getSize());
		//log.debug("products.getNumber() :"+products.getNumber());
		log.debug("Out getProducts()");
		return new ResponseEntity<List<Product>>(products.get().collect(Collectors.toList()), HttpStatus.OK);
	}	
	
	@GetMapping("/list")
	public ResponseEntity<List<Product>> productsByOrder() {
		log.debug("In productsByOrder()");
		
		List<Product> items = new ArrayList<>();
		for (Iterator<Product> it = productRepository.findAll(Sort.by("name","desc")).iterator(); it.hasNext();) {
			items.add(it.next());
		}
		//log.debug("products.getNumber() :"+products.getNumber());
		log.debug("Out productsByOrder()");
		return new ResponseEntity<List<Product>>(items, HttpStatus.OK);
	}
	
	@GetMapping(value = "/search/{name}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Product>> searchByName(@PathVariable String name){
		List<Product> list =productRepository.findByName(name);
		if(list ==null || list.isEmpty()) {
			throw new ProductNotFoundException("No products found with name "+name);
		}
		return new ResponseEntity<List<Product>>(list,HttpStatus.OK);
	}
	
	@GetMapping(value = "/exists/{name}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> existisProduct(@PathVariable String name){
		return new ResponseEntity<Boolean>(productService.existsByName(name),HttpStatus.OK);
	}
	
	
	@PostMapping(value = "/create",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductRecord> create(@RequestBody Product product){
		log.debug("In create() for product : {}",product);
		Product p=productRepository.save(product);
		log.debug("Out create() for product : {}",product);
		ProductRecord  productRecord= new ProductRecord(p.getId(),p.getName(),p.getDescription());
		return new ResponseEntity<ProductRecord>(productRecord,HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/existsById/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> existisById(@PathVariable Integer id){
		return new ResponseEntity<Boolean>(productService.existsById(id),HttpStatus.OK);
	}

}
