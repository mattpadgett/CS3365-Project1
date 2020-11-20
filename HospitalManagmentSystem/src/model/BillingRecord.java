package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.beans.property.SimpleStringProperty;
import util.DBUtil;

public class BillingRecord {
  
  private int billingRecordID;
  private int patientID;
  private int appointmentID;
  private double amount;
  
  public BillingRecord(int patientID, int appointmentID, double amount) {
    
    this.patientID = patientID;
    this.appointmentID = appointmentID;
    this.amount = amount;

    boolean flag = false;
    
    ResultSet rs = DBUtil.selectQuery("SELECT AppointmentID FROM BillingRecord");
    
    try {
      while(rs.next()) {
//        System.out.println(this.billingRecordID + "\t" + rs.getInt(1) + "\t" + this.appointmentID);
        if(rs.getInt(1) == (this.appointmentID)) {
          flag = true;
        }
      }
      
//      System.out.println("\n" + flag);
      rs.close();
      
    } catch (SQLException e) {
      e.printStackTrace();
    }
    
    if(flag == false) {
      PreparedStatement ps = DBUtil.insertQuery("INSERT INTO BillingRecord(patientID, appointmentID, amount, statusID) VALUES (?,?,?,?);");
      try {
        ps.setInt(1, this.patientID);
        ps.setInt(2, this.appointmentID);
        ps.setDouble(3, this.amount);
        ps.setInt(4,1);
        ps.executeUpdate();
      } catch (SQLException e) {
        e.printStackTrace();
      }
      
      ResultSet r = DBUtil.selectQuery("SELECT MAX(BillingRecordId) FROM BillingRecord");
      
      try {
        this.billingRecordID = r.getInt(1);
        r.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
  
  public BillingRecord(int billingRecordID) {
		ResultSet rs = DBUtil.selectQuery("SELECT * FROM BillingRecord WHERE BillingRecordId = '" + billingRecordID + "' LIMIT 1;");
		
		try {
			if(rs.next()) {
				this.billingRecordID = rs.getInt(1);
				this.patientID = rs.getInt(2);
				this.appointmentID = rs.getInt(3);
				this.amount = rs.getDouble(4);
			} else {
				System.out.println("Invalid BillingRecordId.");
			}
						
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
  
  public int getBillingRecordID() {
//	  System.out.println(this.billingRecordID);
	  return this.billingRecordID;
    
  }
  
  public void setBillingRecordID(int id) {
    this.billingRecordID = id;
  }
  
  public int getPatientID() {
    return this.patientID;
  }
  
  public void setPatientID(int typeID) {
    System.out.println(this.billingRecordID);
    this.patientID = typeID;
    
    PreparedStatement ps = DBUtil.insertQuery("UPDATE BillingRecord SET PatientID = ?" + "WHERE BillingRecordID = ? ;");
    try {
      ps.setInt(1,  this.patientID);
      ps.setInt(2, this.billingRecordID);
      ps.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  
  public int getAppointmentID() {
	  return this.appointmentID;
	  
  }
  
  public int getReferenceNum() {
    return this.appointmentID;
  }
  
  public void setReferenceNum(int num) {
    System.out.println(this.billingRecordID);
    this.appointmentID = num;
    
    PreparedStatement ps = DBUtil.insertQuery("UPDATE BillingRecord SET AppointmentID = ?" + "WHERE BillingRecordID = ? ;");
    try {
      ps.setInt(1,  this.appointmentID);
      ps.setInt(2, this.billingRecordID);
      ps.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  
  public double getAmount() {
    return this.amount;
  }
  
  public void setAmount(Double amount) {
    System.out.println(this.amount);
    System.out.println(this.billingRecordID);
    this.amount = amount;
    
    PreparedStatement ps = DBUtil.insertQuery("UPDATE BillingRecord SET Amount = ?" + "WHERE BillingRecordID = ? ;");
    try {
      ps.setDouble(1,  this.amount);
      ps.setInt(2, this.billingRecordID);
      ps.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  
	public SimpleStringProperty billingRecordIDProperty() {
		return new SimpleStringProperty(Integer.toString(getBillingRecordID()));
	}
	
	public SimpleStringProperty patientIDProperty() {
		return new SimpleStringProperty(Integer.toString(getPatientID()));
	}
	
	public SimpleStringProperty appointmentIDProperty() {
		return new SimpleStringProperty(Integer.toString(getAppointmentID()));
	}
	
	public SimpleStringProperty amountProperty() {
		return new SimpleStringProperty(Double.toString(getAmount()));
	}
	
	public SimpleStringProperty paymentTypeProperty() {
		ResultSet rs = DBUtil.selectQuery("SELECT TransactionTypeId FROM TransactionRecord Where BillingId = '" + this.billingRecordID +"';");
		try {
			switch(rs.getInt(1)) {
				case 1:
					return new SimpleStringProperty("Cash");
				case 2: 
					return new SimpleStringProperty("Card");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
		return null;
		
	}
	
	public SimpleStringProperty referenceNumberProperty() {
		ResultSet rs = DBUtil.selectQuery("SELECT ReferenceNumber FROM TransactionRecord Where BillingId = '" + this.billingRecordID +"';");
		try {
			return new SimpleStringProperty(Integer.toString(rs.getInt(1)));
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
		return null;
		
	}
    
}
