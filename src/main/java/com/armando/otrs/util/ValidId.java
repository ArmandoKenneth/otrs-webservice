package com.armando.otrs.util;
/**
 * Enum used to store all the values used in the valid_id columns 
 * in various OTRS tables
 * 
 * @author Armando
 *
 */
public enum ValidId {

	VALID(1),
	INVALID(2),
	INVALID_TEMPORARILY(3);
	
	private int id;

	ValidId(int id){
		this.id = id;
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	
	
}
