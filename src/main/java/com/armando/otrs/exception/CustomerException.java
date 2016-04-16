package com.armando.otrs.exception;

import com.armando.otrs.util.ResponseCodes;

public class CustomerException extends Exception{

	private static final long serialVersionUID = -5413633123176724157L;

	private ResponseCodes responseCode = ResponseCodes.INTERNAL_ERROR;
	private String responseMessage = ResponseCodes.INTERNAL_ERROR.getCodeDescription();
	
	public CustomerException(){
		
	}
	
	public CustomerException(String message)
	{
		super(message);
		analiseException(message);
	}
	
	public CustomerException(ResponseCodes response, String message){
		super(message);
		this.responseCode = response;
	}

	public CustomerException(Throwable cause)
	{
		super(cause);
		analiseException(cause.getMessage());
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

	private void analiseException(String message){
		/* customer_company */
		if (message.contains("change_by")){
			setResponseMessage("Value of the field \"change_by\" is invalid");
			setResponseCode(ResponseCodes.BAD_REQUEST);
		}
		if (message.contains("valid_id")){
			setResponseMessage("Value of the field \"valid_id\" is invalid");
			setResponseCode(ResponseCodes.BAD_REQUEST);
		}
		if (message.contains("customer_company_name")) {
			setResponseMessage("The value in unique attribute \"name\" already exists");
			setResponseCode(ResponseCodes.BAD_REQUEST);
		}
		if (message.contains("customer_id")) {
			setResponseMessage("The value in unique attribute \"customer_id\" already exists");
			setResponseCode(ResponseCodes.BAD_REQUEST);
		}
		
		/* customer_user */
		if (message.contains("customer_id")){
			setResponseMessage("Field \"customer_id\" is invalid");
			setResponseCode(ResponseCodes.BAD_REQUEST);
		}
		if (message.contains("customer_user_pkey")){
			setResponseMessage("Value of the field \"id\" already exists");
			setResponseCode(ResponseCodes.RESOURCE_CONFLICT);
		}
		if (message.contains("customer_company_name")){
			setResponseMessage("Value of the field \"name\" already exists");
			setResponseCode(ResponseCodes.RESOURCE_CONFLICT);
		}
		if (message.contains("fk_customer_user_change_by_id")){
			setResponseMessage("Value of the field \"change_by\" is invalid");
			setResponseCode(ResponseCodes.BAD_REQUEST);
		}
		if (message.contains("fk_customer_user_create_by_id")){
			setResponseMessage("Value of the field \"create_by\" is invalid");
			setResponseCode(ResponseCodes.BAD_REQUEST);
		}
		if (message.contains("customer_user_login")){
			setResponseMessage("Value of the field \"customer_user_login\" already exists");
			setResponseCode(ResponseCodes.RESOURCE_CONFLICT);
		}
	}
	
	/**
	 * @return the responseCode
	 */
	public ResponseCodes getResponseCode() {
		return responseCode;
	}

	/**
	 * @param responseCode the responseCode to set
	 */
	public void setResponseCode(ResponseCodes responseCode) {
		this.responseCode = responseCode;
	}

	/**
	 * @return the responseMessage
	 */
	public String getResponseMessage() {
		return responseMessage;
	}

	/**
	 * @param responseMessage the responseMessage to set
	 */
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

}

