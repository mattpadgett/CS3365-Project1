package view;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HomeViewController {
	private Main main;
	
	@FXML
	private Button patientsBtn, billingBtn, adminBtn, appointmentsBtn, reportingBtn;

	@FXML
	private void handlePatients() {
		this.main.showPatientsView();
	}
	
	@FXML
	private void handleBilling() {
		this.main.showBillingView();
	}
	
	@FXML
	private void handleAdmin() {
		this.main.showAdminView();
	}
	
	@FXML
	private void handleReporting() {
		this.main.showReportView();
	}
	
	@FXML
	private void handleViewProfile() {
		this.main.showProfileView();
	}
	
	public void setupAccess() {
		switch(this.main.getLoggedUser().getUserType().toLowerCase()) {
			case "ceo":
				this.patientsBtn.setVisible(true);
				this.billingBtn.setVisible(true);
				this.adminBtn.setVisible(true);
				this.reportingBtn.setVisible(true);
				break;
			case "nurse":
				this.patientsBtn.setVisible(true);
				this.billingBtn.setVisible(false);
				this.adminBtn.setVisible(false);
				this.reportingBtn.setVisible(false);
				break;
			case "doctor":
				this.patientsBtn.setVisible(true);
				this.billingBtn.setVisible(false);
				this.adminBtn.setVisible(false);
				this.reportingBtn.setVisible(false);
				break;
			case "staff":
				this.patientsBtn.setVisible(true);
				this.billingBtn.setVisible(true);
				this.adminBtn.setVisible(false);
				this.reportingBtn.setVisible(false);
				break;
		}
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
