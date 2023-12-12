package com.jdbc.util;

import java.security.PublicKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
	private static final String DRIVER_PATH="com.mysql.cj.jdbc.Driver";
	private static final String DATABASE_URL="jdbc:mysql://localhost:3306/jdbcdb";
	private static final String USERNAME="root";
	private static final String PASSWORD="Nisha@123";
	
	public DatabaseUtil() {
	    try {
	        Class.forName(DRIVER_PATH);
	    } catch (ClassNotFoundException e) {
	        throw new RuntimeException("MySQL JDBC Driver not found. Include the driver in your classpath." + e);
	    }
	}

	// end of constructor

		public Connection getConnection() throws SQLException {
			return DriverManager.getConnection(DATABASE_URL,USERNAME,PASSWORD);
		}// end of get connection
}