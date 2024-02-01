package com.personalfinance.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

	
	Connection connection ;
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/demo";
	private static final String JDBC_URL_TRANDB = "jdbc:mysql://localhost:3306/demotran";
    private static final String USER = "root";
    private static final String PASSWORD = "R@kesh3592";
    
    public DbConnection() {
		
	}

	public Connection createMasterConnection() {
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection = DriverManager.getConnection(JDBC_URL,USER,PASSWORD);
		
	} catch (SQLException | ClassNotFoundException e) {
	    e.printStackTrace();
	}
	return connection;
		
	}
	
	public Connection createTranConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(JDBC_URL_TRANDB,USER,PASSWORD);
			
		} catch (SQLException | ClassNotFoundException e) {
		    e.printStackTrace();
		}
		return connection;
			
		}
	

}
