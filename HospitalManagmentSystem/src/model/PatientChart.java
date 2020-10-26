package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.DBUtil;

public class PatientChart {
	
	private int patientChartID;
	private String firstName;
	private String lastName;
	private String middleName;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String zipCode;
	private String socialSecurityNumber;
	private String insuranceProvider;
	
	public PatientChart(String firstName, String lastName, String middleName, String address1, String address2, String city, String state, String zipCode, String socialSecurityNumber, String insuranceProvider) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.socialSecurityNumber = socialSecurityNumber;
		this.insuranceProvider = insuranceProvider;
		
		boolean skipFlag = false;
		
		ResultSet rs = DBUtil.selectQuery("SELECT SocialSecurityNumber FROM PatientChart;");
		try {
			while(rs.next()) {
				System.out.println(this.firstName + "\t" + rs.getString(1) + "\t" + this.socialSecurityNumber);
				if(rs.getString(1).equals(this.socialSecurityNumber)) {
					
					skipFlag = true;
				}
			}
			System.out.println("\n" + skipFlag);
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(skipFlag == false) {
			
			PreparedStatement pstmt = DBUtil.insertQuery("INSERT INTO PatientChart(firstName,lastName,middleName,address1,address2,city,state,zipCode,socialSecurityNumber,insuranceProvider) VALUES (?,?,?,?,?,?,?,?,?,?);");
			try {
				
				pstmt.setString(1, this.firstName);
				pstmt.setString(2, this.lastName);
				pstmt.setString(3, this.middleName);
				pstmt.setString(4, this.address1);
				pstmt.setString(5, this.address2);
				pstmt.setString(6, this.city);
				pstmt.setString(7, this.state);
				pstmt.setString(8, this.zipCode);
				pstmt.setString(9, this.socialSecurityNumber);
				pstmt.setString(10, this.insuranceProvider);
				pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			ResultSet r = DBUtil.selectQuery("SELECT MAX(PatientChartId) FROM PatientChart;");
			
			try {
				
				this.patientChartID = r.getInt(1);
				r.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public int getPatientChartID() {
		
		return this.patientChartID;
	}
	
	public void setPatientChartID(int newPatientChartID) {
		
		this.patientChartID = newPatientChartID;
	}
	
	public String getFirstName() {
		
		return this.firstName;
	}
	
	public void setFirstName(String newFirstName) {
		System.out.println(this.patientChartID);
		this.firstName = newFirstName;
		
		PreparedStatement pstmt = DBUtil.insertQuery("UPDATE PatientChart SET FirstName = ? " + "WHERE PatientChartId = ? ;");
		try {
			
			pstmt.setString(1, this.firstName);
			pstmt.setInt(2, this.patientChartID);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String getLastName() {
		
		return this.lastName;
	}
	
	public void setLastName(String newLastName) {
		
		this.lastName = newLastName;
		
		PreparedStatement pstmt = DBUtil.insertQuery("UPDATE PatientChart SET LastName = ? " + "WHERE PatientChartId = ? ;");
		try {
			
			pstmt.setString(1, this.lastName);
			pstmt.setInt(2, this.patientChartID);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String getMiddleName() {
		
		return this.middleName;
	}
	
	public void setMiddleName(String newMiddleName) {
		
		this.middleName = newMiddleName;
		
		PreparedStatement pstmt = DBUtil.insertQuery("UPDATE PatientChart SET MiddleName = ? " + "WHERE PatientChartId = ? ;");
		try {
			
			pstmt.setString(1, this.middleName);
			pstmt.setInt(2, this.patientChartID);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String getAddress1() {
		
		return this.address1;
	}
	
	public void setAddress1(String newAddress1) {
		
		this.address1 = newAddress1;
		
		PreparedStatement pstmt = DBUtil.insertQuery("UPDATE PatientChart SET Address1 = ? " + "WHERE PatientChartId = ? ;");
		try {
			
			pstmt.setString(1, this.address1);
			pstmt.setInt(2, this.patientChartID);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String getAddress2() {
		
		return this.address2;
	}
	
	public void setAddress2(String newAddress2) {
		
		this.address2 = newAddress2;
		
		PreparedStatement pstmt = DBUtil.insertQuery("UPDATE PatientChart SET Address2 = ? " + "WHERE PatientChartId = ? ;");
		try {
			
			pstmt.setString(1, this.address2);
			pstmt.setInt(2, this.patientChartID);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String getCity() {
		
		return this.city;
	}
	
	public void setCity(String newCity) {
		
		this.city = newCity;
		
		PreparedStatement pstmt = DBUtil.insertQuery("UPDATE PatientChart SET City = ? " + "WHERE PatientChartId = ? ;");
		try {
			
			pstmt.setString(1, this.city);
			pstmt.setInt(2, this.patientChartID);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String getState() {
		
		return this.state;
	}
	
	public void setState(String newState) {
		
		this.state = newState;
		
		PreparedStatement pstmt = DBUtil.insertQuery("UPDATE PatientChart SET State = ? " + "WHERE PatientChartId = ? ;");
		try {
			
			pstmt.setString(1, this.state);
			pstmt.setInt(2, this.patientChartID);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String getZipCode() {
		
		return this.zipCode;
	}
	
	public void setZipCode(String newZipCode) {
		
		this.zipCode = newZipCode;
		
		PreparedStatement pstmt = DBUtil.insertQuery("UPDATE PatientChart SET ZipCode = ? " + "WHERE PatientChartId = ? ;");
		try {
			
			pstmt.setString(1, this.zipCode);
			pstmt.setInt(2, this.patientChartID);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String getSocialSecurityNumber() {
		
		return this.socialSecurityNumber;
	}
	
	public void setSocialSecurityNumber(String newSocialSecurityNumber) {
		
		this.socialSecurityNumber = newSocialSecurityNumber;
		
		PreparedStatement pstmt = DBUtil.insertQuery("UPDATE PatientChart SET SocialSecurityNumber = ? " + "WHERE PatientChartId = ? ;");
		try {
			
			pstmt.setString(1, this.socialSecurityNumber);
			pstmt.setInt(2, this.patientChartID);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String getInsuranceProvider() {
		
		return this.insuranceProvider;
	}
	
	public void setInsuranceProvider(String newInsuranceProvider) {
		
		this.insuranceProvider = newInsuranceProvider;
		
		PreparedStatement pstmt = DBUtil.insertQuery("UPDATE PatientChart SET InsuranceProvider = ? " + "WHERE PatientChartId = ? ;");
		try {
			
			pstmt.setString(1, this.insuranceProvider);
			pstmt.setInt(2, this.patientChartID);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
