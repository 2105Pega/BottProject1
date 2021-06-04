package com.revature.model;

public class AccountsAndBalance {

	private double balance;
	private int accid;

	public AccountsAndBalance(int accid, double balance) {
		this.accid = accid;
		this.balance = balance;

	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public int getAccid() {
		return accid;
	}

	public void setAccid(int accid) {
		this.accid = accid;
	}

	@Override
	public String toString() {
		return "AccountsAndBalance [balance=" + balance + ", accid=" + accid + "]";
	}

}
