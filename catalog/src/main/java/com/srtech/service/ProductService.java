package com.srtech.service;

import java.util.List;

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
}
