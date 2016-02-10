package com.armando.otrs.service.customer;

import java.util.List;

import com.armando.otrs.dao.customer.CustomerUserDao;
import com.armando.otrs.exception.CustomerException;
import com.armando.otrs.model.customer.CustomerUser;
import com.armando.otrs.util.ResponseCodes;

public class CustomerUserService {

	private CustomerUserDao cUserDao;
	
	public CustomerUserService() {
		cUserDao = new CustomerUserDao();
	}
	
	/**
	 * Updates the information about a customer user
	 * @param customerUser				
	 * @return
	 * @throws CustomerException
	 * @throws Exception
	 */
	public CustomerUser updateCustomerUser(CustomerUser customerUser) throws CustomerException, Exception{
		try {
			validateCustomerUser(customerUser);
			return cUserDao.updateCustomerUser(customerUser);
		} catch (CustomerException c) {	
			throw c;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public CustomerUser insertCustomerUser(CustomerUser customerUser) throws CustomerException, Exception{
		try {
			validateCustomerUser(customerUser);
			return cUserDao.insertCustomerUser(customerUser);
		} catch (CustomerException c) {	
			throw c;
		} catch (Exception e) {
			throw e;
		}
	}

	public List<CustomerUser> getAllValidCustomerUser() throws Exception {
		try {
			return cUserDao.getAllValidCustomerUser();
		} catch (CustomerException c) {	
			throw c;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public CustomerUser getCustomerUser(int id) throws Exception {
		try {
			return cUserDao.getCustomerUser(id);
		} catch (CustomerException c) {	
			throw c;
		} catch (Exception e) {
			throw e;
		}
	}
	
	private void validateCustomerUser(CustomerUser customerUser) throws CustomerException{
		if (customerUser == null){
			throw new CustomerException(ResponseCodes.BAD_REQUEST,"Customer user invalid");
		}
		if (customerUser.getLogin() == null || customerUser.getLogin().isEmpty()){
			throw new CustomerException(ResponseCodes.BAD_REQUEST,"Field \"login\" is required");
		}
		if (customerUser.getEmail() == null || customerUser.getEmail().isEmpty()){
			throw new CustomerException(ResponseCodes.BAD_REQUEST,"Field \"email\" is required");
		}
		if (customerUser.getCustomerId() == null || customerUser.getCustomerId().isEmpty()){
			throw new CustomerException(ResponseCodes.BAD_REQUEST,"Field \"customer_id\" is required");
		}
		if (customerUser.getFirstName() == null || customerUser.getFirstName().isEmpty()){
			throw new CustomerException(ResponseCodes.BAD_REQUEST,"Field \"first_name\" is required");
		}
		if (customerUser.getLastName() == null || customerUser.getLastName().isEmpty()){
			throw new CustomerException(ResponseCodes.BAD_REQUEST,"Field \"last_name\" is required");
		}
	}
}