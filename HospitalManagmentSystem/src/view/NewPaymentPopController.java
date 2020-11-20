package view;

import java.util.Random;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.BillingRecord;
import model.PatientChart;
import model.TransactionRecord;
import model.User;
import util.Authentication;
import util.DBUtil;

public class NewPaymentPopController {

	private Main main;
	private TransactionRecord transaction;
	private PatientChart patient;
	
	private boolean newTransactionMode;
	
	@FXML
	private TextField amountField;
	
	@FXML
	private ComboBox<String> paymentTypeField;

	public void loadData(TransactionRecord transaction, PatientChart patient) {
		if(transaction == null) {
			this.patient = patient;
			
		} else {
			this.transaction = transaction;
			System.out.println(this.transaction.getAmount());
			this.amountField.setText(Double.toString(this.transaction.getAmount()));
			
			switch(this.transaction.getTransactionTypeID()) {
				case 1:
					this.paymentTypeField.getSelectionModel().select("Cash");
					break;
				case 2:
					this.paymentTypeField.getSelectionModel().select("Card");
					break;
			}
		}
	}
	
	@FXML
	private void handleSave() {
		if(this.newTransactionMode) {
			Random rnd = new Random(System.currentTimeMillis());
			BillingRecord b = new BillingRecord(this.patient.getPatientChartID(), rnd.nextInt(9000), Double.valueOf(amountField.getText()));
			System.out.println("bill:");
			System.out.println(b.getBillingRecordID());
			TransactionRecord t = new TransactionRecord(paymentTypeField.getSelectionModel().getSelectedIndex() + 1, b.getBillingRecordID(), rnd.nextInt(9000), Double.valueOf(amountField.getText()));
		} else {
			DBUtil.updateQuery("UPDATE BillingRecord SET Amount = '" + amountField.getText() + "' "
					+ "WHERE BillingRecordId = '" + this.transaction.getBillingID() + "'");
			
			
			DBUtil.updateQuery("UPDATE TransactionRecord SET ReferenceNumber = '" + this.transaction.getReferenceNum() + "', "
				+ "Amount = '" + amountField.getText() + "', "
				+ "BillingId = '" + this.transaction.getBillingID() + "', "
				+ "TransactionTypeId = '" + String.valueOf(paymentTypeField.getSelectionModel().getSelectedIndex() + 1) + "' "
				+ "WHERE TransactionRecordId = '" + this.transaction.getTransactionRecordID() + "'");
			this.main.getBillingRecordViewController().initialize();
		}
		
		
		this.main.getPopStage().hide();
	}
	
	@FXML
	private void handleCancel() {
		this.main.getPopStage().hide();
	}
	
	public void initialize() {
		paymentTypeField.getItems().add("Cash");
		paymentTypeField.getItems().add("Card");
	}
	
	public void setNewTransactionMode(boolean newTransactionMode) {
		this.newTransactionMode = newTransactionMode;
	}
	
	public void setMain(Main main) {
		this.main = main;
	}
	
}
