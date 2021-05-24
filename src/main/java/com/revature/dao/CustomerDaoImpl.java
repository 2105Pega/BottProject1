package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.revature.model.Customer;
import com.revature.util.ConnectionUtils;

public class CustomerDaoImpl implements CustomerDAO {

	@Override
	public boolean addCustomer(Customer c) {

		try (Connection conn = ConnectionUtils.getConnection()) {

			String sql = "insert into customers(username, password, f_name, l_name)" + "values(?,?,?,?)";
			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setString(1, c.getUsername());
			statement.setString(2, c.getPassword());
			statement.setString(3, c.getFirstName());
			statement.setString(4, c.getLastName());

			statement.execute();
			return true;

		} catch (SQLException e2) {
			e2.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean removeCustomer(String username) {

		try (Connection conn = ConnectionUtils.getConnection()) {
			String sql = "delete from customers where username = ?";

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, username);

			statement.execute();

			return true;

		} catch (Exception e2) {

			e2.printStackTrace();

		}
		return false;

	}

	@Override
	public ArrayList<Customer> getListOfCustomer() {

		try (Connection conn = ConnectionUtils.getConnection()) {

			String sql = "select * from customers";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);

			ArrayList<Customer> customerList = new ArrayList<Customer>();

			while (result.next()) {
				Customer c = new Customer(result.getString("username"), result.getString("password"),
						result.getString("f_name"), result.getString("l_name"), null);
				customerList.add(c);

			}

			return customerList;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	@Override
	public ArrayList<Customer> getPending() {

		try (Connection conn = ConnectionUtils.getConnection()) {

			String sql = "select * from customers where username not in (select username from accounts_to_id )";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);

			ArrayList<Customer> customerList = new ArrayList<Customer>();

			while (result.next()) {
				Customer c = new Customer(result.getString("username"), result.getString("password"),
						result.getString("f_name"), result.getString("l_name"), null);
				customerList.add(c);

			}

			return customerList;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	

	@Override
	public String getPassword(String username) {

		try (Connection conn = ConnectionUtils.getConnection()) {

			String sql = "select password from customers where username = '" + username + "'";

			Statement statement = conn.createStatement();

			ResultSet passwordSet = statement.executeQuery(sql);
			passwordSet.next();
			String password = passwordSet.getString("password");

			return password;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Customer getCustomer(String username) {

		try (Connection conn = ConnectionUtils.getConnection()) {

			String sql = "select * from customers where username = '" + username +"'";

			Statement statement = conn.createStatement();

			ResultSet result = statement.executeQuery(sql);
			result.next();

			Customer c = new Customer(result.getString("username"), result.getString("password"),
					result.getString("f_name"), result.getString("l_name"), null);

			return c;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public boolean updateCustomer(Customer c, String field, String value) {
		// needs value and field
		// update roles set roles_salary = roles_salary + 10000;

		try (Connection conn = ConnectionUtils.getConnection()) {

			String sql = "update customers set" + field + " = " + value + "where username = '" + c.getUsername()
					+ " ' ";

			PreparedStatement statement = conn.prepareStatement(sql);

			statement.execute();

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

}
