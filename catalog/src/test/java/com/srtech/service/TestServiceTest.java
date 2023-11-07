package com.srtech.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestServiceTest {

	@Autowired
	private TestService service;
	
	@Test
	void testGetMessageForEmptyName() {
		assertNull(service.getMessage(""));
	}
	
	@Test
	void testGetMessageForNull() {
		assertNull(service.getMessage(null));
	}
	
	
	@Test
	void testGetMessageForCorrectName() {
		assertNotNull(service.getMessage("Sri Ram"));
	}

	@Test
	void testAddForSuccessScenario() {
		assertEquals(10, service.add(5, 5));
	}

}
