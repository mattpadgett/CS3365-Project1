package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ReportData {
	private String doctorName;
	private int patients;
	
	public ReportData(String doctorName, int patients) {
		this.doctorName = doctorName;
		this.patients = patients;
	}
	
	public SimpleStringProperty doctorProperty() {
		return new SimpleStringProperty(this.doctorName);
	}
	
	public SimpleStringProperty patientsProperty() {
		return new SimpleStringProperty(String.valueOf(this.patients));
	}
}
