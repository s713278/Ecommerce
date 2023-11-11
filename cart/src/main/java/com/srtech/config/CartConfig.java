package com.srtech.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.srtech.filter.VerifyIPFilter;
import com.srtech.interceptor.ExecutionTimeInterceptor;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class CartConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new ExecutionTimeInterceptor()).addPathPatterns("/cart/addToOrder");
	}

	@Bean
	public FilterRegistrationBean<VerifyIPFilter> filterRegistrationBean() {
		log.debug("Filters registred....");
		FilterRegistrationBean<VerifyIPFilter> registrationBean = new FilterRegistrationBean<VerifyIPFilter>();
		registrationBean.setFilter(new VerifyIPFilter());
		registrationBean.addUrlPatterns("/cart/addToOrder");
		registrationBean.setOrder(2);
		return registrationBean;
	}

}
