package com.armando.otrs.service.customer;

import java.util.List;

import com.armando.otrs.dao.customer.CustomerCompanyDao;
import com.armando.otrs.exception.CustomerException;
import com.armando.otrs.model.customer.CustomerCompany;
import com.armando.otrs.util.ResponseCodes;
/**
 * Service that will take care of the data validation and business logic related to the CustomerCompany
 * 
 * @author armando.rocha
 *
 */
public class CustomerCompanyService {

	private CustomerCompanyDao ccDao = new CustomerCompanyDao();
	
	/**
	 * List all the active customer companies
	 * @return						List of CustomerCompany with all active customer companies
	 * @throws Exception
	 */
	public List<CustomerCompany> listAllActiveCustomerCompany() throws Exception {
		try {
			return ccDao.listAllActiveCustomerCompany();
		} catch (Exception e) {
			throw new Exception("Error fetching all active customer companies");
		}
	}
	
	/**
	 * Fetch the selected customer company
	 * @param customerId				customerId of the selected customer company					
	 * @return							CustomerCompany object with the data
	 * @throws CustomerException		Known errors with codes and customised message
	 * @throws Exception				
	 */
	public CustomerCompany getCustomerCompany(String customerId) throws CustomerException, Exception{
		try {
			if (customerId == null || customerId.isEmpty()){
				throw new CustomerException(ResponseCodes.BAD_REQUEST, "Field \"customer_id\" is required");
			}
			CustomerCompany customerCompany = ccDao.getCustomerCompany(customerId);
			if (customerCompany == null){
				throw new CustomerException(ResponseCodes.RESOURCE_NOT_FOUND, "Customer company not found"); 
			}
			return customerCompany;
		} catch (CustomerException c) {	
			throw c;
		} catch (Exception e) {
			throw new Exception("Error fetching the selected customer company");
		}
	}
	
	/**
	 * Insert a new customer company
	 * @param customer				CustomerCompany to be inserted
	 * @return						CustomerCompany with the generated customer_id
	 * @throws CustomerException	Known errors with codes and customised message
	 * @throws Exception
	 */
	public CustomerCompany insertCustomerCompany(CustomerCompany customer) throws CustomerException, Exception{
		try {
			if (customer == null){
				throw new Exception("Error inserting customer company");
			}
			if (customer.getCustomerId() == null || customer.getCustomerId().isEmpty()){
				throw new CustomerException(ResponseCodes.BAD_REQUEST, "Field \"customer_id\" is required");
			}
			if (customer.getName() == null || customer.getName().isEmpty()){
				throw new CustomerException(ResponseCodes.BAD_REQUEST, "Field \"name\" is required");
			}
			return ccDao.insertCustomerCompany(customer);
		} catch (CustomerException c) {	
			throw c;
		} catch (Exception e) {
			throw new Exception("Error inserting customer company");
		}
	}
	
	/**
	 * Edit a existing customer company
	 * @param originalCustomerId    customer_id if the customer company to be edited
	 * @param customer				New data of the customer company
	 * @return						CustomerCompany object with the updated data
	 * @throws CustomerException	
	 * @throws Exception
	 */
	public CustomerCompany editCustomerCompany(String originalCustomerId, CustomerCompany customer) throws CustomerException, Exception{
		try {
			if (customer == null){
				throw new Exception("Error inserting customer company");
			}
			if (originalCustomerId == null || originalCustomerId.isEmpty()){
				throw new CustomerException(ResponseCodes.BAD_REQUEST, "Field \"original_customer_id\" is required");
			}
			return ccDao.editCustomerCompany(originalCustomerId, customer);
		} catch (CustomerException c) {	
			throw c;
		} catch (Exception e) {
			throw new Exception("Error editing customer company");
		}
	}
	
	
}
