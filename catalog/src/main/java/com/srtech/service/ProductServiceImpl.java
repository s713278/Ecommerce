package com.srtech.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.srtech.dto.ProductDTO;
import com.srtech.entity.Image;
import com.srtech.entity.Product;
import com.srtech.exception.ProductNotFoundException;
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
	@Cacheable(cacheNames = "product", key = "#name")
	public List<Product> findByName(String name) {
		return null;
	}

	@Override
	public List<Product> findByNameAndDesc(String name, String desc) {
		return null;
	}

	@Cacheable(value = "existsByName")
	@Override
	public boolean existsByName(String name) {
		log.debug("existsByName for {} ", name);
		boolean result = productRepository.existsByTitle(name);
		log.debug("Is {} found ? {}", name, result);
		return result;
	}

	@Cacheable(cacheNames = "existsById", key = "#id")
	@Override
	public boolean existsById(Integer id) {
		log.debug("existsById for {} ", id);
		boolean result = productRepository.existsById(id);
		log.debug("Is product id:: {} found ? {}", id, result);
		cacheManager.getCacheNames().stream().forEach(t -> log.debug("Cache Name :{}", t));
		return result;
	}

	@Transactional
	@Override
	public void saveAll(List<ProductDTO> products) {
		log.debug("saveAll() started");
		ObjectMapper mapper = new ObjectMapper();
		/*List<Product> productEntities = products.stream()
				.map(productDTO -> mapper.convertValue(productDTO, Product.class)).collect(Collectors.toList());*/
		
		List<Product> productEntities2= products.stream()
		.map(prodDTO ->{
			List<Image> images = prodDTO.getImages().stream()
							.map(t -> {
							return Image.builder()
									.imagePath(t)
									.build();
				}
			).collect(Collectors.toList());
			return Product.builder()
				.title(prodDTO.getTitle())
				.description(prodDTO.getDescription())
				.rating(prodDTO.getRating())
				.thumbnail(prodDTO.getThumbnail())
				.images(images)
				.build();
		}).collect(Collectors.toList());
		
		productRepository.saveAll(productEntities2);
		
		log.debug("saveAll() completed ");
	}

	@Override
	public Long count() {
		return  productRepository.count();
	}

	@Override
	public Product findById(Integer productId) {
		Optional<Product> productOpt=productRepository.findById(productId);
		if(productOpt.isPresent()) {
			return productOpt.get();
		}
		throw new ProductNotFoundException("No product foud nfor product id "+productId);
	}

	@Override
	public Product findBySkuId(Integer skuId) {
		List<Product> products= productRepository.findBySkusId(skuId);
		if(products == null || products.isEmpty()) {
			throw new ProductNotFoundException("No product foud nfor product based on skuId "+skuId);
		}
		return products.get(0);
	}

	@Override
	public Page<Product> getfindAll(Pageable pageabl) {
		
		return null;
	}

	@Override
	public Iterable<Product> findAll() {
		return productRepository.findAll();
	}

	@Override
	public Product save(Product product) {
		return productRepository.save(product);
	}

	
}
