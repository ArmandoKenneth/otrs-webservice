package com.armando.otrs.dao.common;

import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * Class that will be used to create all connections to access the database
 * 
 * IMPORTANT: Before using, fill the fields with your own information and, after
 * that rename this file to ConnectionFactory
 * 
 * @author Armando
 *
 */
public class ConnectionFactoryTemplate {

	// Put your driver class here. E.g. "org.postgresql.Driver"
	private String driver = "#";
	// Set here the url to your database E.g. jdbc:postgresql://localhost:5432/
	private String url = "#";
	// Set here the name of your database
	private String database = "#";
	// Set here your credentials
	private String user = "#";
	private String password = "#";

	private ComboPooledDataSource cpds;

	private static ConnectionFactory con = new ConnectionFactory();

	/*
	 * Some options to be used with C3P0 (pooling lib). Feel free to edit,
	 * remove or add atrributes to suit your needs
	 */
	private int innitialPoolSize = 5;
	private int minPoolSize = 5;
	private int acquireIncrement = 5;
	private int maxPoolSize = 30;

	public ConnectionFactory() {
		try {
			startPooledDataSource();
		} catch (Exception e) {

		}
	}

	public ConnectionFactory(String driver, String url, String database, String user, String password) {
		this.driver = driver;
		this.url = url;
		this.database = database;
		this.user = user;
		this.password = password;
		try {
			startPooledDataSource();
		} catch (Exception e) {

		}
	}

	public static ConnectionFactory getInstance() throws SQLException, Exception {
		if (con == null) {
			con = new ConnectionFactory();
		} 
		return con;
	}

	private void startPooledDataSource() throws SQLException, Exception {
		try {
			cpds = new ComboPooledDataSource();
			cpds.setDriverClass(driver);
			cpds.setJdbcUrl(this.url+this.database);
			cpds.setUser(this.user);
			cpds.setPassword(this.password);

			cpds.setInitialPoolSize(this.innitialPoolSize);
			cpds.setAcquireIncrement(this.acquireIncrement);
			cpds.setMaxPoolSize(this.maxPoolSize);
			cpds.setMinPoolSize(this.minPoolSize);
		} catch (Exception e) {
			throw e;
		}
	}

	public Connection getConnection() throws SQLException {
		return this.cpds.getConnection();
	}

}
