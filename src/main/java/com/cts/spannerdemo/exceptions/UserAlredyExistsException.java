package com.cts.spannerdemo.exceptions;

public class UserAlredyExistsException extends Exception{

	private static final long serialVersionUID = 1L;

	public UserAlredyExistsException(String message) {
		super(message);
	}
}
