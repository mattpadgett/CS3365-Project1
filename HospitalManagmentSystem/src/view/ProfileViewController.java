package view;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import model.User;
import util.Authentication;
import util.DBUtil;

public class ProfileViewController {
	private Main main;
	
	@FXML
	private Text nameLabel, emailLabel, usernameLabel, userTypeLabel;
	
	@FXML
	private PasswordField newPasswordField;
	
	@FXML
	private void handleSave() {
		if(!this.newPasswordField.getText().isEmpty()) {
			this.main.getLoggedUser().setPassHash(Authentication.hashPassphrase(this.newPasswordField.getText()));
		}
		
		this.main.getPopStage().hide();
	}
	
	@FXML
	private void handleCancel() {
		this.main.getPopStage().hide();
	}
	
	public void setup() {
		this.nameLabel.setText(this.main.getLoggedUser().getLastName() + ", " + this.main.getLoggedUser().getFirstName() + " " + this.main.getLoggedUser().getMiddleName());
		this.emailLabel.setText(this.main.getLoggedUser().getEmail());
		this.usernameLabel.setText(this.main.getLoggedUser().getUsername());
		this.userTypeLabel.setText(this.main.getLoggedUser().getUserType());
	}
	
	public void setMain(Main main) {
		this.main = main;
	}
}
