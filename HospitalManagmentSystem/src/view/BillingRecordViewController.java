package view;

import java.sql.ResultSet;
import java.sql.SQLException;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.BillingRecord;
import model.PatientChart;
import model.TransactionRecord;
import model.TreatmentRecord;
import util.DBUtil;

public class BillingRecordViewController {
	
	private Main main;
	
	private ObservableList<BillingRecord> records = FXCollections.observableArrayList();
	private int patientId;
	private PatientChart patient;
	private BillingRecord selectedRecord = null;

	@FXML
	private TableView<BillingRecord> recordTable;
	
	@FXML
	private TableColumn<BillingRecord, String> dateColumn;
	
	@FXML
	private TableColumn<BillingRecord, String> amountColumn;
	
	@FXML
	private TableColumn<BillingRecord, String> paymentTypeColumn;
	
	@FXML
	private TableColumn<BillingRecord, String> referenceNumberColumn;
	
	@FXML
	private void handleDetails() {
		if(this.selectedRecord == null) {
			return;
		} else {
			ResultSet rs = DBUtil.selectQuery("SELECT TransactionRecordId FROM TransactionRecord Where BillingId = '" + this.selectedRecord.getBillingRecordID() +"';");
			try {
				TransactionRecord transRecord = new TransactionRecord(rs.getInt(1));
				this.main.showNewPaymentPop(this.patient, transRecord, false);
			} catch (SQLException e) {
				e.printStackTrace();
				System.exit(-1);
			}
		}
	}
	
	@FXML
	private void handleDelete() {
		if(this.selectedRecord == null) {
			return;
		} else {
			DBUtil.updateQuery("UPDATE BillingRecord SET StatusId = 2 WHERE BillingRecordId = " + this.selectedRecord.getBillingRecordID());
			ResultSet rs = DBUtil.selectQuery("SELECT TransactionRecordId FROM TransactionRecord Where BillingId = '" + this.selectedRecord.getBillingRecordID() +"';");
			try {
//				TransactionRecord transRecord = new TransactionRecord(rs.getInt(1));
				DBUtil.updateQuery("UPDATE TransactionRecord SET StatusId = 2 WHERE TransactionRecordId = " + rs.getInt(1));
				
			} catch (SQLException e) {
				e.printStackTrace();
				System.exit(-1);
			}
			
			initialize();
		}
	}

	@FXML
	private void handleReturn() {
		this.main.showBillingView();
	}
	
	public void initialize() {
		recordTable.getItems().clear();
//		System.out.println("here");
//		System.out.println(this.patientId);
		ResultSet rs = DBUtil.selectQuery("SELECT BillingRecordId FROM BillingRecord Where PatientId = '" + this.patientId +"' AND StatusId = 1;");
				
		try {
			while(rs.next()) {
				this.records.add(new BillingRecord(rs.getInt(1)));				
			}			
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(-1);
		}

//		dateColumn.setCellValueFactory(cellData -> cellData.getValue().heightProperty());
		amountColumn.setCellValueFactory(cellData -> cellData.getValue().amountProperty());
		paymentTypeColumn.setCellValueFactory(cellData -> cellData.getValue().paymentTypeProperty());
		referenceNumberColumn.setCellValueFactory(cellData -> cellData.getValue().referenceNumberProperty());
		
		recordTable.setItems(this.records);
		
		recordTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> selectRecord(newValue));
	}
	
	public void setPatient(PatientChart patient) {
		this.patient = patient;
		this.patientId = patient.getPatientChartID();
		initialize();
		
	}
	
	private void selectRecord(BillingRecord record) {
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
