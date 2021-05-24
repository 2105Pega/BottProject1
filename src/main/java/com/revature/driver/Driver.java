package com.revature.driver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

import javax.xml.ws.Service;

import com.revature.dao.AccountDaoImpl;
import com.revature.dao.CustomerDaoImpl;
import com.revature.model.Account;
import com.revature.model.Customer;
import com.revature.service.Services;

/*
 * ----------------------------------------------------------------------------------------------------
 * Business Requirements:
 A registered user can login with their username and password
 An unregistered user can register by creating a username and password
 An Admin can view, create, update, and delete all users.
 A user can view their own existing accounts and balances.
 A user can create an account.
 A user can delete an account if it is empty.
 A user can add to or withdraw from an account.
 A user can execute multiple deposits or withdrawals in a session.
 A user can logout.
 -------------------------------------------------------------------------------------------------------
Required components:
 Use Serial data types to generate USER_ID and BANK_ACCOUNT_ID.
 Throw custom exceptions in the event of user error (overdraft, incorrect password, etc).
 Provide validation messages through the console for all user actions.
 Use the DAO design pattern.
 Store Admin username/password and database connection information in a properties file.
 PL/SQL with at least one user defined function
 JDBC with statements and prepared statements
 Scanner for user input
 JUnit tests on all user defined Java methods
 Transactions will be logged to a file with Log4J.
Bonus:
 A user may view transaction history.
 Logging into a DB table.
Create a Maven project with your solution as [lastName]JDBCBank. include it in your DB creation 
script (JDBCBank.sql), along with a Markdown (.md) file with information necessary to run your application, 
i.e. instructions, usernames and passwords, etc.
 */

public class Driver {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int loginSelection = 0;

		while (loginSelection != 3) {

			loginSelection = Menu.MenuStart(sc);

			// menu login or apply for acct
			// 1-3 meet if criteria else login again
			if (loginSelection == 1) {
				String username = "";

				Customer loggedIn = Menu.menuLogin(sc);

				if (!loggedIn.getFirstName().equals("base")) {
					// logged in user functions
					Menu.listUserAccounts(loggedIn.getAccounts());
					
					//prevent unregistered user from adding accounts
					if (!loggedIn.getAccounts().isEmpty()) {
						int accountSelected = Menu.accountSelection(sc, loggedIn.getAccounts(), loggedIn.getUsername());
						Menu.accountFeatures(sc, accountSelected);
					}
				}

			} else if (loginSelection == 2) {
				// account application steps
				Menu.MenuApplyForAcct(sc);

			} else if (loginSelection == 3) {
				System.out.println("goodbye");
			} else {
				loginSelection = Menu.MenuStart(sc);
			}
		}

	}

}
