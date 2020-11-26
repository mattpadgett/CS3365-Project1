package view;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.Appointment;
import model.PatientChart;
import model.TreatmentRecord;
import model.User;
import util.Authentication;
import util.DBUtil;
import util.Scheduling;

public class AppointmentDetailsPopController {
	private Main main;
	private PatientChart patient;
	private Appointment appt; 
	
	private boolean newApptMode;
	
	private ObservableList<User> doctors = FXCollections.observableArrayList();
	private ObservableList<String> times = FXCollections.observableArrayList(); 
	
	@FXML
	private ComboBox<User> doctorField;
	
	@FXML
	private ComboBox<String> timeField;
	
	@FXML
	private DatePicker dateField;
	
	@FXML
	private void handleSave() {
		if(newApptMode) {
			Appointment appt = new Appointment(this.doctorField.getSelectionModel().getSelectedItem().getUserID(), this.patient.getPatientChartID(), LocalDateTime.of(dateField.getValue(), LocalTime.parse(timeField.getValue(), DateTimeFormatter.ISO_TIME)));
		} else {
			this.appt.setDoctorIdNumber(this.doctorField.getSelectionModel().getSelectedItem().getUserID());
			this.appt.setAppointmentTime(LocalDateTime.of(dateField.getValue(), LocalTime.parse(timeField.getValue(), DateTimeFormatter.ISO_TIME)));
		}
		
		this.main.getAppointmentsViewController().setup();
		this.main.getPopStage().hide();
		this.main.getAppointmentsViewController().getAnchorPane().setDisable(false);
	}
	
	@FXML
	private void handleCancel() {
		this.main.getPopStage().hide();
		this.main.getAppointmentsViewController().getAnchorPane().setDisable(false);
	}
		
	public void setup(PatientChart patient, Appointment appt, boolean newApptMode) {
		this.patient = patient;
		this.appt = appt;
		this.newApptMode = newApptMode;
		
		int currentDoctorIndex = 0;
		
		// Setup Doctors
		ResultSet rs = DBUtil.selectQuery("SELECT UserId FROM User WHERE UserTypeId = 3 AND StatusId = 1;");			
		
		try {
			while(rs.next()) {
				if(!newApptMode && rs.getInt(1) == this.appt.getDoctorIdNumber()) currentDoctorIndex = doctors.size();
				
				doctors.add(new User(rs.getInt(1)));
			}		
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
		doctorField.setItems(doctors);
		
		if(!newApptMode) {
			dateField.setValue(this.appt.getAppointmentTime().toLocalDate());
			times.add(this.appt.getAppointmentTime().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm")));
			
			ArrayList<LocalDateTime> availableTimes = Scheduling.getAvailability(this.appt.getDoctorIdNumber(), this.appt.getAppointmentTime().toLocalDate());
			
			for(int i = 0; i < availableTimes.size(); i++) {
				times.add(availableTimes.get(i).format(DateTimeFormatter.ofPattern("HH:mm")));
			}
			
			timeField.setItems(times);
			timeField.getSelectionModel().select(0);
			
			doctorField.getSelectionModel().select(currentDoctorIndex);
		} else {
			dateField.setDisable(true);
			timeField.setDisable(true);
			
			dateField.setValue(LocalDate.now());
		}
	}
	
	@FXML
	private void handleChangeDate() {
		timeField.setDisable(true);
		timeField.getItems().clear();
		
		ArrayList<LocalDateTime> availableTimes = Scheduling.getAvailability(doctorField.getSelectionModel().getSelectedItem().getUserID(), dateField.getValue());
		
		for(int i = 0; i < availableTimes.size(); i++) {
			times.add(availableTimes.get(i).format(DateTimeFormatter.ofPattern("HH:mm")));
		}
		
		timeField.setItems(times);
		timeField.setDisable(false);
	}
	
	@FXML
	private void handleDoctorChange() {
		dateField.setDisable(false);
		handleChangeDate();
	}
	
	public void setMain(Main main) {
		this.main = main;
	}
}
