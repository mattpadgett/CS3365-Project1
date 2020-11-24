package model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

import util.DBUtil;

public class Appointment {
	private int appointmentId;
	private int doctorIdNumber; // Holds the Id of the doctor
	private int patientIdNumber; // Holds the Id of the patient making an appointment
	private Date appointmentTime; // holds appointment time
	private int statusId; // Holds if appointment was cancelled or not cancelled
	
	//Retreive an existing appointment from the DB
	public Appointment(int appointmentId) {
		ResultSet rs = DBUtil.selectQuery("SELECT * FROM Appointment WHERE AppointmentId = '" + appointmentId + "' LIMIT 1;");
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
		
		try {
			if(rs.next()) {
				this.appointmentId = rs.getInt(1);
				this.doctorIdNumber = rs.getInt(2);
				this.patientIdNumber = rs.getInt(3);
				try {
					this.appointmentTime = new Date(df.parse(rs.getString(4)).getTime());
				} catch (ParseException e) {
					
					e.printStackTrace();
				}
				this.statusId = rs.getInt(5);		
			} else {
				System.out.println("Invalid UserId.");
			}
						
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	} //end of retreiving an existing appointment from the DB
	
	//Create a new appointment to insert to the database.
	public Appointment(int doctorId, int patientId, Date appointmentTime) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
		String formattedDate = df.format(appointmentTime);
		
		PreparedStatement pstmt = DBUtil.insertQuery("INSERT INTO Appointment(DoctorId, PatientId, AppointmentTime, StatusId) VALUES(?,?,?,?)");
		try {
			
			pstmt.setInt(1, doctorId);
			pstmt.setInt(2, patientId);
			pstmt.setString(3, formattedDate);
			pstmt.setInt(4, 1);
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getDoctorIdNumber() {
		return doctorIdNumber;
	}

	public void setDoctorIdNumber(int doctorIdNumber) {
		//Update in database
		this.doctorIdNumber = doctorIdNumber;
		
		PreparedStatement pstmt = DBUtil.insertQuery("UPDATE Appointment SET DoctorId = ? " + "WHERE AppointmentId = ? ;");
		try {
			pstmt.setInt(1, this.doctorIdNumber);
			pstmt.setInt(2, this.appointmentId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getPatientIdNumber() {
		return patientIdNumber;
	}

	public void setPatientIdNumber(int patientIdNumber) {
		//Update in database
		this.patientIdNumber = patientIdNumber;
		PreparedStatement pstmt = DBUtil.insertQuery("UPDATE Appointment SET PatientId = ? " + "WHERE AppointmentId = ? ;");
		try {
			pstmt.setInt(1, this.patientIdNumber);
			pstmt.setInt(2, this.appointmentId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Date getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(Date appointmentTime) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
		String formattedDate = df.format(appointmentTime);
		
		//Update in database
		this.appointmentTime = appointmentTime;
		PreparedStatement pstmt = DBUtil.insertQuery("UPDATE Appointment SET AppointmentTime = ? " + "WHERE AppointmentId = ? ;");
		try {
			pstmt.setString(1, formattedDate);
			pstmt.setInt(2, this.appointmentId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		//Update in database
		this.statusId = statusId;
		
		PreparedStatement pstmt = DBUtil.insertQuery("UPDATE Appointment SET StatusId = ? " + "WHERE AppointmentId = ? ;");
		try {
			pstmt.setInt(1, this.statusId);
			pstmt.setInt(2, this.appointmentId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getAppointmentId() {
		return appointmentId;
	}
	

} // end of appointment class