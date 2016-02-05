package com.armando.otrs.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.armando.otrs.exception.CustomerException;
import com.armando.otrs.service.customer.CustomerCompanyService;
import com.armando.otrs.util.ResponseCodes;

/**
 * Class that will respond to any request that is targeted to the /customercompany context
 * @author armando.rocha
 *
 */
@Path("/customercompany")
public class CustomerCompanyREST extends BaseREST{
	
	private CustomerCompanyService ccService = new CustomerCompanyService();
	
	/**
	 * List all the active CustomerCompany
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
			result = c.getMessage();
		} catch (Exception e) {
			result = super.buildInternalErrorMessage("Error fetching all active CustomerCompany");
		}
		return Response.status(getResponseCode().getCode()).entity(result).build();
	}
	
}
