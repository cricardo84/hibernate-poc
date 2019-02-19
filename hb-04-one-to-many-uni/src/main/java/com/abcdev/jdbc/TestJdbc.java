package com.abcdev.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {

	public static void main(String[] args) {
		String jdbcUrl = "jdbc:mysql://localhost:3306/hb-01-one-to-one-uni?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
		String user = "hbstudent";
		String password = "hbstudent";
		
		try {
			System.out.println("Connecting to database: " + jdbcUrl);
			
			Connection connection = DriverManager.getConnection(jdbcUrl, user, password);
			
			System.out.println(connection);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
