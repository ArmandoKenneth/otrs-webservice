package com.armando.otrs.dao.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Base class to be extends by all Dao objects that will be implemented. It
 * contains methods and objets that are used in multiple Dao objects,
 * 
 * @author Armando
 *
 */
public class BaseDao {

	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet resultSet = null;
		
	protected void getAvailabeConnection(){
		try {
			connection = ConnectionFactory.getInstance().getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void closeConnection(){
		try {
            getResultSet().close();
        } catch (SQLException e) {
            //throw e;
        } catch (Exception e) {

        }
        try {
            getStatement().close();
        } catch (SQLException e) {
            //throw e;
        } catch (Exception e) {

        }
        try {
            getConnection().close();
        } catch (SQLException e) {
            //throw e;
        } catch (Exception e) {

        }

	}
	
	public PreparedStatement getStatement() {
		return statement;
	}

	public void setStatement(PreparedStatement statement) {
		this.statement = statement;
	}

	public ResultSet getResultSet() {
		return resultSet;
	}

	public void setResultSet(ResultSet resultSet) {
		this.resultSet = resultSet;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public Connection getConnection() {
		return connection;
	}

}
