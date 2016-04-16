package com.armando.otrs.exception;

import com.armando.otrs.util.ResponseCodes;

public class ArticleException extends Exception {

	private static final long serialVersionUID = -5413633123176724157L;

	private ResponseCodes responseCode = ResponseCodes.INTERNAL_ERROR;
	private String responseMessage = ResponseCodes.INTERNAL_ERROR.getCodeDescription();

	public ArticleException() {

	}

	public ArticleException(String message) {
		super(message);
		analiseException(message);
	}

	public ArticleException(ResponseCodes responseCode, String message) {
		super(message);
		this.responseCode = responseCode;
	}

	public ArticleException(Throwable cause) {
		super(cause);
		analiseException(cause.getMessage());
	}

	public ArticleException(String message, Throwable cause) {
		super(message, cause);
	}

	public ArticleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	private void analiseException(String message){
		/* article */
		if (message.contains("fk_article_article_sender_type_id_id")) {
			setResponseMessage("Value of the field \"article_sender_type_id\" is invalid");
			setResponseCode(ResponseCodes.BAD_REQUEST);
		}
		if (message.contains("fk_article_article_type_id_id")) {
			setResponseMessage("Value of the field \"article_type_id\" is invalid");
			setResponseCode(ResponseCodes.BAD_REQUEST);
		}
		if (message.contains("fk_article_change_by_id")) {
			setResponseMessage("Value of the field \"change_by\" is invalid");
			setResponseCode(ResponseCodes.BAD_REQUEST);
		}
		if (message.contains("fk_article_create_by_id")) {
			setResponseMessage("Value of the field \"create_by\" is invalid");
			setResponseCode(ResponseCodes.BAD_REQUEST);
		}
		if (message.contains("fk_article_ticket_id_id")) {
			setResponseMessage("Value of the field \"ticket_id\" is invalid");
			setResponseCode(ResponseCodes.BAD_REQUEST);
		}
		if (message.contains("fk_article_valid_id_id")) {
			setResponseMessage("Value of the field \"valid_id\" is invalid");
			setResponseCode(ResponseCodes.BAD_REQUEST);
		}
		if (message.contains("attachment")){
			setResponseMessage("There are attachments related to this articles");
			setResponseCode(ResponseCodes.RESOURCE_CONFLICT);
		}
		
		/* article_attachment */
		if (message.contains("content")){
			setResponseMessage("Field \"conts\" is required");
			setResponseCode(ResponseCodes.BAD_REQUEST);
		}
		if (message.contains("fk_article_attachment_article_id_id")){
			setResponseMessage("Value of the field \"article_id\" is invalid");
			setResponseCode(ResponseCodes.BAD_REQUEST);
		}
		if (message.contains("fk_article_attachment_change_by_id")){
			setResponseMessage("Value of the field \"change_by_id\" is invalid");
			setResponseCode(ResponseCodes.BAD_REQUEST);
		}
		if (message.contains("fk_article_attachment_create_by_id")){
			setResponseMessage("Value of the field \"create_by_id\" is invalid");
			setResponseCode(ResponseCodes.BAD_REQUEST);
		} 
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ArticleException [responseCode=" + responseCode + ", responseMessage=" + responseMessage + "]";
	}

	/**
	 * @return the responseCode
	 */
	public ResponseCodes getResponseCode() {
		return responseCode;
	}

	/**
	 * @param responseCode
	 *            the responseCode to set
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
	 * @param responseMessage
	 *            the responseMessage to set
	 */
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
}
