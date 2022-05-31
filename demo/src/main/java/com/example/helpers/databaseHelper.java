package com.example.helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class databaseHelper {
	public static String driver = "jdbc";
	public static String dbType = "mysql";
	public static String server = "localhost";
	public static int port = 3306;
	
	public static Connection DbConnect(String database) {
		String connectionString = driver + ":" + 
				dbType + "://" + 
				server +  ":" + port +
				 "/" + database;
		
		Connection connection = null;
		
		try {
			connection = DriverManager.getConnection(connectionString, "root", "tobiastobias123");
		} catch (SQLException e) {
			System.out.println("databas kan ej anslutas");
			e.printStackTrace();
		}

		return connection;
	}

}
