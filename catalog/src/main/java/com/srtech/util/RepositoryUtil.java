package com.srtech.util;

import org.springframework.stereotype.Component;

import com.srtech.repository.CategoryRepository;
import com.srtech.repository.ProductRepository;
import com.srtech.repository.SkuRepository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Component
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class RepositoryUtil {

	private CategoryRepository categoryRepository;

	private ProductRepository productRepository;

	private SkuRepository skuRepository;
}
