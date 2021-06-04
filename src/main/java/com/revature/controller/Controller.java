package com.revature.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.revature.driver.Menu;
import com.revature.model.*;
import com.revature.service.Services;

@Path("/c")
public class Controller extends HttpServlet {
	
	final static Logger logger = LogManager.getLogger(Menu.class);


	@Path("/login")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean Login(Login payload) {

		

		String username = payload.getUsername();
		String password = payload.getPassword();

		boolean validCredentials = Menu.verifyLogin(username, password);

		return validCredentials;

	}

	@Path("/account")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<AccountsAndBalance> getAccounts(String username) {

		Services userServ = new Services();
		ArrayList<Integer> accountIDs = userServ.getCustomerAccounts(username);
		ArrayList<AccountsAndBalance> accAndBal = new ArrayList<AccountsAndBalance>();
		
		for (int i = 0; i < accountIDs.size(); i++) {
			int id = accountIDs.get(i);
			double bal = userServ.getBalance(id);
			
			AccountsAndBalance acc = new AccountsAndBalance(id,bal);
			accAndBal.add(acc);
		}
		
		
		
		return accAndBal;
	}

	@Path("/deposit")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public void deposit (AccountsAndBalance idAndBal) {
		
		int accid = idAndBal.getAccid();
		double deposit = idAndBal.getBalance();
		
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
	
	@Path("/withdraw")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public void withdraw (AccountsAndBalance idAndBal) {
		
		int accid = idAndBal.getAccid();
		double withdraw = idAndBal.getBalance();
		
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
