package view;

import java.sql.ResultSet;
import java.sql.SQLException;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import util.DBUtil;

public class LoginViewController {
	private Main main;
	
	@FXML
	private TextField usernameField;
	
	@FXML
	private PasswordField passwordField;
	
	@FXML
	private void handleLogin() {
		System.out.println("Do the login.");
		
		ResultSet rs = DBUtil.selectQuery("SELECT * FROM User;");
		
		try {
			while(rs.next()) {
				System.out.println(rs.getInt(1) + ", " + rs.getString(3) + ", " + rs.getString(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void handleExit() {
		System.exit(0);
	}
	
	public void setMain(Main main) {
		this.main = main;
	}
}
