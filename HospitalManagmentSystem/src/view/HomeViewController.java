package view;

import application.Main;
import javafx.fxml.FXML;

public class HomeViewController {
	private Main main;

	@FXML
	private void handlePatients() {
		this.main.showPatientsView();
	}
	
	@FXML
	private void handleAdmin() {
		this.main.showAdminView();
	}
	
	@FXML
	private void handleViewProfile() {
		this.main.showProfileView();
	}
	
	@FXML
	private void handleLogout() {
		this.main.setLoggedUser(null);
		this.main.showLoginView();
	}
	
	public void setMain(Main main) {
		this.main = main;
	}
}
