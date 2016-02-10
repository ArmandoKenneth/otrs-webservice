package com.armando.otrs.exception;

import com.armando.otrs.util.ResponseCodes;

public class ArticleException extends Exception{

	private static final long serialVersionUID = -5413633123176724157L;

	private ResponseCodes response;
	
	public ArticleException(){
		
	}
	
	public ArticleException(String message)
	{
		super(message);
	}
	
	public ArticleException(ResponseCodes response, String message){
		super(message);
		this.response = response;
	}

	public ArticleException(Throwable cause)
	{
		super(cause);
	}

	public ArticleException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public ArticleException(String message, Throwable cause, 
                                       boolean enableSuppression, boolean writableStackTrace)
	{
		super(message, cause, enableSuppression, writableStackTrace);
	}
	
	public ResponseCodes getResponse(){
		return this.response;
	}
}
