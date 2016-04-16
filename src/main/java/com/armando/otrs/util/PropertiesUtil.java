package com.armando.otrs.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {

	public final static Properties props;
	
	static {
        props = new Properties();
        try {
        	ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        	try(InputStream resourceStream = classloader.getResourceAsStream("config.properties")) {
        	    props.load(resourceStream);
        	}
        } catch (IOException e) {
            
        }
    }
	
}
