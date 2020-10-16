package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {
	public static final String CONN_STRING = "jdbc:sqlite:src/resource/HMS.db";
	
	public static ResultSet selectQuery(String query) {
		try {
			Connection conn = DriverManager.getConnection(CONN_STRING);
			
			return conn.createStatement().executeQuery(query);			
		} catch(SQLException e) {
			e.printStackTrace();			
		}
		
		return null;
	}
	
	public static int updateQuery(String query) {
		try {
			Connection conn = DriverManager.getConnection(CONN_STRING);
			
			return conn.createStatement().executeUpdate(query);			
		} catch(SQLException e) {
			e.printStackTrace();			
		}
		
		return -1;
	}
}
