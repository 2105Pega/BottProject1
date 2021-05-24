package com.revature.driver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

import com.revature.model.*;
import com.revature.service.Services;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Menu {

	// starting logger
	final static Logger logger = LogManager.getLogger(Menu.class);

	public static int MenuStart(Scanner sc) {

		// prompt user for application or login
		int menuSelection = 0;
		System.out.println("Hello, enter 1 to login, 2 for account application or 3 to exit.");
		menuSelection = sc.nextInt();

		// error checking user input
		while (menuSelection < 1 || menuSelection > 3) {
			System.out.println("Please enter 1 to login, 2 for account application or 3 to exit.");
			menuSelection = sc.nextInt();

		}

		return menuSelection;

	}

	public static Customer menuLogin(Scanner sc) {

		String adminUser = "";
		String adminPass = "";

		try {
			FileInputStream fileStream = new FileInputStream("../Project1/src/main/resources/adminCredentials");

			Properties prop = new Properties();
			prop.load(fileStream);

			adminUser = prop.getProperty("adminUsername");
			adminPass = prop.getProperty("adminPassword");

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e2) {
			e2.printStackTrace();
		} catch (Exception e3) {
			e3.printStackTrace();
		}

		String username = "";

		System.out.println("Enter username: ");
		sc.nextLine();
		username = sc.nextLine();

		while (username.isEmpty()) {
			System.out.println("Enter username: ");

		}

		Services userServ = new Services();
		String expectedPassword = "";

		if (!username.equals(adminUser)) {
			expectedPassword = userServ.getPassword(username);
		} else if (username.equals(adminUser)) {
			expectedPassword = adminPass;
		}
		String password = "";
		System.out.println("Enter password (or blank line to exit): ");
		password = sc.nextLine();

		while (password.isEmpty() || !password.equals(expectedPassword)) {
			System.out.println("Enter password (or blank line to exit): ");
			password = sc.nextLine();
		}

		if (username.equals(adminUser) && password.equals(adminPass)) {
			adminLogin(sc);

		} else {
			// customer details
			Customer loggedInUser = userServ.getCustomer(username);
			// now must get account ID's for customer
			ArrayList<Integer> accid = new ArrayList<Integer>();
			accid = userServ.getCustomerAccounts(username);
			loggedInUser.setAcctID(accid);
			return loggedInUser;
		}

		Customer base = new Customer("base", "base", "base", "base", null);
		return base;

	}

	public static void adminLogin(Scanner sc) {
		System.out.println("Logged in as an Administrator: ");
		System.out.println(
				"1. deposit, 2. withdraw, 3. transfer, 4. view pending accounts, 5 cancel accounts, 6 view all accounts, 7 to quit ");
		int selection = sc.nextInt();
		int acctID = -1;

		while (selection != 7) {

			if (selection == 1) {
				System.out.println("Enter account ID for deposit: ");
				acctID = sc.nextInt();
				preDeposit(sc, acctID);
			} else if (selection == 2) {
				System.out.println("Enter account ID for withdrawl: ");
				acctID = sc.nextInt();
				preWithdraw(sc, acctID);
			} else if (selection == 3) {
				System.out.println("Enter account ID to transfer from: ");
				acctID = sc.nextInt();
				preTransfer(sc, acctID);
			} else if (selection == 4) {

				ArrayList<Integer> pending = new ArrayList<Integer>();
				pending.add(0);
				listUserAccounts(pending);

			} else if (selection == 5) {
				// cancelAccount(sc, accounts, customers);

			} else if (selection == 6) {
				viewAllAccounts();
			} else {
				System.out.println(
						"1. deposit, 2. withdraw, 3. transfer, 4. view pending accounts, 5 cancel accounts, 6 view all accounts, 7 to quit ");
				selection = sc.nextInt();

			}
			System.out.println(
					"1. deposit, 2. withdraw, 3. transfer, 4. view pending accounts, 5 cancel accounts, 6 view all accounts, 7 to quit ");
			selection = sc.nextInt();
		}

	}

	public static void MenuApplyForAcct(Scanner sc) {

		// joint acc or solo
		System.out.println("Applying for a joint account (y/n)?");
		sc.nextLine();
		String jointAcctSelection = sc.nextLine();

		// applying for individuals account
		if (jointAcctSelection.toLowerCase().equals("n")) {
			// applying for individual account
			individualAcctApply(sc);

		} else if (jointAcctSelection.toLowerCase().equals("y")) {
			jointAcctApply(sc);
		}

	}

	public static void individualAcctApply(Scanner sc) {
		Services userServ = new Services();
		String username = "";
		String password = "";
		String firstName = "";
		String lastName = "";

		System.out.println("Desired username: ");
		username = sc.nextLine();
		System.out.println("Desired Password: ");
		password = sc.nextLine();
		System.out.println("First Name: ");
		firstName = sc.nextLine();
		System.out.println("Last Name");
		lastName = sc.nextLine();

		ArrayList<Integer> pending = new ArrayList<Integer>();
		pending.add(0);
		Customer acctApplication = new Customer(username, password, firstName, lastName, pending);
		userServ.addCustomer(acctApplication);

	}

	public static void jointAcctApply(Scanner sc) {
		Services userServ = new Services();
		String username = "";
		String password = "";
		String firstName = "";
		String lastName = "";

		System.out.println("Desired username: ");
		username = sc.nextLine();
		System.out.println("Desired Password: ");
		password = sc.nextLine();
		System.out.println("First Names: ");
		firstName = sc.nextLine();
		System.out.println("Last Names");
		lastName = sc.nextLine();

		ArrayList<Integer> pending = new ArrayList<Integer>();
		pending.add(0);
		Customer acctApplication = new Customer(username, password, firstName, lastName, pending);
		userServ.addCustomer(acctApplication);

	}

	// pass in user accid
	public static void preTransfer(Scanner sc, int accid) {

		Services userServ = new Services();
		double availableBalance = 0;
		availableBalance = userServ.getBalance(accid);

		System.out.println("Enter Account ID to transfer to: ");
		int receiverID = sc.nextInt();
		System.out.println("Enter amount to transfer: ");
		double amount = sc.nextDouble();

		while (amount < 0 || amount > availableBalance) {
			System.out.println("Please enter positive amount: ");
			amount = sc.nextDouble();

		}
		transfer(accid, receiverID, amount);
	}

	public static void transfer(int giverID, int receiverID, double amount) {

		Services userServ = new Services();
		withdraw(giverID, amount);
		deposit(receiverID, amount);

		String text = "Transferred $" + amount + " from account ID " + giverID + ", Balance now: $"
				+ userServ.getBalance(receiverID) + "| to account ID " + receiverID + " Balance now: $"
				+ userServ.getBalance(giverID) + "|";
		System.out.println(text);

		logger.setLevel(Level.TRACE);
		logger.trace(text);

	}

	public static void viewAllAccounts() {
		Services userServ = new Services();

		ArrayList<Account> accounts = new ArrayList<Account>();

		accounts = userServ.getListOfAccounts();

		for (int i = 0; i < accounts.size(); i++) {

			System.out.println(accounts.get(i).toString());

		}

	}

	public static void listUserAccounts(ArrayList<Integer> accid) {

		Services userServ = new Services();

		System.out.println("You have " + accid.size() + " account(s)");

		for (int i = 0; i < accid.size(); i++) {
			int account = accid.get(i);
			double balance = userServ.getBalance(account);
			System.out.println("Account ID " + account + " has balance " + balance);

		}

	}

	public static int accountSelection(Scanner sc, ArrayList<Integer> accid) {

		int selection = 0;
		System.out.println("select an account ID to proceed: ");
		selection = sc.nextInt();
		while (!accid.contains(selection)) {
			System.out.println("select an account ID to proceed: ");
			selection = sc.nextInt();
		}

		return selection;

	}

	public static void accountFeatures(Scanner sc, int accountID) {
		System.out.println("Enter 1 to deposit, 2 for withdraw, 3 to transfer or 4 to exit ");
		int userSelection = sc.nextInt();

		while (userSelection != 4) {
			if (userSelection == 1) {
				preDeposit(sc, accountID);
			} else if (userSelection == 2) {
				preWithdraw(sc, accountID);
			} else if (userSelection == 3) {
				preTransfer(sc, accountID);
			} else {
				System.out.println("Enter 1 to deposit, 2 for withdraw, 3 to transfer or 4 to exit ");
				userSelection = sc.nextInt();
			}
			System.out.println("Enter 1 to deposit, 2 for withdraw, 3 to transfer or 4 to exit ");
			userSelection = sc.nextInt();
		}
	}

	public static void preDeposit(Scanner sc, int accountID) {

		System.out.println("Enter amount to deposit: ");
		double deposit = sc.nextDouble();

		while (deposit <= 0) {
			System.out.println("Please enter positive amount: ");
			deposit = sc.nextDouble();

		}
		deposit(accountID, deposit);

	}

	public static void deposit(int accid, double deposit) {

		Services userServ = new Services();

		double balance = userServ.getBalance(accid);
		balance = balance + deposit;

		String updatedBal = String.valueOf(balance);
		userServ.updateAccount(accid, "balance", updatedBal);

		String text = "Deposit of: $" + deposit + " received, Account balance: $" + updatedBal;
		System.out.println(text);

		logger.setLevel(Level.TRACE);
		logger.trace(text);
	}

	public static void preWithdraw(Scanner sc, int accid) {

		Services userServ = new Services();
		double availableBalance = 0;
		availableBalance = userServ.getBalance(accid);

		System.out.println("Enter amount to withdraw up to " + availableBalance + " : ");
		double withdraw = sc.nextDouble();

		while (withdraw <= 0 || withdraw > availableBalance) {
			System.out.println("Please enter positive amount less than balance: ");
			withdraw = sc.nextDouble();

		}

		withdraw(accid, withdraw);
	}

	public static void withdraw(int accid, double withdraw) {

		Services userServ = new Services();

		double balance = userServ.getBalance(accid);
		balance = balance - withdraw;

		String updatedBal = String.valueOf(balance);
		userServ.updateAccount(accid, "balance", updatedBal);

		String text = "Deposit of: $" + withdraw + " received, Account balance: $" + updatedBal;
		System.out.println(text);

		logger.setLevel(Level.TRACE);
		logger.trace(text);
	}

}
