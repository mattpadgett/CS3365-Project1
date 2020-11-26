package view;

import java.sql.ResultSet;
import java.sql.SQLException;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import model.Appointment;
import model.PatientChart;
import model.TreatmentRecord;
import util.DBUtil;

public class AppointmentsViewController {
	private Main main;
	
	private ObservableList<Appointment> appts = FXCollections.observableArrayList();
	private Appointment selectedAppt = null;	
	private PatientChart patient;
	
	@FXML
	private AnchorPane anchorPane;
	
	@FXML
	private TableView<Appointment> apptsTable;
	
	@FXML
	private TableColumn<Appointment, String> dateColumn;
	
	@FXML
	private TableColumn<Appointment, String> timeColumn;
	
	@FXML
	private TableColumn<Appointment, String> doctorColumn;
	
	@FXML
	private TableColumn<Appointment, String> statusColumn;
	
	@FXML
	private void handleDetails() {
		if(this.selectedAppt == null) {
			return;
		} else {
			this.main.showAppointmentDetailsPop(this.patient, this.selectedAppt, false);
		}
	}
	
	@FXML
	private void handleNew() {
		this.main.showAppointmentDetailsPop(this.patient, null, true);
	}

	@FXML
	private void handleReturn() {
		this.main.showPatientsView();
	}
	
	@FXML
	private void handleDelete() {
		this.selectedAppt.setStatusId(2);
		setup();
	}
	
	@FXML
	private void handleNoShow() {
		this.selectedAppt.setStatusId(3);
		setup();
	}
	
	public void setup() {
		apptsTable.getItems().clear();
		
		ResultSet rs = DBUtil.selectQuery("SELECT AppointmentId FROM Appointment WHERE PatientId = '" + this.patient.getPatientChartID() + "' AND (StatusId = 1 OR StatusId = 3);");
				
		try {
			while(rs.next()) {
				this.appts.add(new Appointment(rs.getInt(1)));				
			}			
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
		dateColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
		timeColumn.setCellValueFactory(cellData -> cellData.getValue().timeProperty());
		doctorColumn.setCellValueFactory(cellData -> cellData.getValue().doctorProperty());
		statusColumn.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
		
		apptsTable.setItems(appts);
		apptsTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> selectAppt(newValue));
	}
	
	public void setPatient(PatientChart patient) {
		this.patient = patient;
	}
	
	private void selectAppt(Appointment record) {
		if(record != null) {
			this.selectedAppt = record;
		} else {
			this.selectedAppt = null;
		}
	}
	
	public void setMain(Main main) {
		this.main = main;
	}
	
	public AnchorPane getAnchorPane() {
		return this.anchorPane;
	}
}
