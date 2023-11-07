package com.srtech.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.srtech.entity.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product,Integer>, PagingAndSortingRepository<Product, Integer> {

	//Select * from PRoduct Where Name='' 
	List<Product> findByName(String name);
	
	//Select * from PRoduct Where Name='' AND DESC=''
	List<Product> findByNameAndDescription(String name,String desc);
	
	//Select name from Product Where Name='';
	boolean existsByName(String name);
}
