package com.srtech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.srtech.entity.Product;
import com.srtech.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private CacheManager cacheManager;
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	@Cacheable(cacheNames = "product" ,key = "#name")
	public List<Product> findByName(String name) {
		return null;
	}

	@Override
	public List<Product> findByNameAndDesc(String name, String desc) {
		return null;
	}

	@Cacheable(value="existsByName")
	@Override
	public boolean existsByName(String name) {
		log.debug("existsByName for {} ",name);
		boolean result = productRepository.existsByName(name);
		log.debug("Is {} found ? {}",name,result);
		return result;
	}
	
	
	@Cacheable(cacheNames =  "existsById",key = "#id")
	@Override
	public boolean existsById(Integer id) {
		log.debug("existsById for {} ",id);
		boolean result = productRepository.existsById(id);
		log.debug("Is product id:: {} found ? {}",id,result);
		cacheManager.getCacheNames().stream().forEach(t -> log.debug("Cache Name :{}",t));
		return result;
	}

}
