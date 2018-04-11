package com.zdjf.frame.chain.exceptions;

/**
 * 集合内值不存在
 * 
 * @author chenrg 2015-9-26
 */
public class ValueExistsException extends Exception {

	private static final long serialVersionUID = 1L;

	public ValueExistsException() {
		super();
	}

	public ValueExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	public ValueExistsException(String message) {
		super(message);
	}

	public ValueExistsException(Throwable cause) {
		super(cause);
	}

}
