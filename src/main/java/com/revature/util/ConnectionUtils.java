package com.revature.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtils {

	public static Connection getConnection() throws SQLException {

		try {

			Class.forName("org.postgresql.Driver"); // gets drivers needeed to connect for Postgresql

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String url = "";
		String username = "";
		String password = "";

		try {
			FileInputStream fileStream = new FileInputStream("../Project1/src/main/resources/DB_properties");

			Properties prop = new Properties();
			prop.load(fileStream);
			url = prop.getProperty("URL");
			username = prop.getProperty("DB_Username");
			password = prop.getProperty("DB_Password");

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e2) {
			e2.printStackTrace();
		} catch (Exception e3) {
			e3.printStackTrace();
		}

//		String url = "jdbc:postgresql://sql-demo-database-1.chpcst4egchx.us-east-2.rds.amazonaws.com:5432/Sqldemo";
//		String username = "postgres";
//		String password = "postgres123";

		return DriverManager.getConnection(url, username, password);
	}
//
//	public static void main(String[] args) {
//
//		try (Connection conn = ConnectionUtils.getConnection()) {
//
//			System.out.println("Connection was successful");
//
//		} catch (SQLException e) {
//			// TODO: handle exception
//		}
//	}

}
