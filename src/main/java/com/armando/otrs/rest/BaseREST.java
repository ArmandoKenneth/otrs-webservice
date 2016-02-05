package com.armando.otrs.rest;

import javax.ws.rs.core.MediaType;

import com.armando.otrs.util.JsonUtil;
import com.armando.otrs.util.ResponseCodes;
/**
 * https://github.com/RestCheatSheet/api-cheat-sheet#api-design-cheat-sheet
 * @author armando.rocha
 *
 */
public class BaseREST {

	protected JsonUtil json = new JsonUtil();
	/** Selected status code */
	private ResponseCodes responseCode = ResponseCodes.SUCCESS;
	protected final String APPLICATION_TYPE_JSON = MediaType.APPLICATION_JSON + ";charset=utf-8";
	
	/**
	 * Builds a string to be returned to the requester when an internal error (500) occurs
	 * @param extra			Extra content to be appended to the default message
	 * @return				Internal error message
	 */
	protected String buildInternalErrorMessage(String extra){
		setCode(ResponseCodes.INTERNAL_ERROR);
		return responseCode.getCodeDescription()+": "+extra;
	}
	
	/**
	 * Builds a string to be returned to the request was finalised without any errors
	 * @param extra			Extra content to be appended to the default message
	 * @return				Success message
	 */
	protected String buildSucessMessage(String extra){
		setCode(ResponseCodes.SUCCESS);
		return responseCode.getCodeDescription()+": "+extra;
	}
	
	protected ResponseCodes getResponseCode() {
		return responseCode;
	}

	protected void setCode(ResponseCodes getResponseCode) {
		this.responseCode = getResponseCode;
	}

}
