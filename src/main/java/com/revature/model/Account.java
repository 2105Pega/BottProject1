package com.revature.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Account implements Serializable {

	private double balance;
	private int accid;
	private String firstName;
	private String lastName;

	public Account(int accid, double balance, String firstName, String lastName) {
		this.accid = accid;
		this.balance = balance;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public int getAccID() {
		return accid;
	}

	public void setAccID(int accID) {
		this.accid = accID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "Account [balance=" + balance + ", accID=" + accid + ", firstName=" + firstName + ", lastName="
				+ lastName + "]";
	}

}
