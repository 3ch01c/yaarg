package edu.nmt.cs.itweb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://localhost/it321", "root","toor");
	}
	
	public static Statement createStatement() throws SQLException {
		Connection con = getConnection();
		return con.createStatement();
	}
}
