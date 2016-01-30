package com.armando.otrs.exception;

public class CustomerException extends Exception{

	private static final long serialVersionUID = -5413633123176724157L;


	public CustomerException(){
		
	}
	
	public CustomerException(String message)
	{
		super(message);
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

}

