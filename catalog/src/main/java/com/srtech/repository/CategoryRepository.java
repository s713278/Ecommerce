package com.srtech.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.srtech.entity.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {

	
	@Query("select cat from Category cat where cat.id=:id")
	Category findById2(@Param("id") Integer id); 
	
	//@Query("select u from Category u where u.emailAddress = ?1")
	//Category findsById( Integer id);
	
	@Query("select cat from Category cat where cat.id=?1")
	Category findById1(Integer id); 
	
}
