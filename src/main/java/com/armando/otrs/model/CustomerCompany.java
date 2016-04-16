package com.armando.otrs.model;

import java.sql.Timestamp;

/**
 * Represents the customer_company table
 * 
 * @author Armando
 *
 */
public class CustomerCompany {

	private String customerId;
	private String name;
	private String street;
	private String zip;
	private String city;
	private String country;
	private String url;
	private String comments;
	private short validId;
	private Timestamp createTime;
	private int createBy;
	private Timestamp changeTime;
	private int changeBy;
	/**
	 * @return the customerId
	 */
	public String getCustomerId() {
		return customerId;
	}
	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}
	/**
	 * @param street the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}
	/**
	 * @return the zip
	 */
	public String getZip() {
		return zip;
	}
	/**
	 * @param zip the zip to set
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}
	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}
	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	/**
	 * @return the validId
	 */
	public short getValidId() {
		return validId;
	}
	/**
	 * @param validId the validId to set
	 */
	public void setValidId(short validId) {
		this.validId = validId;
	}
	/**
	 * @return the createTime
	 */
	public Timestamp getCreateTime() {
		return createTime;
	}
	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	/**
	 * @return the createBy
	 */
	public int getCreateBy() {
		return createBy;
	}
	/**
	 * @param createBy the createBy to set
	 */
	public void setCreateBy(int createBy) {
		this.createBy = createBy;
	}
	/**
	 * @return the changeTime
	 */
	public Timestamp getChangeTime() {
		return changeTime;
	}
	/**
	 * @param changeTime the changeTime to set
	 */
	public void setChangeTime(Timestamp changeTime) {
		this.changeTime = changeTime;
	}
	/**
	 * @return the changeBy
	 */
	public int getChangeBy() {
		return changeBy;
	}
	/**
	 * @param changeBy the changeBy to set
	 */
	public void setChangeBy(int changeBy) {
		this.changeBy = changeBy;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	
	
	
	
}
