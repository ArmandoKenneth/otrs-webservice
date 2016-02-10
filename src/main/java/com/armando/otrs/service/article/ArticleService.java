package com.armando.otrs.service.article;

import java.util.List;

import com.armando.otrs.dao.article.ArticleDao;
import com.armando.otrs.exception.ArticleException;
import com.armando.otrs.model.article.Article;
import com.armando.otrs.util.ResponseCodes;

public class ArticleService {

	private ArticleDao aDao;
	
	public ArticleService() {
		aDao = new ArticleDao();
	}
	
	public List<Article> getAllTicketArticles(Long ticketId) throws ArticleException, Exception{
		try {
			if (ticketId == null){
				throw new ArticleException(ResponseCodes.BAD_REQUEST, "Field \"ticket_id\" is required");
			}
			return aDao.getAllTicketArticles(ticketId);
		} catch (ArticleException a) {
			throw a;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public Article getArticle(Long id) throws Exception{
		try {
			if (id == null){
				throw new ArticleException(ResponseCodes.BAD_REQUEST, "Field \"id\" is required");
			}
			Article article = aDao.getArticle(id);
			if (article == null){
				throw new ArticleException(ResponseCodes.RESOURCE_NOT_FOUND, "Article not found");
			}
			return article;
		} catch (ArticleException a) {
			throw a;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public Article insertArticle(Article article) throws ArticleException, Exception{
		try {
			if (article == null){
				throw new ArticleException(ResponseCodes.BAD_REQUEST, "Invalid article");
			}
			if (article.getTicketId() == null){
				throw new ArticleException(ResponseCodes.BAD_REQUEST, "Field \"ticket_id\" is required");
			}
			return aDao.insertArticle(article);
		} catch (ArticleException a) {
			throw a;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public void deleteArticle(Long id, int changeBy) throws ArticleException, Exception{
		try {
			if (id == null){
				throw new ArticleException(ResponseCodes.BAD_REQUEST, "Field \"id\" is required");
			}
			aDao.deleteArticle(id, changeBy);
		} catch (ArticleException a) {
			throw a;
		} catch (Exception e) {
			throw e;
		}
		
	}
}
