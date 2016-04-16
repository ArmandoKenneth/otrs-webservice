package com.armando.otrs.dao;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.armando.otrs.exception.ArticleException;
import com.armando.otrs.model.Article;
import com.armando.otrs.util.ResponseCodes;

public class ArticleDao extends BaseDao{

	
	public List<Article> getAllTicketArticles(Long ticketId) throws Exception{
		try {
			getAvailabeConnection();
			String sql = "SELECT id, ticket_id, afrom, areply_to, ato, acc, asubject,"
					+ " ain_reply_to, abody, create_time, create_by, change_time, amessage_id, "
					+ "change_by FROM article WHERE ticket_id=?;";
			setPs(getCon().prepareStatement(sql));
			getPs().setLong(1, ticketId);
			setRs(getPs().executeQuery());
			return rsArticles(false);
		} catch (Exception e) {
			throw e;
		} finally {
			closeConnection();
		}
	}
	
	public Article getArticle(Long id) throws Exception{
		try {
			getAvailabeConnection();
			String sql = "SELECT * FROM article WHERE id=?";
			setPs(getCon().prepareStatement(sql));
			setRs(getPs().executeQuery());
			List<Article> articles = rsArticles(true);
			return articles.isEmpty() ? null : articles.get(0);
		} catch (Exception e) {
			throw e;
		} finally {
			closeConnection();
		}
	}
	
	public Article insertArticle(Article article) throws ArticleException, Exception{
		try {
			getAvailabeConnection();
			String sql = "INSERT INTO article(ticket_id, article_type_id, article_sender_type_id, afrom, \n"
                    + "            areply_to, ato, acc, asubject, amessage_id, amessage_id_md5, \n"
                    + "            ain_reply_to, areferences, acontent_type, abody, incoming_time, \n"
                    + "            content_path, valid_id, create_time, create_by, change_time, \n"
                    + "            change_by)\n"
                    + "    VALUES (?, ?, ?, ?, \n"
                    + "            ?, ?, ?, ?, ?, ?, \n"
                    + "            ?, ?, ?, ?, ?, \n"
                    + "            ?, ?, now(), ?,now(), \n"
                    + "            ?);";
			setPs(getCon().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS));
			getPs().setLong(1, article.getTicketId());
			getPs().setShort(2, article.getArticleTypeId());
			getPs().setShort(3, article.getArticleSenderTypeId());
			getPs().setString(4, article.getaFrom());
			getPs().setString(5, article.getaReplyTo());
			getPs().setString(6, article.getaTo());
			getPs().setString(7, article.getaCc());
			getPs().setString(8, article.getaSubject());
			getPs().setString(9, article.getaMessageId());
			getPs().setString(10, article.getaMessageIdMd5());
			getPs().setString(11, article.getaInReplyTo());
			getPs().setString(12, article.getaReferences());
			getPs().setString(13, article.getaContentType());
			getPs().setString(14, article.getaBody());
			getPs().setInt(15, article.getIncomingTime());
			getPs().setString(16, article.getContentPath());
			getPs().setShort(17, article.getValidId());
			getPs().setInt(18, article.getCreateBy());
			getPs().setInt(19, article.getChangeBy());
			
			if (getPs().executeUpdate() < 1){
				throw new ArticleException(ResponseCodes.INTERNAL_ERROR, "Error inserting the article");
			}
			setRs(getPs().getGeneratedKeys());
            if (getRs().next()) {
                article.setId(getRs().getLong(1));
            }
            return article;
		} catch (ArticleException a){
			throw a;
		} catch (SQLException s){
			throw new ArticleException(s);
		} catch (Exception e) {
			throw e;
		} finally {
			closeConnection();
		}
	}
	
	public void deleteArticle(Long id, int changeBy) throws ArticleException, Exception{
		try {
			getAvailabeConnection();
			String sql = "UPDATE article SET valid_id=2, change_time=now(), change_by=? WHERE id=?";
			setPs(getCon().prepareStatement(sql));
			getPs().setInt(1, changeBy);
			getPs().setLong(2, id);
			if (getPs().executeUpdate() < 1){
				throw new ArticleException(ResponseCodes.RESOURCE_NOT_FOUND, "Article not found");
			}
		} catch (ArticleException a){
			throw a;
		} catch (SQLException s){
			throw new ArticleException(s);
		} catch (Exception e) {
			throw new Exception("Erro ao excluir artigos");
		}
	}
	
	private List<Article> rsArticles(boolean isCompleteQuery) throws Exception{
		List<Article> articles = new LinkedList<Article>();
		while (getRs().next()){
			Article article = new Article();
			article.setId(getRs().getLong("id"));
			article.setTicketId(getRs().getLong("ticket_id"));
			article.setaFrom(getRs().getString("afrom"));
			article.setaReplyTo(getRs().getString("areply_to"));
			article.setaTo(getRs().getString("ato"));
			article.setaCc(getRs().getString("acc"));
			article.setaSubject(getRs().getString("asubject"));
			article.setaInReplyTo(getRs().getString("ain_reply_to"));
			article.setaBody(getRs().getString("abody"));
			article.setCreateTime(getRs().getDate("create_time"));
			article.setCreateBy(getRs().getInt("create_by"));
			article.setChangeTime(getRs().getDate("change_time"));
			article.setChangeBy(getRs().getInt("change_by"));
			article.setaMessageId(getRs().getString("amessage_id"));
			if (isCompleteQuery){
				article.setaMessageIdMd5(getRs().getString("amessage_id_md5"));
				article.setaReferences(getRs().getString("areferences"));
				article.setaContentType(getRs().getString("acontent_type"));
				article.setIncomingTime(getRs().getInt("incoming_time"));
				article.setContentPath(getRs().getString("content_path"));
			}
			articles.add(article);
		}
		return articles;
	}
}
