package com.armando.otrs.util;
/**
 * Enum used to store all the values used in the valid_id columns 
 * in various OTRS tables
 * 
 * @author Armando
 *
 */
public enum ValidId {

	VALID(1, "Valid"),
	INVALID(2, "Invalid"),
	INVALID_TEMPORARILY(3, "Invalid temporarily");
	
	private int id;
	private String name;

	ValidId(int id, String name){
		this.id = id;
		this.name = name;
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	
	
}
