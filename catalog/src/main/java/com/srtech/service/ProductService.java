package com.srtech.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.srtech.dto.ProductDTO;
import com.srtech.entity.Product;

public interface ProductService{

	//Select * from PRoduct Where Name='' 
	List<Product> findByName(String name);
	
	//Select * from PRoduct Where Name='' AND DESC=''
	List<Product> findByNameAndDesc(String name,String desc);
	
	//Select name from Product Where Name='';
	boolean existsByName(String name);
	
	boolean existsById(Integer id);

	void saveAll(List<ProductDTO> products);
	
	Long count();

	Product findById(Integer productId);
	
	Product findBySkuId(Integer skuId);

	Page<Product> getfindAll(Pageable pageabl);

	Iterable<Product> findAll();

	Product save(Product product);

}
