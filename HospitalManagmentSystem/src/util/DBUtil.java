package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
	public static ResultSet selectQuery(String query) {
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:src/resource/HMS.db");
			
			try {
				Statement statement = conn.createStatement();
				return statement.executeQuery(query);
			} catch(SQLException e) {
				conn.close();
				e.printStackTrace();
			}
		} catch(SQLException e) {
			e.printStackTrace();			
		}
		
		return null;
	}
}
