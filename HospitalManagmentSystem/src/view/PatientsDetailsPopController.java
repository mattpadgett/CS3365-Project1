package view;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.PatientChart;
import model.User;
import sun.text.normalizer.ICUBinary.Authenticate;
import util.Authentication;
import util.DBUtil;

public class PatientsDetailsPopController {

	private Main main;
	private PatientChart patient;
	
	private boolean newPatientMode;
	
	@FXML
	private TextField firstNameField, lastNameField, middleNameField, address1Field, address2Field, cityField, stateField, zipCodeField, socialSecurityNumberField, insuranceProviderField;

	public void loadData(PatientChart patient) {
		if(patient == null) {
			
		} else {
			this.patient = patient;
			
			this.firstNameField.setText(this.patient.getFirstName());
			this.lastNameField.setText(this.patient.getLastName());
			this.middleNameField.setText(this.patient.getMiddleName());
			this.address1Field.setText(this.patient.getAddress1());
			this.address2Field.setText(this.patient.getAddress2());
			this.cityField.setText(this.patient.getCity());
			this.stateField.setText(this.patient.getState());
			this.zipCodeField.setText(this.patient.getZipCode());
			this.socialSecurityNumberField.setText(this.patient.getSocialSecurityNumber());
			this.insuranceProviderField.setText(this.patient.getInsuranceProvider());

		}
	}
	
	@FXML
	private void handleSave() {
		if(this.newPatientMode) {
			PatientChart p = new PatientChart(firstNameField.getText(), lastNameField.getText(), middleNameField.getText(), address1Field.getText(), address2Field.getText(), cityField.getText(), stateField.getText(), zipCodeField.getText(), socialSecurityNumberField.getText(), insuranceProviderField.getText());
		} else {
			DBUtil.updateQuery("UPDATE PatientChart SET FirstName = '" + firstNameField.getText() + "', "
				+ "LastName = '" + lastNameField.getText() + "', "
				+ "MiddleName = '" + middleNameField.getText() + "', "
				+ "Address1 = '" + address1Field.getText() + "', "
				+ "Address2 = '" + address2Field.getText() + "', "
				+ "City = '" + cityField.getText() + "', "
				+ "State = '" + stateField.getText() + "', "
				+ "ZipCode = '" + zipCodeField.getText() + "', "
				+ "SocialSecurityNumber = '" + socialSecurityNumberField.getText() + "', "
				+ "InsuranceProvider = '" + insuranceProviderField.getText() + "' "
				+ "WHERE PatientChartId = '" + this.patient.getPatientChartID() + "'");
		}
		
		this.main.getPatientsViewController().initialize();
		System.out.println(this.patient.getPatientChartID());
		this.main.getPopStage().hide();
	}
	
	@FXML
	private void handleCancel() {
		this.main.getPopStage().hide();
	}
	
	public void setNewPatientMode(boolean newPatientMode) {
		this.newPatientMode = newPatientMode;
	}
	
	public void setMain(Main main) {
		this.main = main;
	}
	
}
