package com.armando.otrs.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Utility class to handle all the JSON requisitions
 * 
 * @author armando.rocha
 *
 */
public class JsonUtil {

	private ObjectMapper mapper;

	/**
	 * Transforms the JSON received into a object of the specified class
	 * 
	 * @param json
	 *            JSON to be converted
	 * @param classInfo
	 *            Class type that will be returned
	 * @return Object of the type informed in the param "classInfo" with the
	 *         information that was received in the "json" param
	 * @throws Exception
	 */
	public Object jsonToObject(String json, Class classInfo) throws Exception {
		try {
			mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return mapper.readValue(json, classInfo);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Transforms the object received into a JSON string
	 * @param obj				Object to be transformed
	 * @return					String with the contents of the object in the JSON format
	 * @throws Exception
	 */
	public String objectToJson(Object obj) throws Exception {
		try {
			mapper = new ObjectMapper();
			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			throw e;
		}
	}
}
