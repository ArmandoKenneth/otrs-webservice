package com.armando.otrs.config;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.armando.otrs.util.PropertiesUtil;
import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * Class that will be used to create all connections to access the database
 * 
 * IMPORTANT: Before using, follow these steps: 
 * 
 * 	1. Fill the fields in the config_template.properties file (resources);
 * 	2. Rename the config_template.properties file to config.properties
 * 
 * @author Armando
 *
 */
public class DbConnection {

	private static DbConnection instance = new DbConnection();

	private String driver = PropertiesUtil.props.getProperty("driver");
	private String url = PropertiesUtil.props.getProperty("url");
	private String user = PropertiesUtil.props.getProperty("user");
	private String password = PropertiesUtil.props.getProperty("password");

	ComboPooledDataSource cpds;

	private DbConnection() {
	}

	public static DbConnection getInstance() {
		return instance;
	}

	private void configureDataSource() {
		cpds = new ComboPooledDataSource();
		try {
			cpds.setDriverClass(driver);
			cpds.setJdbcUrl(url);
			cpds.setUser(user);
			cpds.setPassword(password);

			cpds.setMinPoolSize(Integer.parseInt(PropertiesUtil.props.getProperty("minPoolSize")));
			cpds.setAcquireIncrement(Integer.parseInt(PropertiesUtil.props.getProperty("acquireIncrement")));
			cpds.setMaxPoolSize(Integer.parseInt(PropertiesUtil.props.getProperty("maxPoolSize")));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public synchronized Connection getConnection() {
		if (cpds == null) {
			configureDataSource();
		}
		try {
			return cpds.getConnection();
		} catch (Exception e) {
			return null;
		}
	}

	public static void closeConnection(ResultSet rs, Statement ps, Connection conn) {
		try {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (Exception e) {
			}

			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e) {
			}

			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
			}

		} catch (Exception e) {

		}
	}
}
