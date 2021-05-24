package com.revature.dao;

import java.util.ArrayList;
import java.util.List;

import com.revature.model.Account;

public interface AccountDAO {

	public boolean addAccount(Account a);

	public boolean removeAccount(Account a);

	boolean updateAccount(int accid, String field, String value);

	double getBalance(int accid);

	public ArrayList<Account> getListOfAccounts();

	ArrayList<Integer> getCustomerAccounts(String username);

}
