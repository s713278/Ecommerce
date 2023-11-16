package com.srtech.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.srtech.entity.Sku;
import com.srtech.util.RepositoryUtil;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class SkuServiceImpl implements SkuService {

	@Autowired
	private CacheManager cacheManager;

	private RepositoryUtil repositoryUtil;

	@Cacheable(cacheNames = "sku", key = "#id")
	@Override
	public Sku findById(Integer skuId) {
		log.debug("findById()");
		Optional<Sku> skuOpt = repositoryUtil.getSkuRepository().findById(skuId);
		if (skuOpt.isPresent()) {
			return skuOpt.get();
		}
		throw new EntityNotFoundException("No product foud nfor product id " + skuId);
	}

}
