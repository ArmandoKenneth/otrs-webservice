package com.armando.otrs.dao.customer;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.armando.otrs.dao.common.BaseDao;
import com.armando.otrs.exception.CustomerException;
import com.armando.otrs.model.customer.CustomerCompany;
import com.armando.otrs.util.ResponseCodes;
import com.armando.otrs.util.ValidId;

/**
 * Class that will access the database and get information about the
 * customer_company
 * 
 * @author Armando
 *
 */

public class CustomerCompanyDao extends BaseDao {
	/**
	 * Validates (set the valid_id attribute to VALID) a customer_company
	 * 
	 * @param customerId
	 *            customer_id of the customer_company to be modified
	 * @param changeBy
	 *            id of the user that is validating the customer_company
	 * @throws CustomerException
	 *             Raised if no line was updated
	 * @throws Exception
	 */
	public void validateCustomerCompany(String customerId, int changeBy) throws CustomerException, Exception {
		invalidate(customerId, changeBy, ValidId.VALID.getId());
	}

	/**
	 * Invalidates (set the valid_id attribute to INVALID) a customer_company
	 * 
	 * @param customerId
	 *            customer_id of the customer_company to be modified
	 * @param changeBy
	 *            id of the user that is invalidating the customer_company
	 * @throws CustomerException
	 *             Raised if no line was updated
	 * @throws Exception
	 */
	public void invalidateCustomerCompany(String customerId, int changeBy) throws CustomerException, Exception {
		invalidate(customerId, changeBy, ValidId.INVALID.getId());
	}

	/**
	 * Invalidates temporarily (set the valid_id attribute to
	 * INVALID_TEMPORARILY) a customer_company
	 * 
	 * @param customerId
	 *            customer_id of the customer_company to be modified
	 * @param changeBy
	 *            id of the user that is invalidating the customer_company
	 * @throws CustomerException
	 *             Raised if no line was updated
	 * @throws Exception
	 */
	public void invalidateCustomerCompanyTemporarily(String customerId, int changeBy)
			throws CustomerException, Exception {
		invalidate(customerId, changeBy, ValidId.INVALID_TEMPORARILY.getId());
	}

	private void invalidate(String customerId, int changeBy, int validId) throws CustomerException, Exception {
		try {
			getAvailabeConnection();
			String sql = "UPDATE customer_company SET valid_id=?, change_time=now(), change_by=? WHERE customer_id=?";
			setStatement(getConnection().prepareStatement(sql));
			getStatement().setInt(1, validId);
			getStatement().setInt(2, changeBy);
			getStatement().setString(3, customerId);

			if (getStatement().executeUpdate() < 1) {
				throw new CustomerException(ResponseCodes.RESOURCE_NOT_FOUND,"Customer company not found");
			}
		} catch (CustomerException c) {
			throw c;
		} catch (SQLException s) {
			if (s.getMessage().contains("change_by")){
				throw new CustomerException(ResponseCodes.BAD_REQUEST,"Value of the field \"change_by\" is invalid");
			}
			if (s.getMessage().contains("valid_id")){
				throw new CustomerException(ResponseCodes.BAD_REQUEST,"Value of the field \"valid_id\" is invalid");
			}
			throw new Exception(s);
		} catch (Exception e) {
			throw e;
		} finally {
			closeConnection();
		}
	}

