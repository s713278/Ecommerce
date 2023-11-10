package com.srtech.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.srtech.entity.Image;
import com.srtech.entity.Product;
import com.srtech.repository.ProductRepository;

@DataJpaTest
@ActiveProfiles(value = {"h2"})
class ProductServiceTest {
	@Autowired
	ProductRepository productRepository;
	

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testSaveAll() {
		productRepository.saveAll(List.of(
				Product.builder()
				.title("Samsung Universe 9")
				.description("Samsung's new variant which goes beyond Galaxy to the Universe")
				.price(1249d)
				.discountPercentage(15.46d)
				.rating(4.9d)
				.stock(36)
				.brand("Samsung")
				.category("smartphones")
				.images((ArrayList<Image>) List.of(Image.builder()
						.imagePath("https://i.dummyjson.com/data/products/3/1.jpg")
						.build()))
				.build()
				));
	}

}
