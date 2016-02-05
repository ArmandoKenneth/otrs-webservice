package com.armando.otrs.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Utility class 
 * @author armando.rocha
 *
 */
public class Json {

	private ObjectMapper mapper;
	
	/**
	 * 
	 * @param json
	 * @param classe
	 * @return
	 * @throws Exception
	 */
	public Object jsonToObject(String json, Class classe) throws Exception{
        try {
            mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return mapper.readValue(json, classe);
        } catch (Exception e) {
            throw e;
        }
    }
	
	/**
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public String objectToJsno(Object obj) throws Exception{
    	try {
    		mapper = new ObjectMapper();
			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			throw e;
		}
    }
}
