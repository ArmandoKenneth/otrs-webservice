package com.armando.otrs.model;

import java.sql.Timestamp;

/**
 * Class that will be inherited by all the models in the webservice. It contains the
 * fields that appear in most of the tables in the database
 * @author Armando
 *
 */
public class BaseModel {
	
	private short validId;
	private Timestamp createTime;
	private int createBy;
	private Timestamp changeTime;
	private int changeBy;
	
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

}
