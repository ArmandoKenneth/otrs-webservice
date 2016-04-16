package com.armando.otrs.model;

public class Sla extends BaseModel{
	
	private int id;
	private String name;
	private String calendarName;
	private int firstResponseTime;
	private short firstResponseNotify;
	private int updateTime;
	private short updateNotify;
	private int solutionTime;
	private short solutionNotify;
	private String comments;
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
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
	 * @return the calendarName
	 */
	public String getCalendarName() {
		return calendarName;
	}
	/**
	 * @param calendarName the calendarName to set
	 */
	public void setCalendarName(String calendarName) {
		this.calendarName = calendarName;
	}
	/**
	 * @return the firstResponseTime
	 */
	public int getFirstResponseTime() {
		return firstResponseTime;
	}
	/**
	 * @param firstResponseTime the firstResponseTime to set
	 */
	public void setFirstResponseTime(int firstResponseTime) {
		this.firstResponseTime = firstResponseTime;
	}
	/**
	 * @return the firstResponseNotify
	 */
	public short getFirstResponseNotify() {
		return firstResponseNotify;
	}
	/**
	 * @param firstResponseNotify the firstResponseNotify to set
	 */
	public void setFirstResponseNotify(short firstResponseNotify) {
		this.firstResponseNotify = firstResponseNotify;
	}
	/**
	 * @return the updateTime
	 */
	public int getUpdateTime() {
		return updateTime;
	}
	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(int updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * @return the updateNotify
	 */
	public short getUpdateNotify() {
		return updateNotify;
	}
	/**
	 * @param updateNotify the updateNotify to set
	 */
	public void setUpdateNotify(short updateNotify) {
		this.updateNotify = updateNotify;
	}
	/**
	 * @return the solutionTime
	 */
	public int getSolutionTime() {
		return solutionTime;
	}
	/**
	 * @param solutionTime the solutionTime to set
	 */
	public void setSolutionTime(int solutionTime) {
		this.solutionTime = solutionTime;
	}
	/**
	 * @return the solutionNotify
	 */
	public short getSolutionNotify() {
		return solutionNotify;
	}
	/**
	 * @param solutionNotify the solutionNotify to set
	 */
	public void setSolutionNotify(short solutionNotify) {
		this.solutionNotify = solutionNotify;
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
	

}
