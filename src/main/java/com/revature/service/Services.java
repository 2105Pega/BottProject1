package com.revature.service;

import com.revature.dao.CustomerDAO;
import com.revature.dao.CustomerDaoImpl;
import com.revature.model.Account;
import com.revature.model.Customer;

import java.util.ArrayList;

import com.revature.dao.AccountDAO;
import com.revature.dao.AccountDaoImpl;

public class Services {

	private CustomerDAO eDaoCustomer = new CustomerDaoImpl();
	private AccountDAO eDaoAccount = new AccountDaoImpl();

	public String addCustomer(Customer c) {

		if (eDaoCustomer.addCustomer(c)) {
			return "Customer was successfully added to the database!";
		} else {
			return "Customer was notadded to the database!";
		}

	}

	public String removeCustomer(String username) {

		if (eDaoCustomer.removeCustomer(username)) {

			return "Customer was successfully removed from the database!";
		} else {
			return "Customer was not successfully removed from the database!";
		}
	}

	public String updateCustomer(Customer c, String field, String value) {

		if (eDaoCustomer.updateCustomer(c, field, value)) {

			return "Customer was successfully updated in the database!";
		} else {
			return "Customer not was successfully updated in the database!";
		}
	}

	public String getPassword(String username) {

		String password = eDaoCustomer.getPassword(username);

		return password;
	}

	public Customer getCustomer(String username) {

		Customer customer = eDaoCustomer.getCustomer(username);

		return customer;
	}

	public ArrayList<Integer> getCustomerAccounts(String username) {

		ArrayList<Integer> customerAccounts = eDaoAccount.getCustomerAccounts(username);

		return customerAccounts;

	}

	public ArrayList<Account> getListOfAccounts() {

		ArrayList<Account> accounts = eDaoAccount.getListOfAccounts();

		return accounts;

	}

	public double getBalance(int accid) {

		double balance = eDaoAccount.getBalance(accid);

		return balance;
	}

	public String addAccount(Account a) {

		if (eDaoAccount.addAccount(a)) {
			return "Account was successfully added to the database!";
		} else {
			return "Account was not added to the database!";
		}
	}

	public String removeAccount(int accid) {

		if (eDaoAccount.removeAccount(accid)) {

			return "Account was successfully removed from the database!";
		} else {
			return "Account was not successfully removed from the database!";
		}
	}

	public String updateAccount(int accid, String field, String value) {

		if (eDaoAccount.updateAccount(accid, field, value)) {

			return "Account was successfully updated in the database!";
		} else {
			return "Account not was successfully updated in the database!";
		}
	}

	public int getAvailableAccountId() {

		int availableAccount = eDaoAccount.getAvailableAccountId();
		return availableAccount;

	}

	public void addAccID(String username, int accid) {

		eDaoAccount.addAccID(username, accid);

	}

	public ArrayList<Customer> getPending() {
		ArrayList<Customer> customer = eDaoCustomer.getPending();
		return customer;

	}

}
