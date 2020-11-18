package view;

import java.sql.ResultSet;
import java.sql.SQLException;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.PatientChart;
import model.TreatmentRecord;
import util.DBUtil;

public class TreatmentRecordViewController {

private Main main;
	
	private ObservableList<TreatmentRecord> records = FXCollections.observableArrayList();
	private TreatmentRecord selectedRecord = null;
	private int patientId;
	private PatientChart patient;
	
	@FXML
	private TableView<TreatmentRecord> recordTable;
	
	@FXML
	private TableColumn<TreatmentRecord, String> dateColumn;
	
	@FXML
	private TableColumn<TreatmentRecord, String> weightColumn;
	
	@FXML
	private TableColumn<TreatmentRecord, String> heightColumn;
	
	@FXML
	private TableColumn<TreatmentRecord, String> bloodPressureColumn;
	
	@FXML
	private TableColumn<TreatmentRecord, String> visitReasonColumn;
	
	@FXML
	private TableColumn<TreatmentRecord, String> treatmentNoteColumn;
	
	
	@FXML
	private TableColumn<TreatmentRecord, String> prescriptionIdColumn;
	
	@FXML
	private void handleDetails() {
		if(this.selectedRecord == null) {
			return;
		} else {
			this.main.showTreatmentDetailsPop(this.patient, this.selectedRecord, false);
		}
	}
	
//	@FXML
//	private void handleDelete() {
//		if(this.selectedRecord == null) {
//			return;
//		} else {
//			DBUtil.updateQuery("UPDATE TreatmentRecord SET StatusId = 2 WHERE PatientChartId = " + this.selectedPatient.getPatientChartID());
//			initialize();
//		}
//	}
	
	@FXML
	private void handleNew() {
		this.main.showTreatmentDetailsPop(this.patient, null, true);
	}

	@FXML
	private void handleReturn() {
		this.main.showPatientsView();
	}
	
	public void initialize() {
		recordTable.getItems().clear();
		System.out.println("here");
		System.out.println(this.patientId);
		ResultSet rs = DBUtil.selectQuery("SELECT TreatmentRecordId FROM TreatmentRecord Where PatientId = '" + this.patientId + "';");
				
		try {
			while(rs.next()) {
				this.records.add(new TreatmentRecord(rs.getInt(1)));				
			}			
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
		dateColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
		weightColumn.setCellValueFactory(cellData -> cellData.getValue().weightProperty());
		heightColumn.setCellValueFactory(cellData -> cellData.getValue().heightProperty());
		bloodPressureColumn.setCellValueFactory(cellData -> cellData.getValue().bloodPressureProperty());
		visitReasonColumn.setCellValueFactory(cellData -> cellData.getValue().visitReasonProperty());
		treatmentNoteColumn.setCellValueFactory(cellData -> cellData.getValue().treatmentNoteProperty());
		prescriptionIdColumn.setCellValueFactory(cellData -> cellData.getValue().prescriptionProperty());
		
		recordTable.setItems(this.records);
		
		recordTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> selectRecord(newValue));
	}
	
	public void setPatient(PatientChart patient) {
		this.patient = patient;
		this.patientId = patient.getPatientChartID();
		initialize();
		
	}
	
	private void selectRecord(TreatmentRecord record) {
		if(record != null) {
			this.selectedRecord = record;
		} else {
			this.selectedRecord = null;
		}
	}
	
	public void setMain(Main main) {
		this.main = main;
	}
	
}
