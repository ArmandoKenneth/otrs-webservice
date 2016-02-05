package com.armando.otrs.exception;

import com.armando.otrs.util.ResponseCodes;

public class CustomerException extends Exception{

	private static final long serialVersionUID = -5413633123176724157L;

	private ResponseCodes response;
	
	public CustomerException(){
		
	}
	
	public CustomerException(String message)
	{
		super(message);
	}
	
	public CustomerException(ResponseCodes response, String message){
		super(message);
		this.response = response;
	}

	public CustomerException(Throwable cause)
	{
		super(cause);
	}

	public CustomerException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public CustomerException(String message, Throwable cause, 
                                       boolean enableSuppression, boolean writableStackTrace)
	{
		super(message, cause, enableSuppression, writableStackTrace);
	}
	
	public ResponseCodes getResponse(){
		return this.response;
	}

}

