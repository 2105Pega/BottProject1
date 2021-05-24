package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Account;
import com.revature.model.Customer;
import com.revature.util.ConnectionUtils;

public class AccountDaoImpl implements AccountDAO {

	@Override
	public boolean addAccount(Account a) {

		try (Connection conn = ConnectionUtils.getConnection()) {

			String sql = "insert into accounts(accid, balance, f_name, l_name)" + "values(?,?,?,?)";
			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setInt(1, a.getAccID());
			statement.setDouble(2, a.getBalance());
			statement.setString(3, a.getFirstName());
			statement.setString(4, a.getLastName());

			statement.execute();
			return true;

		} catch (SQLException e2) {
			e2.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean removeAccount(Account a) {

		try (Connection conn = ConnectionUtils.getConnection()) {
			String sql = "Delete from accounts where accid = ?";

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, a.getAccID());

			statement.execute();

			return true;

		} catch (Exception e2) {

			e2.printStackTrace();

		}
		return false;
	}

	@Override
	public ArrayList<Account> getListOfAccounts() {

		try (Connection conn = ConnectionUtils.getConnection()) {

			String sql = "select * from accounts";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);

			ArrayList<Account> accountList = new ArrayList<Account>();
			while (result.next()) {
				Account a = new Account(result.getInt("accid"), result.getDouble("balance"), result.getString("f_name"),
						result.getString("l_name"));
				accountList.add(a);

			}

			return accountList;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}

	@Override
	public ArrayList<Integer> getCustomerAccounts(String username) {

		try (Connection conn = ConnectionUtils.getConnection()) {

			String sql = "select accid from accounts_to_id where username = '" + username + "' ";

			Statement statement = conn.createStatement();

			ResultSet result = statement.executeQuery(sql);

			ArrayList<Integer> accList = new ArrayList<Integer>();
			while (result.next()) {

				accList.add(result.getInt(1));

			}

			return accList;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}

	public boolean updateAccount(int accid, String field, String value) {
		// needs value and field
		// update roles set roles_salary = roles_salary + 10000;

		try (Connection conn = ConnectionUtils.getConnection()) {

//			update avengers set superhero_power = 'Shield'
//					where superhero_name = 'Capt. America';

			String sql = "update accounts set " + field + " = " + value + "where accid = " + accid;

			PreparedStatement statement = conn.prepareStatement(sql);

			statement.execute();

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public double getBalance(int accid) {

		try (Connection conn = ConnectionUtils.getConnection()) {

			String sql = "select balance from accounts where accid = " + accid;

			Statement statement = conn.createStatement();

			ResultSet result = statement.executeQuery(sql);

			double balance = 0;
			result.next();
			balance = result.getDouble(1);

			return balance;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
