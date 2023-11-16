package com.srtech.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.srtech.entity.CartItemEntity;

@Repository
public interface CartRepository extends CrudRepository<CartItemEntity, Integer> {

}
