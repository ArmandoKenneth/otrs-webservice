package com.armando.otrs.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.armando.otrs.exception.CustomerException;
import com.armando.otrs.model.CustomerCompany;
import com.armando.otrs.service.CustomerCompanyService;
import com.armando.otrs.util.ResponseCodes;

/**
 * Class that will respond to any request that is targeted to the /customercompany context
 * @author armando.rocha
 *
 */
@Path("/customercompanies")
public class CustomerCompanyREST extends BaseREST{
	
	private CustomerCompanyService ccService = new CustomerCompanyService();
	
	/**
	 * List all the active customer companies
	 * @return			JSON with all the active CustomerCompany
	 */
	@GET
	@Path("/")
	@Produces(APPLICATION_TYPE_JSON)
	public Response listAllActiveCustomerCompany(){
		String result = "";
		try {
			result = super.json.objectToJson(ccService.listAllActiveCustomerCompany());
			setCode(ResponseCodes.SUCCESS);
		} catch (CustomerException c){
			setCode(c.getResponseCode());
			result = c.getMessage();
		} catch (Exception e) {
			result = super.buildInternalErrorMessage("Error fetching all active CustomerCompany");
		}
		return Response.status(getResponseCode().getCode()).entity(result).build();
	}
	
	/**
	 * Get the selected customer company
	 * @param customerId	The customer_id of the customer company to get
	 * @return				JSON with the customer company
	 */
	@GET
	@Path("/{customer_id}")
	@Produces(APPLICATION_TYPE_JSON)
	public Response getCustomerCompany(@PathParam("customer_id") String customerId){
		String result = "";
		try {
			result = super.json.objectToJson(ccService.getCustomerCompany(customerId));
			setCode(ResponseCodes.SUCCESS);
		} catch (CustomerException c){
			setCode(c.getResponseCode());
			result = c.getMessage();
		} catch (Exception e) {
			result = super.buildInternalErrorMessage("Error getting customer company");
		}
		return Response.status(getResponseCode().getCode()).entity(result).build();
	}
	
	/**
	 * Insert a new customer company with the data received via JSON
	 * @param customerCompany		The customer company data that was received via JSON
	 * @return						The customer company object with the data inserted
	 */
	@POST
	@Path("/")
	@Consumes(APPLICATION_TYPE_JSON)
	@Produces(APPLICATION_TYPE_JSON)
	public Response insertCustomerCompany(CustomerCompany customerCompany){
		String result = "";
		try {
			result = super.json.objectToJson(ccService.insertCustomerCompany(customerCompany));
			setCode(ResponseCodes.SUCCESS);
		} catch (CustomerException c){
			setCode(c.getResponseCode());
			result = c.getMessage();
		} catch (Exception e) {
			result = super.buildInternalErrorMessage("Error inserting the customer company");
		}
		return Response.status(getResponseCode().getCode()).entity(result).build(); 
	}
	
	/**
	 * Edit a customer company
	 * @param originalCustomerId
	 * @param customerCompany
	 * @return
	 */
	@PUT
	@Path("/{customer_id}")
	@Consumes(APPLICATION_TYPE_JSON)
	@Produces(APPLICATION_TYPE_JSON)
	public Response editCustomerCompany(@PathParam("customer_id") String originalCustomerId, CustomerCompany customerCompany){
		String result = "";
		try {
			result = super.json.objectToJson(ccService.editCustomerCompany(originalCustomerId, customerCompany));
			setCode(ResponseCodes.SUCCESS);
		} catch (CustomerException c){
			setCode(c.getResponseCode());
			result = c.getMessage();
		} catch (Exception e) {
			result = super.buildInternalErrorMessage("Error inserting the customer company");
		}
		return Response.status(getResponseCode().getCode()).entity(result).build(); 
	}
}
