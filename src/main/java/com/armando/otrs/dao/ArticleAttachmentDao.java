package com.armando.otrs.dao;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.armando.otrs.exception.ArticleException;
import com.armando.otrs.model.ArticleAttachment;
import com.armando.otrs.util.ResponseCodes;


public class ArticleAttachmentDao extends BaseDao {

	public ArticleAttachment getAttachment(Long id) throws Exception {
		try {
			getAvailabeConnection();
			String sql = "SELECT id, article_id, filename, content_size, content_type, content_id, \n"
					+ "       content_alternative, disposition, content, create_time, create_by, \n"
					+ "       change_time, change_by\n" + "  FROM article_attachment\n" + "  WHERE id = ?";
			setPs(getCon().prepareStatement(sql));
			getPs().setLong(1, id);
			setRs(getPs().executeQuery());
			List<ArticleAttachment> attachments = rsAttachments();
			return attachments.isEmpty() ?  null : attachments.get(0);
		} catch (Exception e) {
			throw e;
		} finally {
			closeConnection();
		}
	}

	public List<ArticleAttachment> getAttachmentsFromArticle(Long articleId) throws Exception {
		try {
			getAvailabeConnection();
			String sql = "SELECT id, article_id, filename, content_size, content_type, content_id, \n" +
					"       content_alternative, disposition, content, create_time, create_by, \n" +
					"       change_time, change_by\n" +
					"  FROM article_attachment\n" +
					"  WHERE article_id = ?";
			setPs(getCon().prepareStatement(sql));
			getPs().setLong(1, articleId);
			setRs(getPs().executeQuery());
			return rsAttachments();
		} catch (Exception e) {
			throw e;
		} finally {
			closeConnection();
		}
	}
	
	public ArticleAttachment insertAttachment(ArticleAttachment attachment) throws ArticleException, Exception {
		try {
			getAvailabeConnection();
			String sql = "INSERT INTO article_attachment "
					+ "			 (article_id, filename, content_size, content_type, content_id, \n" +
					"            content_alternative, disposition, content, create_time, create_by, \n" +
					"            change_time, change_by)\n" +
					"    VALUES (?, ?, ?, ?, ?, \n" +
					"            ?, ?, ?, now(), ?, \n" +
					"            now(), ?);";
	
			setPs(getCon().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS));
			getPs().setLong(1, attachment.getArticleId());
			getPs().setString(2, attachment.getFilename());
			getPs().setString(3, attachment.getContentSize());
			getPs().setString(4, attachment.getContentType());
			getPs().setString(5, attachment.getContentId());
			
			getPs().setString(6, attachment.getContentAlternative());
			getPs().setString(7, attachment.getDisposition());
			getPs().setString(8, attachment.getContent());
			getPs().setInt(9, attachment.getCreateBy());
			
			getPs().setInt(10, attachment.getChangeBy());

			if (getPs().executeUpdate() < 1){
				throw new ArticleException(ResponseCodes.INTERNAL_ERROR, "Error inserting the attachment");
			}
			setRs(getPs().getGeneratedKeys());
            if (getRs().next()) {
            	attachment.setId(getRs().getLong(1));
            }
            return attachment;
		} catch (SQLException s){
			throw new ArticleException(s);
		} catch (ArticleException a){
			throw a;
		} catch (Exception e) {
			throw e;
		} finally {
			closeConnection();
		}
	}
	
	public void deleteAttachment(Long id) throws ArticleException, Exception {
		try {
			getAvailabeConnection();
			String sql = "DELETE FROM article_attachment WHERE id =?";
			setPs(getCon().prepareStatement(sql));
			getPs().setLong(1, id);
			setRs(getPs().executeQuery());
			if (getPs().executeUpdate()  < 1){
				throw new ArticleException(ResponseCodes.RESOURCE_NOT_FOUND, "Attachment not found");
			}
		} catch (ArticleException a){
			throw a;
		} catch (Exception e) {
			throw e;
		} finally {
			closeConnection();
		}
	}
	
	private List<ArticleAttachment> rsAttachments() throws Exception{
		 List<ArticleAttachment> attachments = new LinkedList<ArticleAttachment>();
		 while (getRs().next()){
			 ArticleAttachment attachment = new ArticleAttachment();
			 attachment.setId(getRs().getLong("id"));
			 attachment.setArticleId(getRs().getLong("article_id"));
			 attachment.setFilename(getRs().getString("filename"));
			 attachment.setContentSize(getRs().getString("content_size"));
			 attachment.setContentType(getRs().getString("content_type"));
			 attachment.setContentId(getRs().getString("content_id"));
			 attachment.setContentAlternative(getRs().getString("content_alternative"));
			 attachment.setDisposition(getRs().getString("disposition"));
			 attachment.setContent(getRs().getString("content"));
			 attachment.setCreateTime(getRs().getDate("create_time"));
			 attachment.setCreateBy(getRs().getInt("create_by"));
			 attachment.setChangeTime(getRs().getDate("change_time"));
			 attachment.setChangeBy(getRs().getInt("change_by"));
			 attachments.add(attachment);
		 }
		 
		 return attachments;
	}
}
