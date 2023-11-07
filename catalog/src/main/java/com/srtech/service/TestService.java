package com.srtech.service;

import org.springframework.stereotype.Service;

@Service
public class TestService {

	public String getMessage(String name) {
		if(name == null || name.strip()=="") {
			return null;
		}
		return "Hello";
	}
	
	public int add(int a,int b) {
		if(a<0 || b<0) {
			return -1;
		}
		return a+b;
	}
}
