package com.armando.otrs.service.customer;

import java.util.List;

import com.armando.otrs.dao.customer.CustomerCompanyDao;
import com.armando.otrs.model.customer.CustomerCompany;
/**
 * Service that will take care of the data validation and business logic related to the CustomerCompany
 * 
 * @author armando.rocha
 *
 */
public class CustomerCompanyService {

	private CustomerCompanyDao ccDao = new CustomerCompanyDao();
	
	
	public List<CustomerCompany> listAllActiveCustomerCompany() throws Exception {
		try {
			return ccDao.listAllActiveCustomerCompany();
		} catch (Exception e) {
			throw new Exception("Error fetching all active customer company");
		}
	}
}
