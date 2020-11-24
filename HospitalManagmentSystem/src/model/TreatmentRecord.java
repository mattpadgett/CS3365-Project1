package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import util.DBUtil;

public class TreatmentRecord {

	private int treatmentRecordID;
	private int patientId;
	private String date; // Had to make this a string for now
	private float weight; // Might want to convert these floats to doubles
	private float height;
	private String bloodPressure;
	private String visitReason;
	private String treatmentNote;
	private int prescriptionId;
	
	public TreatmentRecord(PatientChart patient, String date, float weight, float height, String bloodPressure, String visitReason, String treatmentNote, int prescriptionId) {
		
		this.patientId = patient.getPatientChartID();
		this.date = date;
		this.weight = weight;
		this.height = height;
		this.bloodPressure = bloodPressure;
		this.visitReason = visitReason;
		this.treatmentNote = treatmentNote;
		this.prescriptionId = prescriptionId;
		
		boolean skipFlag = false;
		
		ResultSet rs = DBUtil.selectQuery("SELECT Date FROM TreatmentRecord;");
		try {
			while(rs.next()) {
				if(rs.getString(1).equals(this.date)) {
					
					skipFlag = true;
				}
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(skipFlag == false) {
			
			PreparedStatement pstmt = DBUtil.insertQuery("INSERT INTO TreatmentRecord(patientId,date,weight,height,bloodPressure,visitReason,treatmentNote,prescriptionId) VALUES (?,?,?,?,?,?,?,?);");
			try {
				
				pstmt.setInt(1, this.patientId);
				pstmt.setString(2, this.date);
				pstmt.setFloat(3, this.weight);
				pstmt.setFloat(4, this.height);
				pstmt.setString(5, this.bloodPressure);
				pstmt.setString(6, this.visitReason);
				pstmt.setString(7, this.treatmentNote);
				pstmt.setInt(8, this.prescriptionId);
				pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ResultSet r = DBUtil.selectQuery("SELECT MAX(TreatmentRecordId) FROM TreatmentRecord;");
			
			try {
				
				this.treatmentRecordID = r.getInt(1);
				r.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public TreatmentRecord(int treatmentRecordID) {
		ResultSet rs = DBUtil.selectQuery("SELECT * FROM TreatmentRecord WHERE TreatmentRecordId = '" + treatmentRecordID + "' LIMIT 1;");
		
		try {
			if(rs.next()) {
				this.treatmentRecordID = rs.getInt(1);
				this.patientId = rs.getInt(2);
				this.date = rs.getString(3);
				this.weight = rs.getFloat(4);
				this.height = rs.getFloat(5);
				this.bloodPressure = rs.getString(6);
				this.visitReason = rs.getString(7);
				this.treatmentNote = rs.getString(8);
				this.prescriptionId = rs.getInt(9);	
			} else {
				System.out.println("Invalid TreatmentRecordId.");
			}
						
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	public int getTreatmentRecordID() {
		
		return this.treatmentRecordID;
	}
	
	public void setTreatmentRecordID(int newTreatmentRecordID) {
		
		this.treatmentRecordID = newTreatmentRecordID;
	}
	
	public String getDate() {
		
		return this.date;
	}
	
	public void setDate(String newDate) {
		
		this.date = newDate;
		
		PreparedStatement pstmt = DBUtil.insertQuery("UPDATE TreatmentRecord SET Date = ? " + "WHERE TreatmentRecordId = ? ;");
		try {
			
			pstmt.setString(1, this.date);
			pstmt.setInt(2, this.treatmentRecordID);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public float getWeight() {
		
		return this.weight;
	}
	
	public void setWeight(float newWeight) {
		
		this.weight = newWeight;
		
		PreparedStatement pstmt = DBUtil.insertQuery("UPDATE TreatmentRecord SET Weight = ? " + "WHERE TreatmentRecordId = ? ;");
		try {
			
			pstmt.setFloat(1, this.weight);
			pstmt.setInt(2, this.treatmentRecordID);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public float getHeight() {
		
		return this.height;
	}
	
	public void setHeight(float newHeight) {
		
		this.height = newHeight;
		
		PreparedStatement pstmt = DBUtil.insertQuery("UPDATE TreatmentRecord SET Height = ? " + "WHERE TreatmentRecordId = ? ;");
		try {
			
			pstmt.setFloat(1, this.height);
			pstmt.setInt(2, this.treatmentRecordID);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public String getBloodPressure() {
		
		return this.bloodPressure;
	}
	
	public void setBloodPressure(String newBloodPressure) {
		
		this.bloodPressure = newBloodPressure;
		
		PreparedStatement pstmt = DBUtil.insertQuery("UPDATE TreatmentRecord SET BloodPressure = ? " + "WHERE TreatmentRecordId = ? ;");
		try {
			
			pstmt.setString(1, this.bloodPressure);
			pstmt.setInt(2, this.treatmentRecordID);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String getVisitReason() {
		
		return this.visitReason;
	}
	
	public void setVisitReason(String newVisitReason) {
		
		this.visitReason = newVisitReason;
		
		PreparedStatement pstmt = DBUtil.insertQuery("UPDATE TreatmentRecord SET VisitReason = ? " + "WHERE TreatmentRecordId = ? ;");
		try {
			
			pstmt.setString(1, this.visitReason);
			pstmt.setInt(2, this.treatmentRecordID);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String getTreatmentNote() {
		
		return this.treatmentNote;
	}
	
	public void setTreatmentNote(String newTreatmentNote) {
		
		this.treatmentNote = newTreatmentNote;
		
		PreparedStatement pstmt = DBUtil.insertQuery("UPDATE TreatmentRecord SET TreatmentNote = ? " + "WHERE TreatmentRecordId = ? ;");
		try {
			
			pstmt.setString(1, this.treatmentNote);
			pstmt.setInt(2, this.treatmentRecordID);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int getPrescriptionNote() {
		
		return this.prescriptionId;
	}
	
	public void setPrescriptionId(int newPrescriptionId) {
		
		this.prescriptionId = newPrescriptionId;
		
		PreparedStatement pstmt = DBUtil.insertQuery("UPDATE TreatmentRecord SET PrescriptionId = ? " + "WHERE TreatmentRecordId = ? ;");
		try {
			
			pstmt.setInt(1, this.prescriptionId);
			pstmt.setInt(2, this.treatmentRecordID);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public SimpleStringProperty dateProperty() {
		return new SimpleStringProperty(getDate());
	}
	
	public SimpleStringProperty weightProperty() {
		return new SimpleStringProperty(Float.toString(getWeight()));
	}
	
	public SimpleStringProperty heightProperty() {
		return new SimpleStringProperty(Float.toString(getHeight()));
	}
	
	public SimpleStringProperty bloodPressureProperty() {
		return new SimpleStringProperty(getBloodPressure());
	}
	
	public SimpleStringProperty visitReasonProperty() {
		return new SimpleStringProperty(getVisitReason());
	}
	
	public SimpleStringProperty treatmentNoteProperty() {
		return new SimpleStringProperty(getTreatmentNote());
	}
	
	public SimpleStringProperty prescriptionProperty() {
		return new SimpleStringProperty(Integer.toString(getPrescriptionNote()));
	}
}
