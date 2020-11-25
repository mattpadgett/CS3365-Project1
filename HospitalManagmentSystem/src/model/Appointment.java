package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import util.DBUtil;

public class Appointment {	
	private int appointmentId;
	private int doctorIdNumber;
	private int patientIdNumber;
	private LocalDateTime appointmentTime;
	private int statusId;	
	
	public Appointment(int appointmentId) {
		ResultSet rs = DBUtil.selectQuery("SELECT * FROM Appointment WHERE AppointmentId = '" + appointmentId + "' LIMIT 1;");		
		
		try {
			if(rs.next()) {
				this.appointmentId = rs.getInt(1);
				this.doctorIdNumber = rs.getInt(2);
				this.patientIdNumber = rs.getInt(3);
				this.appointmentTime = LocalDateTime.parse(rs.getString(4), DateTimeFormatter.ISO_DATE_TIME);
				this.statusId = rs.getInt(5);		
			} else {
				System.out.println("Invalid AppointmentId.");
			}
						
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	public Appointment(int doctorId, int patientId, LocalDateTime appointmentTime) {
		PreparedStatement pstmt = DBUtil.insertQuery("INSERT INTO Appointment(DoctorId, PatientId, AppointmentTime, StatusId) VALUES(?,?,?,?)");
		try {
			
			pstmt.setInt(1, doctorId);
			pstmt.setInt(2, patientId);
			pstmt.setString(3, appointmentTime.format(DateTimeFormatter.ISO_DATE_TIME));
			pstmt.setInt(4, 1);
			pstmt.executeUpdate();
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getDoctorIdNumber() {
		return doctorIdNumber;
	}

	public void setDoctorIdNumber(int doctorIdNumber) {
		this.doctorIdNumber = doctorIdNumber;
		
		PreparedStatement pstmt = DBUtil.insertQuery("UPDATE Appointment SET DoctorId = ? " + "WHERE AppointmentId = ? ;");
		try {
			pstmt.setInt(1, this.doctorIdNumber);
			pstmt.setInt(2, this.appointmentId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getPatientIdNumber() {
		return patientIdNumber;
	}

	public void setPatientIdNumber(int patientIdNumber) {
		this.patientIdNumber = patientIdNumber;
		
		PreparedStatement pstmt = DBUtil.insertQuery("UPDATE Appointment SET PatientId = ? " + "WHERE AppointmentId = ? ;");
		try {
			pstmt.setInt(1, this.patientIdNumber);
			pstmt.setInt(2, this.appointmentId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public LocalDateTime getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(LocalDateTime appointmentTime) {
		this.appointmentTime = appointmentTime;
		
		PreparedStatement pstmt = DBUtil.insertQuery("UPDATE Appointment SET AppointmentTime = ? " + "WHERE AppointmentId = ? ;");
		try {
			pstmt.setString(1, appointmentTime.format(DateTimeFormatter.ISO_DATE_TIME));
			pstmt.setInt(2, this.appointmentId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
		
		PreparedStatement pstmt = DBUtil.insertQuery("UPDATE Appointment SET StatusId = ? " + "WHERE AppointmentId = ? ;");
		try {
			pstmt.setInt(1, this.statusId);
			pstmt.setInt(2, this.appointmentId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getAppointmentId() {
		return appointmentId;
	}
	
}