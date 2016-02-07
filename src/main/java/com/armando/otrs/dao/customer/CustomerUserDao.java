package com.armando.otrs.dao.customer;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.armando.otrs.dao.common.BaseDao;
import com.armando.otrs.exception.CustomerException;
import com.armando.otrs.model.customer.CustomerUser;
import com.armando.otrs.util.ResponseCodes;
import com.armando.otrs.util.ValidId;

/**
 * Class that will access the database and get/modify the information about the
 * customer_user
 * 
 * @author Armando
 *
 */
public class CustomerUserDao extends BaseDao {


	/**
	 * Updates a existing customer user
	 * @param customerUser				Customer user to be updated, along with the new data
	 * @return							Modified customer user
	 * @throws CustomerException		Raised with known exception (mostly will be validations from the database)
	 * @throws Exception				Not expected exception
	 */
	public CustomerUser updateCustomerUser(CustomerUser customerUser) throws CustomerException, Exception{
		try {
			getAvailabeConnection();
			String sql = "UPDATE customer_user\n" +
                   "   SET login=?, email=?, customer_id=?, title=?, first_name=?, \n" +
                   "       last_name=?, phone=?, fax=?, mobile=?, street=?, zip=?, city=?, \n" +
                   "       country=?, comments=?, valid_id=?, change_time=now(), change_by=?\n" +
                   " WHERE id=?;";
			setStatement(getConnection().prepareStatement(sql));
			getStatement().setString(1, customerUser.getLogin());
			getStatement().setString(2, customerUser.getEmail());
			getStatement().setString(3, customerUser.getCustomerId());
			getStatement().setString(4, customerUser.getTitle());
			getStatement().setString(5, customerUser.getFirstName());
			getStatement().setString(6, customerUser.getLastName());
			getStatement().setString(7, customerUser.getPhone());
			getStatement().setString(8, customerUser.getFax());
			getStatement().setString(9, customerUser.getMobile());
			getStatement().setString(10, customerUser.getStreet());
			getStatement().setString(11, customerUser.getZip());
			getStatement().setString(12, customerUser.getCity());
			getStatement().setString(13, customerUser.getCountry());
			getStatement().setString(14, customerUser.getComments());
			getStatement().setShort(15, customerUser.getValidId());
			getStatement().setInt(16, customerUser.getChangeBy());
			getStatement().setInt(17, customerUser.getId());
			if (getStatement().executeUpdate() < 1){
				throw new CustomerException(ResponseCodes.RESOURCE_NOT_FOUND, "No customer user found with the specified id");
			}
		} catch (SQLException s) {
			analiseSqlException(s);
		} catch (Exception e) {
			throw e;
		} finally {
			closeConnection();
		}
		return customerUser;
	}
	
