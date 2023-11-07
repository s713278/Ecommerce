package com.srtech.exception;

public class ProductNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 796248560239272425L;
	
	public ProductNotFoundException(String message) {
		super(message);
	}

}
