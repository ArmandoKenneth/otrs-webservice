package com.armando.otrs.model.article;

import java.sql.Date;

public class ArticleAttachment {
	
	private Long id;
	private Long articleId;
	private String filename;
	private String contentSize;
	private String contentType;
	private String contentId;
	private String contentAlternative;
	private String disposition;
	private String content;
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
	public Long getArticleId() {
		return articleId;
	}
	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getContentSize() {
		return contentSize;
	}
	public void setContentSize(String contentSize) {
		this.contentSize = contentSize;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getContentId() {
		return contentId;
	}
	public void setContentId(String contentId) {
		this.contentId = contentId;
	}
	public String getContentAlternative() {
		return contentAlternative;
	}
	public void setContentAlternative(String contentAlternative) {
		this.contentAlternative = contentAlternative;
	}
	public String getDisposition() {
		return disposition;
	}
	public void setDisposition(String disposition) {
		this.disposition = disposition;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
