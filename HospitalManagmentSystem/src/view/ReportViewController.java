package view;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import model.Appointment;
import model.PatientChart;
import model.ReportData;
import model.TreatmentRecord;
import model.User;
import util.DBUtil;
import util.Report;

public class ReportViewController {
	private Main main;	
	
	private Report report;
	private ObservableList<ReportData> reportData = FXCollections.observableArrayList();	
	
	@FXML
	private DatePicker reportDate;
	
	@FXML
	private TableView<ReportData> reportTable;
	
	@FXML
	private TableColumn<ReportData, String> doctorColumn;
	
	@FXML
	private TableColumn<ReportData, String> pateintsColumn;
	
	@FXML
	private Label amountEarned;
	
	@FXML
	private void handleGo() {
		reportTable.getItems().clear();
		
		ResultSet rs = DBUtil.selectQuery("SELECT ReportId FROM Report WHERE Date LIKE '" + this.reportDate.getValue().format(DateTimeFormatter.ISO_DATE) + "%' LIMIT 1;");
		try {
			if(rs.next()) {				
				this.report = new Report(rs.getInt(1));				
			} else {
				this.report = Report.generateReport(this.reportDate.getValue());
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		for(int i = 0; i < this.report.getDoctors().size(); i++) {
			this.reportData.add(new ReportData(this.report.getDoctors().get(i).getLastName() + ", " + this.report.getDoctors().get(i).getFirstName(), this.report.getPatientCounts().get(i)));
		}
		
		if(this.report.getPaymentAmounts() == null) {
			this.amountEarned.setText("Amount Earned: $0.00");
		} else {
			this.amountEarned.setText("Amount Earned: $" + this.report.getPaymentAmounts());
		}		
		
		doctorColumn.setCellValueFactory(cellData -> cellData.getValue().doctorProperty());
		pateintsColumn.setCellValueFactory(cellData -> cellData.getValue().patientsProperty());
				
		reportTable.setItems(reportData);
	}

	@FXML
	private void handleReturn() {
		this.main.showHomeView();
	}
	
	public void setup() {
		this.reportDate.setValue(LocalDate.now());
		this.reportDate.setDayCellFactory(param -> new DateCell() {
	        @Override
	        public void updateItem(LocalDate date, boolean empty) {
	            super.updateItem(date, empty);
	            setDisable(empty || date.compareTo(LocalDate.now()) > 0 );
	        }
	    });
		handleGo();
	}
	
	public void setMain(Main main) {
		this.main = main;
	}

}
