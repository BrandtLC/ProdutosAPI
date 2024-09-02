package com.projeto.produtosAPI.exceptions;

public class BadParamException extends RuntimeException{

	private static final long serialVersionUID = -8506408484651612654L;
	
	public BadParamException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BadParamException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public BadParamException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public BadParamException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public BadParamException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
}
