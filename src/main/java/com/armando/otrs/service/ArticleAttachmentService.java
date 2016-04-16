package com.armando.otrs.service;

import java.util.List;

import com.armando.otrs.dao.ArticleAttachmentDao;
import com.armando.otrs.exception.ArticleException;
import com.armando.otrs.model.ArticleAttachment;
import com.armando.otrs.util.ResponseCodes;

public class ArticleAttachmentService {

	private ArticleAttachmentDao attachmentDao;
	
	public ArticleAttachmentService() {
		attachmentDao = new ArticleAttachmentDao();
	}
	
	public ArticleAttachment insertAttachment(ArticleAttachment attachment) throws ArticleException, Exception {
		try {
			if (attachment == null){
				throw new ArticleException(ResponseCodes.BAD_REQUEST, "Invalid attachment");
			}
			if (attachment.getArticleId() == null){
				throw new ArticleException(ResponseCodes.BAD_REQUEST, "Field \"article_id\" is required");
			}
			if (attachment.getContent() == null || attachment.getContent().isEmpty()){
				throw new ArticleException(ResponseCodes.BAD_REQUEST, "Field \"content\" is required");
			}
			return attachmentDao.insertAttachment(attachment);
		} catch (ArticleException a) {
			throw a;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public void deleteAttachment(Long id) throws ArticleException, Exception {
		try {
			if (id == null){
				throw new ArticleException(ResponseCodes.BAD_REQUEST, "Field \"id\" is required");
			}
			attachmentDao.deleteAttachment(id);
		} catch (ArticleException a) {
			throw a;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public List<ArticleAttachment> getAttachmentsFromArticle(Long articleId) throws Exception {
		try {
			if (articleId == null){
				throw new ArticleException(ResponseCodes.BAD_REQUEST, "Field \"article_id\" is required");
			}
			return attachmentDao.getAttachmentsFromArticle(articleId);	
		} catch (ArticleException a) {
			throw a;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public ArticleAttachment getAttachment(Long id) throws Exception {
		try {
			if (id == null){
				throw new ArticleException(ResponseCodes.BAD_REQUEST, "Field \"id\" is required");
			}
			ArticleAttachment attachment = attachmentDao.getAttachment(id);
			if (attachment == null){
				throw new ArticleException(ResponseCodes.RESOURCE_NOT_FOUND, "Attachment not found");
			}
			return attachment;
		} catch (ArticleException a) {
			throw a;
		} catch (Exception e) {
			throw e;
		}
	}
}
