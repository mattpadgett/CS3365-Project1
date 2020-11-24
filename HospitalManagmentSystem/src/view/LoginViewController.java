package view;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.sun.org.apache.bcel.internal.generic.Select;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.User;
import util.Authentication;
import util.DBUtil;

public class LoginViewController {
	private Main main;
	
	@FXML
	private TextField usernameField;
	
	@FXML
	private PasswordField passwordField;
	
	@FXML
	private void handleLogin() {
		ResultSet rs = DBUtil.selectQuery("SELECT * FROM User WHERE Username = '" + usernameField.getText() + "' AND StatusId = 1 LIMIT 1;");
		
		try {
			if(rs.next()) {
				if(Authentication.verifyPassphrase(passwordField.getText(), rs.getString(8))) {
					this.main.setLoggedUser(new User(rs.getInt(1)));
					System.out.println("Authenticated.");
					this.main.showHomeView();
				} else {
					System.out.println("Invalid Password.");
				}
			} else {
				System.out.println("Invalid Username.");
			}
						
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(-1);
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
