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
import com.armando.otrs.model.article.ArticleAttachment;
import com.armando.otrs.service.article.ArticleAttachmentService;
import com.armando.otrs.util.ResponseCodes;

@Path("/attachments")
public class ArticleAttachmentREST extends BaseREST {

	private ArticleAttachmentService attachmentService;
	
	public ArticleAttachmentREST() {
		attachmentService = new ArticleAttachmentService();
	}
	
	@GET
	@Path("/{id}")
	@Produces(APPLICATION_TYPE_JSON)
	public Response getAttachment(@PathParam("id")Long id){
		String result = "";
		try {
			result = super.json.objectToJson(attachmentService.getAttachment(id));
			setCode(ResponseCodes.SUCCESS);
		} catch (ArticleException a){
			setCode(a.getResponse());
			result = a.getMessage();
		} catch (Exception e) {
			result = super.buildInternalErrorMessage("Error getting the attachment");
		}
		return Response.status(getResponseCode().getCode()).entity(result).build();
	}
	
	@GET
	@Path("/")
	@Produces(APPLICATION_TYPE_JSON)
	public Response getAttachmentsFromArticle(@HeaderParam("article_id")Long articleId){
		String result = "";
		try {
			result = super.json.objectToJson(attachmentService.getAttachmentsFromArticle(articleId));
			setCode(ResponseCodes.SUCCESS);
		} catch (ArticleException a){
			setCode(a.getResponse());
			result = a.getMessage();
		} catch (Exception e) {
			result = super.buildInternalErrorMessage("Error getting the attachments from the article "+articleId);
		}
		return Response.status(getResponseCode().getCode()).entity(result).build();
	}
	
	@POST
	@Path("/")
	@Consumes(APPLICATION_TYPE_JSON)
	@Produces(APPLICATION_TYPE_JSON)
	public Response insertAttachment(ArticleAttachment attachment){
		String result = "";
		try {
			result = super.json.objectToJson(attachmentService.insertAttachment(attachment));
			setCode(ResponseCodes.SUCCESS);
		} catch (ArticleException a){
			setCode(a.getResponse());
			result = a.getMessage();
		} catch (Exception e) {
			result = super.buildInternalErrorMessage("Error inserting the attachment");
		}
		return Response.status(getResponseCode().getCode()).entity(result).build();
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(APPLICATION_TYPE_JSON)
	public Response deleteAttachment(@PathParam("id")Long id) {
		String result = "";
		try {
			attachmentService.deleteAttachment(id);
			result = "Attachmente deleted";
			setCode(ResponseCodes.SUCCESS);
		} catch (ArticleException a){
			setCode(a.getResponse());
			result = a.getMessage();
		} catch (Exception e) {
			result = super.buildInternalErrorMessage("Error deleting the attachment");
		}
		return Response.status(getResponseCode().getCode()).entity(result).build();
	}
}
