package view;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.PatientChart;
import util.DBUtil;

public class PatientsViewController {

	private Main main;
	
	private ObservableList<PatientChart> patients = FXCollections.observableArrayList();
	private PatientChart selectedPatient = null;
	
	@FXML
	private TableView<PatientChart> patientsTable;
	
	@FXML
	private TableColumn<PatientChart, String> firstNameColumn;
	
	@FXML
	private TableColumn<PatientChart, String> lastNameColumn;
	
	@FXML
	private TableColumn<PatientChart, String> addressColumn;
	
	@FXML
	private TableColumn<PatientChart, String> insuranceColumn;
	
	@FXML
	private void handleDetails() {
		if(this.selectedPatient == null) {
			return;
		} else {
			this.main.showPatientsDetailsPop(this.selectedPatient, false);
		}
	}
	
	@FXML
	private void handleDelete() {
		if(this.selectedPatient == null) {
			return;
		} else {
			DBUtil.updateQuery("UPDATE PatientChart SET StatusId = 2 WHERE PatientChartId = " + this.selectedPatient.getPatientChartID());
			initialize();
		}
	}
	
	@FXML
	private void handleNew() {
		this.main.showPatientsDetailsPop(null, true);
	}
	
	@FXML
	private void handleTreatmentRecord() {
		if(this.selectedPatient == null) {
			return;
		} else {
			this.main.showTreatmentRecordView(this.selectedPatient);
		}		
	}
	
	@FXML
	private void handleReturn() {
		this.main.showHomeView();
	}
	
	public void initialize() {
		patientsTable.getItems().clear();
		
		ResultSet rs = DBUtil.selectQuery("SELECT PatientChartId FROM PatientChart Where StatusId = 1;");
		
		try {
			while(rs.next()) {
				this.patients.add(new PatientChart(rs.getInt(1)));				
			}			
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
		firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
		lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
		addressColumn.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
		insuranceColumn.setCellValueFactory(cellData -> cellData.getValue().insuranceProperty());
		
		patientsTable.setItems(this.patients);
		
		patientsTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> selectPatient(newValue));
	}
	
	private void selectPatient(PatientChart patient) {
		if(patient != null) {
			this.selectedPatient = patient;
		} else {
			this.selectedPatient = null;
		}
	}
	
	public void setMain(Main main) {
		this.main = main;
	}
	
}