	/**
	 * Insert a new customer_company to the database
	 * 
	 * @param customer
	 *            customer_company to be inserted
	 * @return customer_company that was inserter with the attribute customer_id
	 *         filled
	 * @throws CustomerException
	 *             Raised if no new customer_company was inserted (this may be
	 *             caused by unique keys validation in database or different
	 *             data type)
	 * @throws Exception
	 */
	public CustomerCompany insertCustomerCompany(CustomerCompany customer) throws CustomerException, Exception {
		try {
			getAvailabeConnection();
			String sql = "INSERT INTO customer_company(customer_id, name, street, zip, city, country, url, comments, "
					+ "valid_id, create_time, create_by, change_time, change_by) "
					+ "VALUES (?,?,?,?,?,?,?,?,?,now(),?, now(), ?);";
			setStatement(getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS));
			getStatement().setString(1, customer.getCustomerId());
			getStatement().setString(2, customer.getName());
			getStatement().setString(3, customer.getStreet());
			getStatement().setString(4, customer.getZip());
			getStatement().setString(5, customer.getCity());
			getStatement().setString(6, customer.getCountry());
			getStatement().setString(7, customer.getUrl());
			getStatement().setString(8, customer.getComments());
			getStatement().setShort(9, customer.getValidId());
			getStatement().setInt(10, customer.getCreateBy());
			getStatement().setInt(11, customer.getChangeBy());
			int modifiedRows = getStatement().executeUpdate();
			if (modifiedRows < 1) {
				throw new Exception("Error inserting the Customer Company");
			}
			setResultSet(getStatement().getGeneratedKeys());
			if (getResultSet().next()) {
				customer.setCustomerId(getResultSet().getString(1));
			}
		} catch (CustomerException c) {
			throw c;
		} catch (SQLException s) {
			analiseSqlException(s);
		} catch (Exception e) {
			throw e;
		} finally {
			closeConnection();
		}
		return customer;
	}

	/**
	 * Edit a existing customer_company
	 * 
	 * @param originalCustomerId
	 *            customer_id of the customer_company to be modified. This
	 *            necessary due to the fact that the customer_id can be
	 *            modified, so we need to keep track of the current customer_id
	 * @param customer
	 *            Contains the new data of the customer_company
	 * @throws CustomerException
	 *             Raised if no customer_company was modified (invalid
	 *             customer_id) or validation error occurs (this may be caused
	 *             by unique keys validation in database or different data type)
	 * @throws Exception
	 */
	public CustomerCompany editCustomerCompany(String originalCustomerId, CustomerCompany customer)
			throws CustomerException, Exception {
		try {
			getAvailabeConnection();
			String sql = "UPDATE customer_company\n"
					+ "   SET customer_id = ?, name=?, street=?, zip=?, city=?, country=?, url=?, \n"
					+ "       comments=?, valid_id=?, change_time=now(), \n" + "       change_by=?\n"
					+ " WHERE customer_id=?;";
			setStatement(getConnection().prepareStatement(sql));
			if (customer.getCustomerId() == null || customer.getCustomerId().isEmpty()){
				getStatement().setString(1, originalCustomerId);
			}else{
				getStatement().setString(1, customer.getCustomerId());
			}
			getStatement().setString(2, customer.getName());
			getStatement().setString(3, customer.getStreet());
			getStatement().setString(4, customer.getZip());
			getStatement().setString(5, customer.getCity());
			getStatement().setString(6, customer.getCountry());
			getStatement().setString(7, customer.getUrl());

			getStatement().setString(8, customer.getComments());
			getStatement().setShort(9, customer.getValidId());

			getStatement().setInt(10, customer.getChangeBy());

			getStatement().setString(11, originalCustomerId);
			int modifiedRows = getStatement().executeUpdate();
			if (modifiedRows < 1) {
				throw new CustomerException(ResponseCodes.BAD_REQUEST,"Customer company not found");
			}
			if (customer.getCustomerId() == null || customer.getCustomerId().isEmpty()){
				customer.setCustomerId(originalCustomerId);
			}
		} catch (CustomerException c) {
			throw c;
		} catch (SQLException s) {
			if (s.getMessage().contains("change_by")){
				throw new CustomerException(ResponseCodes.BAD_REQUEST,"Value of the field \"change_by\" is invalid");
			}
			if (s.getMessage().contains("valid_id")){
				throw new CustomerException(ResponseCodes.BAD_REQUEST,"Value of the field \"valid_id\" is invalid");
			}
			analiseSqlException(s);
		} catch (Exception e) {
			throw e;
		} finally {
			closeConnection();
		}
		return customer;
	}

	/**
	 * Analise the SQLException that was raised and returns the correct message
	 * (if it was as known cause)
	 * 
	 * @param s
	 *            SqlException raised
	 * @throws CustomerException
	 *             CustomerException with a modified, friendly message
	 * @throws Exception
	 *             If the SQLException was raised by a unknow cause
	 */
	private void analiseSqlException(SQLException s) throws CustomerException, Exception {
		if (s.getMessage().contains("customer_company_name")) {
			throw new CustomerException(ResponseCodes.BAD_REQUEST,"The value in unique attribute \"name\" already exists");
		}
		if (s.getMessage().contains("customer_id")) {
			throw new CustomerException(ResponseCodes.BAD_REQUEST,"The value in unique attribute \"customer_id\" already exists");
		}
		throw new Exception(s);
	}

	/**
	 * Get a list with all customer_company that have a active status
	 * 
	 * @return List of all active customer_company
	 * @throws Exception
	 */
	public List<CustomerCompany> listAllActiveCustomerCompany() throws Exception {
		try {
			getAvailabeConnection();
			String sql = "SELECT * FROM customer_company WHERE valid_id = ?";
			setStatement(getConnection().prepareStatement(sql));
			getStatement().setInt(1, ValidId.VALID.getId());
			setResultSet(getStatement().executeQuery());
			return rsCustomerCompany();
		} catch (SQLException s) {
			throw new Exception(s);
		} catch (Exception e) {
			throw e;
		} finally {
			closeConnection();
		}
	}

	/**
	 * Obtains a customer_company that has the customer_id selected
	 * 
	 * @param customerId
	 *            customer_id of the selected customer_company
	 * @return The customer_company with all the fields filled
	 * @throws Exception
	 */
	public CustomerCompany getCustomerCompany(String customerId) throws Exception {
		try {
			getAvailabeConnection();
			String sql = "SELECT * FROM customer_company WHERE customer_id = ?";
			setStatement(getConnection().prepareStatement(sql));
			getStatement().setString(1, customerId);
			setResultSet(getStatement().executeQuery());
			List<CustomerCompany> customerList = rsCustomerCompany();
			return customerList.isEmpty() ? null : customerList.get(0);
		} catch (SQLException s) {
			throw new Exception(s);
		} catch (Exception e) {
			throw e;
		} finally {
			closeConnection();
		}
	}

	/**
	 * Iterates through the ResultSet and creates a list with all the data that
	 * was acquired in the query
	 * 
	 * @return List with all the customer_company that were returned by the
	 *         database
	 * @throws SQLException
	 * @throws Exception
	 */
	private List<CustomerCompany> rsCustomerCompany() throws SQLException, Exception {
		List<CustomerCompany> list = new LinkedList<>();
		while (getResultSet().next()) {
			CustomerCompany customer = new CustomerCompany();
			customer.setCustomerId(getResultSet().getString("customer_id"));
			customer.setName(getResultSet().getString("name"));
			customer.setStreet(getResultSet().getString("street"));
			customer.setZip(getResultSet().getString("zip"));
			customer.setCity(getResultSet().getString("city"));
			customer.setCountry(getResultSet().getString("country"));
			customer.setUrl(getResultSet().getString("url"));
			customer.setComments(getResultSet().getString("comments"));
			customer.setValidId(getResultSet().getShort("valid_id"));
			customer.setCreateTime(getResultSet().getTimestamp("create_time"));
			customer.setCreateBy(getResultSet().getShort("create_by"));
			customer.setChangeTime(getResultSet().getTimestamp("change_time"));
			customer.setChangeBy(getResultSet().getShort("change_by"));

			list.add(customer);
		}
		return list;
	}

}
