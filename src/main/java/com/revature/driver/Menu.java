package com.revature.driver;


import com.revature.service.Services;

public class Menu {

	public static boolean verifyLogin(String username, String password) {

		
		boolean validCredentials = false;

	

		Services userServ = new Services();
		String expectedPassword = "";

		if (!username.equals("admin")) {
			expectedPassword = userServ.getPassword(username);
		} else if (username.equals("admin")) {
			expectedPassword = "admin";
		}

		if (username.equals("admin") && password.equals("admin")) {
			validCredentials = true;

		} else if (password.equals(expectedPassword)) {
			validCredentials = true;
		}

		return validCredentials;

	}

}
