package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.beans.property.SimpleStringProperty;
import util.Authentication;
import util.DBUtil;

public class User {
	
	private int userID;
	private int userTypeId;
	private String firstName;
	private String lastName;
	private String middleName;
	private String email;
	private String username;
	private String passHash;
	
	public User(int userTypeId, String firstName, String lastName, String middleName, String email, String username) {
		
		//this.userID = userID;
		this.userTypeId = userTypeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.email = email;
		this.username = username;
		//this.passHash = passHash;
		
		boolean skipFlag = false;
		
		ResultSet rs = DBUtil.selectQuery("SELECT Email FROM User;");
		try {
			while(rs.next()) {
				if(rs.getString(1).equals(this.email)) {
					
					skipFlag = true;
				}
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(skipFlag == false) {
			
			PreparedStatement pstmt = DBUtil.insertQuery("INSERT INTO User(userTypeId,firstName,lastName,middleName,email,username,StatusId) VALUES (?,?,?,?,?,?,?);");
			try {
				
				pstmt.setInt(1, this.userTypeId);
				pstmt.setString(2, this.firstName);
				pstmt.setString(3, this.lastName);
				pstmt.setString(4, this.middleName);
				pstmt.setString(5, this.email);
				pstmt.setString(6, this.username);
				pstmt.setInt(7, 1);
				pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		ResultSet r = DBUtil.selectQuery("SELECT MAX(UserId) FROM User;");
		
		try {
			
			this.userID = r.getInt(1);
			r.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public User(int userId) {
		ResultSet rs = DBUtil.selectQuery("SELECT * FROM User WHERE UserId = '" + userId + "' LIMIT 1;");
		
		try {
			if(rs.next()) {
				this.userID = rs.getInt(1);
				this.userTypeId = rs.getInt(2);
				this.firstName = rs.getString(3);
				this.lastName = rs.getString(4);
				this.middleName = rs.getString(5);
				this.email = rs.getString(6);
				this.username = rs.getString(7);
				this.passHash = rs.getString(8);				
			} else {
				System.out.println("Invalid UserId.");
			}
						
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	public int getUserID() {
		
		return this.userID;
	}
	
	public void setUserID(int newUserId) {
		
		this.userID = newUserId;
	}
	
	public int getUserTypeId() {	
		return this.userTypeId;
	}
	
	public String getUserType() {
		ResultSet rs = DBUtil.selectQuery("SELECT UserType FROM Resource_UserType WHERE UserTypeId = '" + getUserTypeId() + "';");
		
		try {
			if(rs.next()) {
				return rs.getString(1);
			} else {
				System.out.println("Invalid UserTypeId.");
			}
						
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
		return "";
	}
	
	public void setUserTypeId(int newUserId) {
		
		this.userTypeId = newUserId;
		
		PreparedStatement pstmt = DBUtil.insertQuery("UPDATE User SET UserTypeId = ? " + "WHERE UserId = ? ;");
		try {
			
			pstmt.setInt(1, this.userTypeId);
			pstmt.setInt(2, this.userID);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String getFirstName() {
		
		return this.firstName;
	}
	
	public void setFirstName(String newFirstName) {
		
		this.firstName = newFirstName;
		
		PreparedStatement pstmt = DBUtil.insertQuery("UPDATE User SET FirstName = ? " + "WHERE UserId = ? ;");
		try {
			
			pstmt.setString(1, this.firstName);
			pstmt.setInt(2, this.userID);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String getLastName() {
		
		return this.lastName;
	}
	
	public void setLastName(String newLastName) {
		
		this.lastName = newLastName;
		
		PreparedStatement pstmt = DBUtil.insertQuery("UPDATE User SET LastName = ? " + "WHERE UserId = ? ;");
		try {
			
			pstmt.setString(1, this.lastName);
			pstmt.setInt(2, this.userID);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String getMiddleName() {
		
		return this.middleName;
	}
	
	public void setMiddleName(String newMiddleName) {
		
		this.middleName = newMiddleName;
		
		PreparedStatement pstmt = DBUtil.insertQuery("UPDATE User SET MiddleName = ? " + "WHERE UserId = ? ;");
		try {
			
			pstmt.setString(1, this.middleName);
			pstmt.setInt(2, this.userID);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String getEmail() {
		
		return this.email;
	}
	
	public void setEmail(String newEmail) {
		
		this.email = newEmail;
		
		PreparedStatement pstmt = DBUtil.insertQuery("UPDATE User SET Email = ? " + "WHERE UserId = ? ;");
		try {
			
			pstmt.setString(1, this.email);
			pstmt.setInt(2, this.userID);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String getUsername() {
		
		return this.username;
	}
	
	public void setUsername(String newUsername) {
		
		this.username = newUsername;
		
		PreparedStatement pstmt = DBUtil.insertQuery("UPDATE User SET Username = ? " + "WHERE UserId = ? ;");
		try {
			
			pstmt.setString(1, this.username);
			pstmt.setInt(2, this.userID);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String getPassHash() {
		
		return this.passHash;
	}
	
	public void setPassHash(String newPasshash) {
		this.passHash = newPasshash;
		
		PreparedStatement pstmt = DBUtil.insertQuery("UPDATE User SET PasswordHash = ? " + "WHERE UserId = ? ;");
		try {
			
			pstmt.setString(1, this.passHash);
			pstmt.setInt(2, this.userID);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public SimpleStringProperty usernameProperty() {
		return new SimpleStringProperty(getUsername());
	}
	
	public SimpleStringProperty firstNameProperty() {
		return new SimpleStringProperty(getFirstName());
	}
	
	public SimpleStringProperty lastNameProperty() {
		return new SimpleStringProperty(getLastName());
	}
	
	public SimpleStringProperty userTypeProperty() {
		return new SimpleStringProperty(getUserType());
	}
}
