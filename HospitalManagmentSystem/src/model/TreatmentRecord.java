package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.DBUtil;

public class TreatmentRecord {

	private int treatmentRecordID;
	private String date; // Had to make this a string for now
	private float weight; // Might want to convert these floats to doubles
	private float height;
	private String bloodPressure;
	private String visitReason;
	private String treatmentNote;
	private int prescriptionId;
	
	public TreatmentRecord(String date, float weight, float height, String bloodPressure, String visitReason, String treatmentNote, int prescriptionId) {
		
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
			
			PreparedStatement pstmt = DBUtil.insertQuery("INSERT INTO TreatmentRecord(date,weight,height,bloodPressure,visitReason,treatmentNote,prescriptionId) VALUES (?,?,?,?,?,?,?);");
			try {
				
				pstmt.setString(1, this.date);
				pstmt.setFloat(2, this.weight);
				pstmt.setFloat(3, this.height);
				pstmt.setString(4, this.bloodPressure);
				pstmt.setString(5, this.visitReason);
				pstmt.setString(6, this.treatmentNote);
				pstmt.setInt(7, this.prescriptionId);
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
}
