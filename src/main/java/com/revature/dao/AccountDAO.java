package com.revature.dao;

import java.util.ArrayList;

import com.revature.model.Account;

public interface AccountDAO {

	public boolean addAccount(Account a);

	public boolean removeAccount(int accid);

	boolean updateAccount(int accid, String field, String value);

	double getBalance(int accid);
	
	public int getAvailableAccountId();

	public ArrayList<Account> getListOfAccounts();

	ArrayList<Integer> getCustomerAccounts(String username);

	boolean addAccID(String username, int accid);

}
