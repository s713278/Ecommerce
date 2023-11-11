package com.srtech.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.srtech.entity.Sku;

@Repository
public interface SkuRepository extends CrudRepository<Sku,Integer>, PagingAndSortingRepository<Sku, Integer> {

}
