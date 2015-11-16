package com.exceptions;

public class DataNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -5257975131489666278L;

	public DataNotFoundException(String message) {
		super(message);
		System.out.println("****DataNotFoundException");
	}
}
