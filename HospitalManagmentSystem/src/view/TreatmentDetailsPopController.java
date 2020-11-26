package view;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.PatientChart;
import model.TreatmentRecord;
import util.DBUtil;

public class TreatmentDetailsPopController {

	private Main main;
	private PatientChart patient;
	private TreatmentRecord record; 
	
	private boolean newRecordMode;
	
	@FXML
	private TextField dateField, weightField, heightField, bloodPressureField, visitReasonField, treatmentNoteField, prescriptionIDField;

	public void loadPatientData(PatientChart patient) {
		if(patient == null) {
			
		} else {
			this.patient = patient;

		}
	}
	
	public void loadRecordData(TreatmentRecord record) {
		if(record == null) {
			
		} else {
			this.record = record;
			
			this.dateField.setText(this.record.getDate());
			this.weightField.setText(Float.toString(this.record.getWeight()));
			this.heightField.setText(Float.toString(this.record.getHeight()));
			this.bloodPressureField.setText(this.record.getBloodPressure());
			this.visitReasonField.setText(this.record.getVisitReason());
			this.treatmentNoteField.setText(this.record.getTreatmentNote());
			this.prescriptionIDField.setText(Integer.toString(this.record.getPrescriptionNote()));
			
			if(!this.main.getLoggedUser().getUserType().equalsIgnoreCase("doctor")) {
				this.treatmentNoteField.setDisable(true);
				this.prescriptionIDField.setDisable(true);
			} else {
				this.treatmentNoteField.setDisable(false);
				this.prescriptionIDField.setDisable(false);
			}

		}
	}
	
	@FXML
	private void handleSave() {
		if(this.newRecordMode) {
			TreatmentRecord r = new TreatmentRecord(this.patient, dateField.getText(), Float.valueOf(weightField.getText()), Float.valueOf(heightField.getText()), bloodPressureField.getText(), visitReasonField.getText(), treatmentNoteField.getText(), Integer.valueOf(prescriptionIDField.getText()));
		} else {
			DBUtil.updateQuery("UPDATE TreatmentRecord SET Date = '" + dateField.getText() + "', "
				+ "Weight = '" + weightField.getText() + "', "
				+ "Height = '" + heightField.getText() + "', "
				+ "BloodPressure = '" + bloodPressureField.getText() + "', "
				+ "VisitReason = '" + visitReasonField.getText() + "', "
				+ "TreatmentNote = '" + treatmentNoteField.getText() + "', "
				+ "PrescriptionId = '" + prescriptionIDField.getText() + "' "
				+ "WHERE TreatmentRecordId = '" + this.record.getTreatmentRecordID() + "'");
		}
		
		this.main.getTreatmentRecordViewController().initialize();
		this.main.getPopStage().hide();
	}
	
	@FXML
	private void handleCancel() {
		this.main.getPopStage().hide();
	}
	
	public void setNewRecordMode(boolean newRecordMode) {
		this.newRecordMode = newRecordMode;
	}
	
	public void setMain(Main main) {
		this.main = main;
	}
	
}
