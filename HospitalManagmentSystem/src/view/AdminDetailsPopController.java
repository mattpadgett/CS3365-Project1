package view;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.User;
import sun.text.normalizer.ICUBinary.Authenticate;
import util.Authentication;
import util.DBUtil;

public class AdminDetailsPopController {
	private Main main;
	private User user;
	
	private boolean newUserMode;
	
	@FXML
	private TextField firstNameField, lastNameField, middleNameField, emailField, usernameField;
	
	@FXML
	private ComboBox<String> userTypeField;

	public void loadData(User user) {
		if(user == null) {
			
		} else {
			this.user = user;
			
			this.firstNameField.setText(this.user.getFirstName());
			this.lastNameField.setText(this.user.getLastName());
			this.middleNameField.setText(this.user.getMiddleName());
			this.emailField.setText(this.user.getEmail());
			this.usernameField.setText(this.user.getUsername());
			
			switch(this.user.getUserTypeId()) {
				case 1:
					this.userTypeField.getSelectionModel().select("Staff");
					break;
				case 2:
					this.userTypeField.getSelectionModel().select("Nurse");
					break;
				case 3:
					this.userTypeField.getSelectionModel().select("Doctor");
					break;
				case 4:
					this.userTypeField.getSelectionModel().select("CEO");
					break;
			}
		}
	}
	
	@FXML
	private void handleSave() {
		if(this.newUserMode) {
			User u = new User(userTypeField.getSelectionModel().getSelectedIndex() + 1, firstNameField.getText(), lastNameField.getText(), middleNameField.getText(), emailField.getText(), usernameField.getText());
			u.setPassHash(Authentication.hashPassphrase("password"));
		} else {
			DBUtil.updateQuery("UPDATE User SET FirstName = '" + firstNameField.getText() + "', "
				+ "LastName = '" + lastNameField.getText() + "', "
				+ "MiddleName = '" + middleNameField.getText() + "', "
				+ "Email = '" + emailField.getText() + "', "
				+ "Username = '" + usernameField.getText() + "', "
				+ "UserTypeId = '" + String.valueOf(userTypeField.getSelectionModel().getSelectedIndex() + 1) + "' "
				+ "WHERE UserId = '" + this.user.getUserID() + "'");
		}
		
		this.main.getAdminViewController().initialize();
		this.main.getPopStage().hide();
	}
	
	@FXML
	private void handleCancel() {
		this.main.getPopStage().hide();
	}
	
	public void initialize() {
		userTypeField.getItems().add("Staff");
		userTypeField.getItems().add("Nurse");
		userTypeField.getItems().add("Doctor");
		userTypeField.getItems().add("CEO");
	}
	
	public void setNewUserMode(boolean newUserMode) {
		this.newUserMode = newUserMode;
	}
	
	public void setMain(Main main) {
		this.main = main;
	}
}
