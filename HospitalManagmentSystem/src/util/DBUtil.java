package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.Main;

public class DBUtil {
	public static final String CONN_STRING = "jdbc:sqlite:" + System.getProperty("user.home") + "/Desktop/HospitalManagementSystem/HMS.db";
	public static Connection conn = null;
	
	public static Connection getConn() {
		if(conn == null) {
			try {
				Class.forName("org.sqlite.JDBC");				
				conn = DriverManager.getConnection(CONN_STRING);
			} catch (SQLException e) {
				System.err.println("Couldn't connect to the database.");
				System.exit(9000);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				System.exit(9001);
			}
		}
		
		return conn;
	}
	
	public static ResultSet selectQuery(String query) {
		try {
			return getConn().createStatement().executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static int updateQuery(String query) {
		try {
			return getConn().createStatement().executeUpdate(query);			
		} catch(SQLException e) {
			e.printStackTrace();			
		}
		
		return -1;
	}
	
	public static PreparedStatement insertQuery(String query) {
		try {
			return getConn().prepareStatement(query);
		} catch(SQLException e) {
			e.printStackTrace();			
		}
		
		return null;
	}
}
