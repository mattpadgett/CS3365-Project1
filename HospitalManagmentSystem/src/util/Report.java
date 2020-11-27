package util;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import model.User;


/*
	The system generates a daily summary report at 9 pm every business day. The report shows
	information about doctors’ performance for a day. The report contains each doctor name, the
	number of patients served by a doctor, and the amount earned. The daily reports are stored in the
	system so that CEO can view them. 
 */

public class Report {
		private int reportId;
		private LocalDate reportDate;
		private ArrayList<User> doctors = new ArrayList<User>();
		private ArrayList<Integer> patientCounts = new ArrayList<Integer>();
		private String paymentAmounts = "";
		
		// Retreive an existing report from the DB
		public Report(int reportId) {
			ResultSet rs = DBUtil.selectQuery("SELECT * FROM Report WHERE ReportId = '" + reportId + "' LIMIT 1;");
			
			try {
				if(rs.next()) {
					this.reportId = reportId;
					this.reportDate = LocalDate.parse(rs.getString(2), DateTimeFormatter.ISO_DATE);
					
					String[] doctorsString = rs.getString(3).split(";;;");
					String[] patientsString = rs.getString(4).split(";;;");
					this.paymentAmounts = rs.getString(5);
					
					for(int i = 0; i < doctorsString.length; i++) {
						this.doctors.add(new User(Integer.valueOf(doctorsString[i])));
						this.patientCounts.add(Integer.valueOf(patientsString[i]));
					}
				} else {
					System.out.println("Invalid ReportId.");
				}
							
			} catch (SQLException e) {
				e.printStackTrace();
				System.exit(-1);
			}
		}
		
		// Create a new report to insert into the DB
		public static Report generateReport(LocalDate reportDate) {
			ArrayList<User> doctors = new ArrayList<User>();
			ArrayList<Integer> patientCounts = new ArrayList<Integer>();
			String paymentAmounts = "";
			
			// Setup Doctors
			ResultSet rs = DBUtil.selectQuery("SELECT UserId FROM User WHERE UserTypeId = 3 AND StatusId = 1;");			
			
			try {
				while(rs.next()) {
					doctors.add(new User(rs.getInt(1)));
				}		
			} catch (SQLException e) {
				e.printStackTrace();
				System.exit(-1);
			}
			
			for(int i = 0; i < doctors.size(); i++) {
				patientCounts.add(0);				
			}
			
			// Get PatientCounts
			rs = DBUtil.selectQuery("SELECT DoctorId, COUNT(*) AS 'PatientCount' FROM Appointment WHERE AppointmentTime LIKE '" + reportDate.format(DateTimeFormatter.ISO_DATE) + "%' AND StatusId = 1 GROUP BY DoctorId;");			
			
			try {
				while(rs.next()) {
					int docId = rs.getInt(1);
					int patCount = rs.getInt(2);
					
					for(int i = 0; i < doctors.size(); i++) {
						if(doctors.get(i).getUserID() == docId) {
							patientCounts.set(i, patCount);
						}
					}
				}		
			} catch (SQLException e) {
				e.printStackTrace();
				System.exit(-1);
			}
			
			//Get Total Money
			rs = DBUtil.selectQuery("SELECT SUM(bill.Amount) FROM Appointment appt INNER JOIN TransactionRecord trans ON appt.PatientId = bill.PatientId INNER JOIN BillingRecord bill on trans.BillingId = bill.BillingRecordId WHERE appt.AppointmentTime LIKE '" + reportDate.format(DateTimeFormatter.ISO_DATE) + "%';");
			try {
				if(rs.next()) {
					paymentAmounts = rs.getString(1);
				} else {
					paymentAmounts = "0";
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.exit(-1);
			}
			
			String doctorsString = "";
			String patientCountsString = "";			
			
			for(int i = 0; i < doctors.size(); i++) {
				doctorsString += doctors.get(i).getUserID() + ";;;";
				patientCountsString += patientCounts.get(i) + ";;;";				
			}		
			
			PreparedStatement pstmt = DBUtil.insertQuery("INSERT INTO Report(Date, Doctors, PatientCounts, AmountEarned) VALUES(?, ?, ?, ?)");
			
			try {
				pstmt.setString(1, reportDate.format(DateTimeFormatter.ISO_DATE));	
				pstmt.setString(2, doctorsString);
				pstmt.setString(3, patientCountsString);
				pstmt.setString(4, paymentAmounts);
				
				pstmt.executeUpdate();				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			//Get the report we just made
			int repId = 0;
			rs = DBUtil.selectQuery("SELECT last_insert_rowid();");			
			
			try {
				while(rs.next()) {
					repId = rs.getInt(1);
				}		
			} catch (SQLException e) {
				e.printStackTrace();
				System.exit(-1);
			}
			
			return new Report(repId);
		}

		public int getReportId() {
			return reportId;
		}

		public LocalDate getReportDate() {
			return reportDate;
		}

		public ArrayList<User> getDoctors() {
			return doctors;
		}

		public ArrayList<Integer> getPatientCounts() {
			return patientCounts;
		}

		public String getPaymentAmounts() {
			return paymentAmounts;
		}
} // end of Report class