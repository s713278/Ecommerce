package com.srtech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
@EnableCaching
public class CatalogApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatalogApplication.class, args);
		// applicationContext.getBean(EmailService.class).sendMail("test@gmail.com");
		// applicationContext.close();
		// log.debug("DEBUG : ***************************************************");
		// log.info("INFO : ***************************************************");

	}
}
