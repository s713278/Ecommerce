package com.srtech.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.srtech.entity.Category;
import com.srtech.repository.CategoryRepository;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/catalog")
@Slf4j
public class CategoryController {

	@Autowired
	RestTemplate restTemplate = null;
	
	@Autowired
	private CategoryRepository categoryRepository;

	@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Category>> getCategories() {
		log.debug("In getCategories()");
		List<Category> items = new ArrayList<>();
		for (Iterator<Category> it = categoryRepository.findAll().iterator(); it.hasNext();) {
			items.add(it.next());
		}
		log.debug("Out getCategories()");
		return new ResponseEntity<List<Category>>(items, HttpStatus.OK);
	}

	@GetMapping("/index")
	public ModelAndView index() {
		ModelAndView andView = new ModelAndView("index");

		return andView;
	}

	String DUMMY_PRODUCTS_URI = "https://dummyjson.com/products";

	@GetMapping("/dummyProducts")
	public ResponseEntity<Object> getExternalProducts() {
		log.debug("Getting PRoducts from {}",DUMMY_PRODUCTS_URI);
		
		Object data = restTemplate.getForObject(DUMMY_PRODUCTS_URI, Object.class);
		return new ResponseEntity<Object>(data, HttpStatus.OK);
	}

}
