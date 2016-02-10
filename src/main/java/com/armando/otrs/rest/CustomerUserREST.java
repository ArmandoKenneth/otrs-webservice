package com.armando.otrs.rest;

import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.armando.otrs.exception.CustomerException;
import com.armando.otrs.model.customer.CustomerUser;
import com.armando.otrs.service.customer.CustomerUserService;
import com.armando.otrs.util.ResponseCodes;

@Path("/customerusers")
public class CustomerUserREST extends BaseREST{

	private CustomerUserService cUserService;
	
	public CustomerUserREST() {
		cUserService = new CustomerUserService();
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(APPLICATION_TYPE_JSON)
	@Produces(APPLICATION_TYPE_JSON)
	public Response updateCustomerUser(CustomerUser customerUser){
		String result = "";
		try {
			result = super.json.objectToJson(cUserService.updateCustomerUser(customerUser));
			setCode(ResponseCodes.SUCCESS);
		} catch (CustomerException c){
			setCode(c.getResponse());
			result = c.getMessage();
		} catch (Exception e) {
			result = super.buildInternalErrorMessage("Error updating the selected customer user");
		}
		return Response.status(getResponseCode().getCode()).entity(result).build();
	}
	
	@POST
	@Path("/")
	@Consumes(APPLICATION_TYPE_JSON)
	@Produces(APPLICATION_TYPE_JSON)
	public Response insertCustomerUser(CustomerUser customerUser){
		String result = "";
		try {
			result = super.json.objectToJson(cUserService.insertCustomerUser(customerUser));
			setCode(ResponseCodes.SUCCESS);
		} catch (CustomerException c){
			setCode(c.getResponse());
			result = c.getMessage();
		} catch (Exception e) {
			result = super.buildInternalErrorMessage("Error inserting the customer user");
		}
		return Response.status(getResponseCode().getCode()).entity(result).build();
	}
	
	@GET
	@Path("/")
	@Produces(APPLICATION_TYPE_JSON)
	public Response getAllValidCustomerUser(){
		String result = "";
		try {
			result = super.json.objectToJson(cUserService.getAllValidCustomerUser());
			setCode(ResponseCodes.SUCCESS);
		} catch (CustomerException c){
			setCode(c.getResponse());
			result = c.getMessage();
		} catch (Exception e) {
			result = super.buildInternalErrorMessage("Error getting all valid customer users");
		}
		return Response.status(getResponseCode().getCode()).entity(result).build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(APPLICATION_TYPE_JSON)
	public Response getCustomerUser(@PathParam("id") int id){
		String result = "";
		try {
			result = super.json.objectToJson(cUserService.getCustomerUser(id));
			setCode(ResponseCodes.SUCCESS);
		} catch (CustomerException c){
			setCode(c.getResponse());
			result = c.getMessage();
		} catch (Exception e) {
			result = super.buildInternalErrorMessage("Error getting all valid customer users");
		}
		return Response.status(getResponseCode().getCode()).entity(result).build();
	}
}
