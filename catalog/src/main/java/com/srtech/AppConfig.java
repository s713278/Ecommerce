package com.srtech;

import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.client.RestTemplate;

import com.srtech.repository.MySQLUserDetailsService;

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

	//@Bean
	public UserDetailsService defaultUsers1() {

		UserDetails adminUser = User.withDefaultPasswordEncoder().username("admin").password("admin1234")
				.authorities("admin").build();

		UserDetails readUser = User.withDefaultPasswordEncoder().username("read").password("read1234")
				.authorities("read").build();

		UserDetails writeUser = User.withDefaultPasswordEncoder().username("write").password("write1234")
				.authorities("write").build();

		UserDetails normal = User.withDefaultPasswordEncoder().username("user").password("user1234").authorities("user")
				.build();

		return new InMemoryUserDetailsManager(adminUser, readUser, writeUser, normal);
	}

	//This is for custom DB
	@Bean
	public UserDetailsService userDetailsService() {
		return new MySQLUserDetailsService();
	}

	/**
	 * Password with hashing
	 * 
	 * @return
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
