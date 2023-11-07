package com.srtech.service;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class EmailService {

	
	public void sendMail(String email) {
	
		System.out.println("Email will be sent to "+email);
		log.info("Email will be sent to \"+email");
		
	}
}
