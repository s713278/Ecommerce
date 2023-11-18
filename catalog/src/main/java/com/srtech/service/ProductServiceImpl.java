package com.srtech.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
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
import com.srtech.entity.Category;
import com.srtech.entity.Image;
import com.srtech.entity.Product;
import com.srtech.entity.Sku;
import com.srtech.exception.ProductNotFoundException;
import com.srtech.repository.CategoryRepository;
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

	@Autowired
	private CategoryRepository categoryRepository;

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

	@Transactional
	@Override
	public void saveAllNew(List<ProductDTO> products) {
		log.debug("saveAllNew() started");
		ObjectMapper mapper = new ObjectMapper();
		/*
		 * List<Product> productEntities = products.stream() .map(productDTO ->
		 * mapper.convertValue(productDTO, Product.class)).collect(Collectors.toList());
		 */

		Map<String, Category> catMap = new HashMap<String, Category>();
		Set<String> categoryNames = products.stream().map(prod -> prod.getCategory()).collect(Collectors.toSet());
		for (String cateName : categoryNames) {
			Category category = new Category();
			category.setName(cateName);
			catMap.put(cateName, category);
		}
		Iterator<ProductDTO> prodItr = products.iterator();
		while (prodItr.hasNext()) {
			ProductDTO prodDto = prodItr.next();
			if (catMap.containsKey(prodDto.getCategory())) {
				List<String> images = new ArrayList<String>();
				images = prodDto.images;
				List<Image> imagesList = new ArrayList<Image>();
				for (String str : images) {
					Image img = Image.builder().imagePath(str).build();
					imagesList.add(img);
				}

				List<Sku> skuList = new ArrayList<Sku>();
				Sku sku = Sku.builder().title(prodDto.getTitle()).description(prodDto.description)
						.listPrice(prodDto.getPrice()).salePrice(prodDto.getDiscountPercentage())
						.brand(prodDto.getBrand()).build();

				skuList.add(sku);

				Product prod = Product.builder().title(prodDto.getTitle()).description(prodDto.getDescription())
						.rating(prodDto.getRating()).thumbnail(prodDto.getThumbnail()).images(imagesList).skus(skuList)
						.build();

				Category cat = catMap.get(prodDto.getCategory());
				if (cat.getProducts() == null) {
					List<Product> newList = new ArrayList<>();
					newList.add(prod);
					cat.setProducts(newList);
				} else {
					List<Product> existinglist = cat.getProducts();
					existinglist.add(prod);
					cat.setProducts(existinglist);
				}
				cat.setDescription(prodDto.getCategory());
			}
		}
		List<Category> catsList = new ArrayList<Category>();
		for (Map.Entry<String, Category> entry : catMap.entrySet()) {
			catsList.add(entry.getValue());
		}
		categoryRepository.saveAll(catsList);
		log.debug("saveAllNew() completed ");
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
