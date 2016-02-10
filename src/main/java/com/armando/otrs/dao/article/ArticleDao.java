package com.armando.otrs.dao.article;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.armando.otrs.dao.common.BaseDao;
import com.armando.otrs.exception.ArticleException;
import com.armando.otrs.model.article.Article;
import com.armando.otrs.util.ResponseCodes;

public class ArticleDao extends BaseDao{

	
	public List<Article> getAllTicketArticles(Long ticketId) throws Exception{
		try {
			getAvailabeConnection();
			String sql = "SELECT id, ticket_id, afrom, areply_to, ato, acc, asubject,"
					+ " ain_reply_to, abody, create_time, create_by, change_time, amessage_id, "
					+ "change_by FROM article WHERE ticket_id=?;";
			setStatement(getConnection().prepareStatement(sql));
			getStatement().setLong(1, ticketId);
			setResultSet(getStatement().executeQuery());
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
			setStatement(getConnection().prepareStatement(sql));
			setResultSet(getStatement().executeQuery());
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
			setStatement(getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS));
			getStatement().setLong(1, article.getTicketId());
			getStatement().setShort(2, article.getArticleTypeId());
			getStatement().setShort(3, article.getArticleSenderTypeId());
			getStatement().setString(4, article.getaFrom());
			getStatement().setString(5, article.getaReplyTo());
			getStatement().setString(6, article.getaTo());
			getStatement().setString(7, article.getaCc());
			getStatement().setString(8, article.getaSubject());
			getStatement().setString(9, article.getaMessageId());
			getStatement().setString(10, article.getaMessageIdMd5());
			getStatement().setString(11, article.getaInReplyTo());
			getStatement().setString(12, article.getaReferences());
			getStatement().setString(13, article.getaContentType());
			getStatement().setString(14, article.getaBody());
			getStatement().setInt(15, article.getIncomingTime());
			getStatement().setString(16, article.getContentPath());
			getStatement().setShort(17, article.getValidId());
			getStatement().setInt(18, article.getCreateBy());
			getStatement().setInt(19, article.getChangeBy());
			
			if (getStatement().executeUpdate() < 1){
				throw new ArticleException(ResponseCodes.INTERNAL_ERROR, "Error inserting the article");
			}
			setResultSet(getStatement().getGeneratedKeys());
            if (getResultSet().next()) {
                article.setId(getResultSet().getLong(1));
            }
            return article;
		} catch (ArticleException a){
			throw a;
		} catch (SQLException s){
			analiseSqlException(s);
			throw new Exception(s);
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
			setStatement(getConnection().prepareStatement(sql));
			getStatement().setInt(1, changeBy);
			getStatement().setLong(2, id);
			if (getStatement().executeUpdate() < 1){
				throw new ArticleException(ResponseCodes.RESOURCE_NOT_FOUND, "Article not found");
			}
		} catch (ArticleException a){
			throw a;
		} catch (SQLException s){
			if (s.getMessage().contains("attachment")){
				throw new ArticleException(ResponseCodes.RESOURCE_CONFLICT, "There are attachments related to this articles");
			}
		} catch (Exception e) {
			throw new Exception("Erro ao excluir artigos");
		}
	}
	
	private List<Article> rsArticles(boolean isCompleteQuery) throws Exception{
		List<Article> articles = new LinkedList<Article>();
		while (getResultSet().next()){
			Article article = new Article();
			article.setId(getResultSet().getLong("id"));
			article.setTicketId(getResultSet().getLong("ticket_id"));
			article.setaFrom(getResultSet().getString("afrom"));
			article.setaReplyTo(getResultSet().getString("areply_to"));
			article.setaTo(getResultSet().getString("ato"));
			article.setaCc(getResultSet().getString("acc"));
			article.setaSubject(getResultSet().getString("asubject"));
			article.setaInReplyTo(getResultSet().getString("ain_reply_to"));
			article.setaBody(getResultSet().getString("abody"));
			article.setCreateTime(getResultSet().getDate("create_time"));
			article.setCreateBy(getResultSet().getInt("create_by"));
			article.setChangeTime(getResultSet().getDate("change_time"));
			article.setChangeBy(getResultSet().getInt("change_by"));
			article.setaMessageId(getResultSet().getString("amessage_id"));
			if (isCompleteQuery){
				article.setaMessageIdMd5(getResultSet().getString("amessage_id_md5"));
				article.setaReferences(getResultSet().getString("areferences"));
				article.setaContentType(getResultSet().getString("acontent_type"));
				article.setIncomingTime(getResultSet().getInt("incoming_time"));
				article.setContentPath(getResultSet().getString("content_path"));
			}
			articles.add(article);
		}
		return articles;
	}
	
	private void analiseSqlException(SQLException s) throws ArticleException, Exception{
		if (s.getMessage().contains("fk_article_article_sender_type_id_id")){
			throw new ArticleException("Value of the field \"article_sender_type_id\" is invalid");
		}
		if (s.getMessage().contains("fk_article_article_type_id_id")){
			throw new ArticleException("Value of the field \"article_type_id\" is invalid");
		}
		if (s.getMessage().contains("fk_article_change_by_id")){
			throw new ArticleException("Value of the field \"change_by\" is invalid");
		}
		if (s.getMessage().contains("fk_article_create_by_id")){
			throw new ArticleException("Value of the field \"create_by\" is invalid");
		}
		if (s.getMessage().contains("fk_article_ticket_id_id")){
			throw new ArticleException("Value of the field \"ticket_id\" is invalid");
		}
		if (s.getMessage().contains("fk_article_valid_id_id")){
			throw new ArticleException("Value of the field \"valid_id\" is invalid");
		}
	}
}
