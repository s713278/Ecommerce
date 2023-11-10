package com.srtech.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.srtech.entity.Category;

import lombok.extern.slf4j.Slf4j;

@DataJpaTest
@Slf4j
@ActiveProfiles("h2")
class CategoryRepositoryTest {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@BeforeEach
	void  setUp() throws Exception {
		categoryRepository.save(new Category(1,"Clothes","Clothes Desc",null));//List.of(new Product(1, "Mens Tea Shirt", "Test Desc")) ));
		categoryRepository.save(new Category(2,"Soaps","Desc",null));//List.of(new Product(1, "Mens Tea Shirt", "Test Desc")) ));
		categoryRepository.save(new Category(3,"Gifts Collection","Desc",null));//List.of(new Product(1, "Mens Tea Shirt", "Test Desc")) ));
	}
	
	@AfterAll
	static void after() throws Exception {
		log.debug("############# Only one time should be executed at last");
	}

	@Test
	void testFindAll() {
		Iterable<Category> it=categoryRepository.findAll();
		assertNotNull(it);
		assertTrue(it.iterator().hasNext());
		assertNotNull(it.iterator().next());
		assertTrue(it.iterator().hasNext());
		assertNotNull(it.iterator().next());
		assertTrue(it.iterator().hasNext());
		assertNotNull(it.iterator().next());
		//assertTrue(it.iterator().hasNext());
		//it.forEach(category -> log.debug("CAtegory : {}",category));
	}

	@Test
	void testFindByIdForNotExistedItems_100() {
		assertFalse(categoryRepository.findById(100).isPresent());
	}
	
	@Test
	void testFindByIdForExistedItems_1() {
		Optional<Category> opt=categoryRepository.findById(1);
		assertTrue(categoryRepository.findById(1).isPresent());
		assertEquals("Clothes", opt.get().getName());
		assertEquals("Clothes Desc", opt.get().getDescription());
	}
	
	//@Test
	void testFindAllById() {
		fail("Not yet implemented");
	}
	
	@Test
	void testJPAQueryForId() {
		categoryRepository.findAll().forEach(cat->log.debug("################## Category : {}",cat));
		Category cat=categoryRepository.findById2(7);
		assertNotNull(cat);
		assertEquals("Clothes", cat.getName());
		assertEquals("Clothes Desc", cat.getDescription());
	}

}
