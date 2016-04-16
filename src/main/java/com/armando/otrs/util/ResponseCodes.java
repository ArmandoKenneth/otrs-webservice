package com.armando.otrs.util;

/**
 * Enum to represent all the codes that will be used in the webserver
 * @author armando.rocha
 *
 */
public enum ResponseCodes {
	
	SUCCESS(200, "Sucess", "200"),
	INTERNAL_ERROR(500, "Internal error", "500"),
	RESOURCE_NOT_FOUND(404, "Resource not found", "404"),
	RESOURCE_CREATED(201, "Resource created", "201"),
	RESOURCE_CONFLICT(409, "Resource conflict", "409"),
	BAD_REQUEST(400, "Bad request", "400"),
	NO_CONTENT(204, "No content", "204");
	
	private int code;
	private String codeDescription;
	private String codeAsString;
	
	ResponseCodes(int code, String codeDescription, String codeAsString) {
		this.code = code;
		this.codeDescription = codeDescription;
		this.codeAsString = codeAsString;
	}
	
	public String getCodeAsString() {
		return codeAsString;
	}

	public int getCode() {
		return code;
	}
	public String getCodeDescription() {
		return codeDescription;
	}
	
	
}
