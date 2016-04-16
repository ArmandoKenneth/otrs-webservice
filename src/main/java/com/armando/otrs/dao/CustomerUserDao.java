package com.armando.otrs.dao;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.armando.otrs.exception.CustomerException;
import com.armando.otrs.model.CustomerUser;
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
			setPs(getCon().prepareStatement(sql));
			getPs().setString(1, customerUser.getLogin());
			getPs().setString(2, customerUser.getEmail());
			getPs().setString(3, customerUser.getCustomerId());
			getPs().setString(4, customerUser.getTitle());
			getPs().setString(5, customerUser.getFirstName());
			getPs().setString(6, customerUser.getLastName());
			getPs().setString(7, customerUser.getPhone());
			getPs().setString(8, customerUser.getFax());
			getPs().setString(9, customerUser.getMobile());
			getPs().setString(10, customerUser.getStreet());
			getPs().setString(11, customerUser.getZip());
			getPs().setString(12, customerUser.getCity());
			getPs().setString(13, customerUser.getCountry());
			getPs().setString(14, customerUser.getComments());
			getPs().setShort(15, customerUser.getValidId());
			getPs().setInt(16, customerUser.getChangeBy());
			getPs().setInt(17, customerUser.getId());
			if (getPs().executeUpdate() < 1){
				throw new CustomerException(ResponseCodes.RESOURCE_NOT_FOUND, "No customer user found with the specified id");
			}
		} catch (SQLException s) {
			throw new CustomerException(s);
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
			setPs(getCon().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS));
			getPs().setString(1, customerUser.getLogin());
			getPs().setString(2, customerUser.getEmail());
			getPs().setString(3, customerUser.getCustomerId());
			getPs().setString(4, customerUser.getPw());
			getPs().setString(5, customerUser.getTitle());
			getPs().setString(6, customerUser.getFirstName());
			getPs().setString(7, customerUser.getLastName());
			
			getPs().setString(8, customerUser.getPhone());
			getPs().setString(9, customerUser.getFax());
			getPs().setString(10, customerUser.getMobile());
			getPs().setString(11, customerUser.getStreet());
			getPs().setString(12, customerUser.getZip());
			getPs().setString(13, customerUser.getCity());
			getPs().setString(14, customerUser.getCountry());
			getPs().setString(15, customerUser.getComments());
			getPs().setShort(16, customerUser.getValidId());
			
			getPs().setInt(17, customerUser.getCreateBy());
			getPs().setInt(18, customerUser.getChangeBy());
			
			if (getPs().executeUpdate() < 1){
				throw new CustomerException(ResponseCodes.RESOURCE_NOT_FOUND, "Customer user not found");
			}
			setRs(getPs().getGeneratedKeys());
            if (getRs().next()) {
                customerUser.setId(getRs().getInt(1));
            }
            
		} catch (SQLException s) {
			throw new CustomerException(s);
		} catch (Exception e) {
			throw e;
		} finally {
			closeConnection();
		}
		return customerUser;
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
			setPs(getCon().prepareStatement(sql));
			getPs().setInt(1, ValidId.VALID.getId());
			setRs(getPs().executeQuery());
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
			String sql = "SELECT * FROM customer_user WHERE id = ?";
			setPs(getCon().prepareStatement(sql));
			setRs(getPs().executeQuery());
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
			setPs(getCon().prepareStatement(sql));
			getPs().setInt(1, ValidId.INVALID.getId());
			getPs().setInt(2, id);
			getPs().setInt(3, changeBy);
			if (getPs().executeUpdate() < 1){
				throw new CustomerException(ResponseCodes.RESOURCE_NOT_FOUND, "No customer user found with the specified id");
			}
		} catch (CustomerException e){
			throw e;
		} catch (SQLException s) {
			throw new CustomerException(s);
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
		while (getRs().next()) {
			CustomerUser user = new CustomerUser();
			user.setId(getRs().getInt("id"));
			user.setLogin(getRs().getString("login"));
			user.setEmail(getRs().getString("email"));
			user.setCustomerId(getRs().getString("customer_id"));
			user.setTitle(getRs().getString("title"));
			user.setFirstName(getRs().getString("first_name"));
			user.setLastName(getRs().getString("last_name"));
			user.setPhone(getRs().getString("phone"));
			user.setFax(getRs().getString("fax"));
			user.setMobile(getRs().getString("mobile"));
			user.setStreet(getRs().getString("street"));
			user.setZip(getRs().getString("zip"));
			user.setCity(getRs().getString("city"));
			user.setCountry(getRs().getString("country"));
			user.setComments(getRs().getString("comments"));
			user.setValidId(getRs().getShort("valid_id"));
			user.setCreateTime(getRs().getDate("create_time"));
			user.setCreateBy(getRs().getShort("create_by"));
			user.setChangeTime(getRs().getDate("change_time"));
			user.setChangeBy(getRs().getShort("change_by"));

			result.add(user);
		}

		return result;
	}

}
