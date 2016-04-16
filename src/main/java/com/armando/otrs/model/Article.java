package com.armando.otrs.model;

import java.sql.Date;

public class Article {

	private Long id;
	private Long ticketId;
	private short articleTypeId;
	private short articleSenderTypeId;
	private String aFrom;
	private String aTo;
	private String aCc;
	private String aReplyTo;
	private String aSubject;
	private String aMessageId;
	private String aMessageIdMd5;
	private String aInReplyTo;
	private String aReferences;
	private String aContentType;
	private String aBody;
	private int incomingTime;
	private String contentPath;
	private short validId;
	private Date createTime;
	private int createBy;
	private Date changeTime;
	private int changeBy;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getTicketId() {
		return ticketId;
	}
	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}
	public short getArticleTypeId() {
		return articleTypeId;
	}
	public void setArticleTypeId(short articleTypeId) {
		this.articleTypeId = articleTypeId;
	}
	public short getArticleSenderTypeId() {
		return articleSenderTypeId;
	}
	public void setArticleSenderTypeId(short articleSenderTypeId) {
		this.articleSenderTypeId = articleSenderTypeId;
	}
	public String getaFrom() {
		return aFrom;
	}
	public void setaFrom(String aFrom) {
		this.aFrom = aFrom;
	}
	public String getaTo() {
		return aTo;
	}
	public void setaTo(String aTo) {
		this.aTo = aTo;
	}
	public String getaCc() {
		return aCc;
	}
	public void setaCc(String aCc) {
		this.aCc = aCc;
	}
	public String getaReplyTo() {
		return aReplyTo;
	}
	public void setaReplyTo(String aReplyTo) {
		this.aReplyTo = aReplyTo;
	}
	public String getaSubject() {
		return aSubject;
	}
	public void setaSubject(String aSubject) {
		this.aSubject = aSubject;
	}
	public String getaMessageId() {
		return aMessageId;
	}
	public void setaMessageId(String aMessageId) {
		this.aMessageId = aMessageId;
	}
	public String getaMessageIdMd5() {
		return aMessageIdMd5;
	}
	public void setaMessageIdMd5(String aMessageIdMd5) {
		this.aMessageIdMd5 = aMessageIdMd5;
	}
	public String getaInReplyTo() {
		return aInReplyTo;
	}
	public void setaInReplyTo(String aInReplyTo) {
		this.aInReplyTo = aInReplyTo;
	}
	public String getaReferences() {
		return aReferences;
	}
	public void setaReferences(String aReferences) {
		this.aReferences = aReferences;
	}
	public String getaContentType() {
		return aContentType;
	}
	public void setaContentType(String aContentType) {
		this.aContentType = aContentType;
	}
	public String getaBody() {
		return aBody;
	}
	public void setaBody(String aBody) {
		this.aBody = aBody;
	}
	public int getIncomingTime() {
		return incomingTime;
	}
	public void setIncomingTime(int incomingTime) {
		this.incomingTime = incomingTime;
	}
	public String getContentPath() {
		return contentPath;
	}
	public void setContentPath(String contentPath) {
		this.contentPath = contentPath;
	}
	public short getValidId() {
		return validId;
	}
	public void setValidId(short validId) {
		this.validId = validId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public int getCreateBy() {
		return createBy;
	}
	public void setCreateBy(int createBy) {
		this.createBy = createBy;
	}
	public Date getChangeTime() {
		return changeTime;
	}
	public void setChangeTime(Date changeTime) {
		this.changeTime = changeTime;
	}
	public int getChangeBy() {
		return changeBy;
	}
	public void setChangeBy(int changeBy) {
		this.changeBy = changeBy;
	}
	
}
