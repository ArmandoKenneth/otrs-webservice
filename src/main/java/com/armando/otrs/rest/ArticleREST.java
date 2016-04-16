package com.armando.otrs.rest;


import javax.websocket.server.PathParam; 
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.armando.otrs.exception.ArticleException;
import com.armando.otrs.model.Article;
import com.armando.otrs.service.ArticleService;
import com.armando.otrs.util.ResponseCodes;

@Path("/articles")
public class ArticleREST extends BaseREST {

	
	private ArticleService aService;
	
	public ArticleREST() {
		aService = new ArticleService();
	}
	
	@GET
	@Path("/")
	@Produces(APPLICATION_TYPE_JSON)
	public Response getAllTicketArticles(@HeaderParam ("ticket_id") Long ticketId){
		String result = "";
		try {
			result = super.json.objectToJson(aService.getAllTicketArticles(ticketId));
			setCode(ResponseCodes.SUCCESS);
		} catch (ArticleException a){
			setCode(a.getResponseCode());
			result = a.getMessage();
		} catch (Exception e) {
			result = super.buildInternalErrorMessage("Error getting all the articles from the selected ticket");
		}
		return Response.status(getResponseCode().getCode()).entity(result).build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(APPLICATION_TYPE_JSON)
	public Response getArticle(@PathParam("id") Long id){
		String result = "";
		try {
			result = super.json.objectToJson(aService.getArticle(id));
			setCode(ResponseCodes.SUCCESS);
		} catch (ArticleException a){
			setCode(a.getResponseCode());
			result = a.getMessage();
		} catch (Exception e) {
			result = super.buildInternalErrorMessage("Error getting the article");
		}
		return Response.status(getResponseCode().getCode()).entity(result).build();
	}
	
	@POST
	@Path("/")
	@Consumes(APPLICATION_TYPE_JSON)
	@Produces(APPLICATION_TYPE_JSON)
	public Response insertArticle(Article article){
		String result = "";
		try {
			result = super.json.objectToJson(aService.insertArticle(article));
			setCode(ResponseCodes.SUCCESS);
		} catch (ArticleException a){
			setCode(a.getResponseCode());
			result = a.getMessage();
		} catch (Exception e) {
			result = super.buildInternalErrorMessage("Error inserting the article");
		}
		return Response.status(getResponseCode().getCode()).entity(result).build();
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(APPLICATION_TYPE_JSON)
	public Response insertArticle(@PathParam("id") Long id, @HeaderParam("change_by") int changeBy){
		String result = "";
		try {
			aService.deleteArticle(id, changeBy);
			result = "Article deleted";
			setCode(ResponseCodes.SUCCESS);
		} catch (ArticleException a){
			setCode(a.getResponseCode());
			result = a.getMessage();
		} catch (Exception e) {
			result = super.buildInternalErrorMessage("Error deleting the article");
		}
		return Response.status(getResponseCode().getCode()).entity(result).build();
	}
}