	/**
	 *	Insert a new customer user 
	 * @param customerUser  		Data to be inserted
	 * @return						CustomerUser with the database generated ID
	 * @throws CustomerException	Known exceptions, like FK and UK errors
	 * @throws Exception
	 */
	public CustomerUser insertCustomerUser(CustomerUser customerUser) throws CustomerException, Exception{
		try {
			getAvailabeConnection();
			String sql = "INSERT INTO customer_user(login, email, customer_id, pw, title, first_name, last_name, \n" +
					"            phone, fax, mobile, street, zip, city, country, comments, valid_id, \n" +
					"            create_time, create_by, change_time, change_by)\n" +
					"    VALUES (?, ?, ?, ?, ?, ?, ?, \n" +
					"            ?, ?, ?, ?, ?, ?, ?, ?, ?, \n" +
					"            now(), ?, now(), ?);";
			setStatement(getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS));
			getStatement().setString(1, customerUser.getLogin());
			getStatement().setString(2, customerUser.getEmail());
			getStatement().setString(3, customerUser.getCustomerId());
			getStatement().setString(4, customerUser.getPw());
			getStatement().setString(5, customerUser.getTitle());
			getStatement().setString(6, customerUser.getFirstName());
			getStatement().setString(7, customerUser.getLastName());
			
			getStatement().setString(8, customerUser.getPhone());
			getStatement().setString(9, customerUser.getFax());
			getStatement().setString(10, customerUser.getMobile());
			getStatement().setString(11, customerUser.getStreet());
			getStatement().setString(12, customerUser.getZip());
			getStatement().setString(13, customerUser.getCity());
			getStatement().setString(14, customerUser.getCountry());
			getStatement().setString(15, customerUser.getComments());
			getStatement().setShort(16, customerUser.getValidId());
			
			getStatement().setInt(17, customerUser.getCreateBy());
			getStatement().setInt(18, customerUser.getChangeBy());
			
			if (getStatement().executeUpdate() < 1){
				throw new Exception();
			}
			setResultSet(getStatement().getGeneratedKeys());
            if (getResultSet().next()) {
                customerUser.setId(getResultSet().getInt(1));
            }
            
		} catch (SQLException s) {
			analiseSqlException(s);
		} catch (Exception e) {
			throw e;
		} finally {
			closeConnection();
		}
		return customerUser;
	}
	
	/**
	 * Analise all the known possibilities of a raised SQLException and try to build a user friendly message
	 * @param s						SQLException to be analised
	 * @throws CustomerException	Exception with friendly message and response code
	 * @throws Exception			Unknow exception
	 */
	private void analiseSqlException(SQLException s) throws CustomerException, Exception{
		if (s.getMessage().contains("customer_id")){
			throw new CustomerException(ResponseCodes.BAD_REQUEST, "Field \"customer_id\" is invalid");
		}
		if (s.getMessage().contains("customer_user_pkey")){
			throw new CustomerException(ResponseCodes.RESOURCE_CONFLICT,"Value of the field \"id\" already exists");
		}
		if (s.getMessage().contains("customer_company_name")){
			throw new CustomerException(ResponseCodes.RESOURCE_CONFLICT,"Value of the field \"name\" already exists");
		}
		if (s.getMessage().contains("fk_customer_user_change_by_id")){
			throw new CustomerException(ResponseCodes.BAD_REQUEST,"Value of the field \"change_by\" is invalid");
		}
		if (s.getMessage().contains("fk_customer_user_create_by_id")){
			throw new CustomerException(ResponseCodes.BAD_REQUEST,"Value of the field \"create_by\" is invalid");
		}
		if (s.getMessage().contains("fk_customer_user_valid_id_id")){
			throw new CustomerException(ResponseCodes.BAD_REQUEST,"Value of the field \"valid_id\" is invalid");
		}
		if (s.getMessage().contains("customer_user_login")){
			throw new CustomerException(ResponseCodes.RESOURCE_CONFLICT,"Value of the field \"customer_user_login\" already exists");
		}
		throw new Exception();
	}
	
	/**
	 * Get a list with all the valids customer_user in the database
	 * 
	 * @return List with all valid customer users
	 * @throws Exception
	 */
	public List<CustomerUser> getAllValidCustomerUser() throws Exception {
		try {
			getAvailabeConnection();
			String sql = "SELECT * FROM customer_user WHERE valid_id = ?";
			setStatement(getConnection().prepareStatement(sql));
			getStatement().setInt(1, ValidId.VALID.getId());
			setResultSet(getStatement().executeQuery());
			return rsCustomerUser();
		} catch (Exception e) {
			throw e;
		} finally {
			closeConnection();
		}
	}

	/**
	 * Gets a specific customer user
	 * 
	 * @param id
	 *            ID of the customer id to get
	 * @return CustomerUser if the ID was valid or null if we couldn't find any
	 *         customer user with the selected id
	 * @throws Exception
	 */
	public CustomerUser getCustomerUser(int id) throws Exception {
		try {
			getAvailabeConnection();
			String sql = "SELECT * FROM customer_user WHERE valid_id = 1 AND id = ?";
			setStatement(getConnection().prepareStatement(sql));
			getStatement().setInt(1, ValidId.VALID.getId());
			setResultSet(getStatement().executeQuery());
			List<CustomerUser> result = rsCustomerUser();
			return result.isEmpty() ? null : result.get(0);
		} catch (Exception e) {
			throw e;
		} finally {
			closeConnection();
		}
	}

	/**
	 * Invalidates a customer user
	 * @param id					ID of the customer user to be invalidated
	 * @param changeBy				ID of the user responsible for the change
	 * @throws CustomerException	CustomerException with a modified, friendly message
	 * @throws Exception
	 */
	public void invalidateCustomerUser(int id, int changeBy) throws CustomerException, Exception {
		try {
			getAvailabeConnection();
			String sql = "UPDATE customer_user SET valid_id=? WHERE id=?, change_by=?, change_time=now()";
			setStatement(getConnection().prepareStatement(sql));
			getStatement().setInt(1, ValidId.INVALID.getId());
			getStatement().setInt(2, id);
			getStatement().setInt(3, changeBy);
			if (getStatement().executeUpdate() < 1){
				throw new CustomerException(ResponseCodes.RESOURCE_NOT_FOUND, "No customer user found with the specified id");
			}
		} catch (SQLException s) {
			if (s.getMessage().contains("change_by")) {
				throw new CustomerException(ResponseCodes.BAD_REQUEST, "Value of the field \"change_by\" is invaliFd");
			}
			throw new Exception();
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
	 * @return
	 * @throws Exception
	 */
	private List<CustomerUser> rsCustomerUser() throws Exception {
		List<CustomerUser> result = new LinkedList<>();
		while (getResultSet().next()) {
			CustomerUser user = new CustomerUser();
			user.setId(getResultSet().getInt("id"));
			user.setLogin(getResultSet().getString("login"));
			user.setEmail(getResultSet().getString("email"));
			user.setCustomerId(getResultSet().getString("customer_id"));
			user.setTitle(getResultSet().getString("title"));
			user.setFirstName(getResultSet().getString("first_name"));
			user.setLastName(getResultSet().getString("last_name"));
			user.setPhone(getResultSet().getString("phone"));
			user.setFax(getResultSet().getString("fax"));
			user.setMobile(getResultSet().getString("mobile"));
			user.setStreet(getResultSet().getString("street"));
			user.setZip(getResultSet().getString("zip"));
			user.setCity(getResultSet().getString("city"));
			user.setCountry(getResultSet().getString("country"));
			user.setComments(getResultSet().getString("comments"));
			user.setValidId(getResultSet().getShort("valid_id"));
			user.setCreateTime(getResultSet().getDate("create_time"));
			user.setCreateBy(getResultSet().getShort("create_by"));
			user.setChangeTime(getResultSet().getDate("change_time"));
			user.setChangeBy(getResultSet().getShort("change_by"));

			result.add(user);
		}

		return result;
	}

}
