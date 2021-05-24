package com.revature.dao;

import java.util.ArrayList;

import com.revature.model.Customer;

public interface CustomerDAO {

	public boolean addCustomer(Customer c);

	public boolean removeCustomer(String username);

	public boolean updateCustomer(Customer c, String field, String value);

	public String getPassword(String username);

	Customer getCustomer(String username);

	public ArrayList<Customer> getListOfCustomer();

	ArrayList<Customer> getPending();

}
