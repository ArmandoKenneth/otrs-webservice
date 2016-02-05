package com.armando.otrs.util;

/**
 * Enum to represent all the codes that will be used in the webserver
 * @author armando.rocha
 *
 */
public enum ResponseCodes {
	
	SUCCESS(200, "Sucess"),
	INTERNAL_ERROR(500, "Internal error"),
	RESOURCE_NOT_FOUND(404, "Resource not found"),
	RESOURCE_CREATED(201, "Resource created"),
	RESOURCE_CONFLICT(409, "Resource conflict"),
	NO_CONTENT(204, "No content");
	
	private int code;
	private String codeDescription;
	
	ResponseCodes(int code, String codeDescription) {
		this.code = code;
		this.codeDescription = codeDescription;
	}
	
	public int getCode() {
		return code;
	}
	public String getCodeDescription() {
		return codeDescription;
	}
	
	
}
