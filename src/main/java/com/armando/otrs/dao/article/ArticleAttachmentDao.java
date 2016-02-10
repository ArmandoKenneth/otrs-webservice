package com.armando.otrs.dao.article;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.armando.otrs.dao.common.BaseDao;
import com.armando.otrs.exception.ArticleException;
import com.armando.otrs.model.article.ArticleAttachment;
import com.armando.otrs.util.ResponseCodes;

public class ArticleAttachmentDao extends BaseDao {

	public ArticleAttachment getAttachment(Long id) throws Exception {
		try {
			getAvailabeConnection();
			String sql = "SELECT id, article_id, filename, content_size, content_type, content_id, \n"
					+ "       content_alternative, disposition, content, create_time, create_by, \n"
					+ "       change_time, change_by\n" + "  FROM article_attachment\n" + "  WHERE id = ?";
			setStatement(getConnection().prepareStatement(sql));
			getStatement().setLong(1, id);
			setResultSet(getStatement().executeQuery());
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
			setStatement(getConnection().prepareStatement(sql));
			getStatement().setLong(1, articleId);
			setResultSet(getStatement().executeQuery());
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
	
			setStatement(getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS));
			getStatement().setLong(1, attachment.getArticleId());
			getStatement().setString(2, attachment.getFilename());
			getStatement().setString(3, attachment.getContentSize());
			getStatement().setString(4, attachment.getContentType());
			getStatement().setString(5, attachment.getContentId());
			
			getStatement().setString(6, attachment.getContentAlternative());
			getStatement().setString(7, attachment.getDisposition());
			getStatement().setString(8, attachment.getContent());
			getStatement().setInt(9, attachment.getCreateBy());
			
			getStatement().setInt(10, attachment.getChangeBy());

			if (getStatement().executeUpdate() < 1){
				throw new ArticleException(ResponseCodes.INTERNAL_ERROR, "Error inserting the attachment");
			}
			setResultSet(getStatement().getGeneratedKeys());
            if (getResultSet().next()) {
            	attachment.setId(getResultSet().getLong(1));
            }
            return attachment;
		} catch (SQLException s){
			if (s.getMessage().contains("content")){
				throw new ArticleException(ResponseCodes.BAD_REQUEST,"Field \"conts\" is required");
			}
			if (s.getMessage().contains("fk_article_attachment_article_id_id")){
				throw new ArticleException(ResponseCodes.BAD_REQUEST,"Value of the field \"article_id\" is invalid");
			}
			if (s.getMessage().contains("fk_article_attachment_change_by_id")){
				throw new ArticleException(ResponseCodes.BAD_REQUEST,"Value of the field \"change_by_id\" is invalid");
			}
			if (s.getMessage().contains("fk_article_attachment_create_by_id")){
				throw new ArticleException(ResponseCodes.BAD_REQUEST,"Value of the field \"create_by_id\" is invalid");
			} 
			throw new Exception(s);
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
			setStatement(getConnection().prepareStatement(sql));
			getStatement().setLong(1, id);
			setResultSet(getStatement().executeQuery());
			if (getStatement().executeUpdate()  < 1){
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
		 while (getResultSet().next()){
			 ArticleAttachment attachment = new ArticleAttachment();
			 attachment.setId(getResultSet().getLong("id"));
			 attachment.setArticleId(getResultSet().getLong("article_id"));
			 attachment.setFilename(getResultSet().getString("filename"));
			 attachment.setContentSize(getResultSet().getString("content_size"));
			 attachment.setContentType(getResultSet().getString("content_type"));
			 attachment.setContentId(getResultSet().getString("content_id"));
			 attachment.setContentAlternative(getResultSet().getString("content_alternative"));
			 attachment.setDisposition(getResultSet().getString("disposition"));
			 attachment.setContent(getResultSet().getString("content"));
			 attachment.setCreateTime(getResultSet().getDate("create_time"));
			 attachment.setCreateBy(getResultSet().getInt("create_by"));
			 attachment.setChangeTime(getResultSet().getDate("change_time"));
			 attachment.setChangeBy(getResultSet().getInt("change_by"));
			 attachments.add(attachment);
		 }
		 
		 return attachments;
	}
}
