package com.srtech;

import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class AppConfig {

	/*
	 * @Bean public CacheManager cacheManager() { // configure and return an
	 * implementation of Spring's CacheManager SPI SimpleCacheManager cacheManager =
	 * new SimpleCacheManager(); cacheManager.setCaches(Set.of(new
	 * ConcurrentMapCache("default"))); return cacheManager; }
	 */
	

	@Bean
	public CacheManager cacheManager() {
		log.debug("#################In cacheManager() ################33");
		return new ConcurrentMapCacheManager();
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
