package com.armando.otrs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.armando.otrs.config.DbConnection;


/**
 * Base class to be extends by all Dao objects that will be implemented. It
 * contains methods and objets that will be used to access the database
 * 
 * @author Armando
 *
 */
public class BaseDao {

	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	
	protected void closeConnection(){
		DbConnection.closeConnection(getRs(), getPs(), getCon());
	}
	
	protected void getAvailabeConnection(){
		setCon(DbConnection.getInstance().getConnection());
	}

	/**
	 * @return the con
	 */
	public Connection getCon() {
		return con;
	}

	/**
	 * @param con the con to set
	 */
	public void setCon(Connection con) {
		this.con = con;
	}

	/**
	 * @return the ps
	 */
	public PreparedStatement getPs() {
		return ps;
	}

	/**
	 * @param ps the ps to set
	 */
	public void setPs(PreparedStatement ps) {
		this.ps = ps;
	}

	/**
	 * @return the rs
	 */
	public ResultSet getRs() {
		return rs;
	}

	/**
	 * @param rs the rs to set
	 */
	public void setRs(ResultSet rs) {
		this.rs = rs;
	}

}
