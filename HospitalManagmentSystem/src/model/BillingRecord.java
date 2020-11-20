package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.DBUtil;

public class BillingRecord {
  
  private int billingRecordID;
  private int patientID;
  private int appointmentID;
  private double amount;
  private int statusID;
  
  public BillingRecord(int patientID, int appointmentID, double amount, int statusID) {
    
    this.patientID = patientID;
    this.appointmentID = appointmentID;
    this.amount = amount;
    this.statusID = statusID;

    boolean flag = false;
    
    ResultSet rs = DBUtil.selectQuery("SELECT AppointmentID FROM BillingRecord");
    
    try {
      while(rs.next()) {
        System.out.println(this.billingRecordID + "\t" + rs.getInt(1) + "\t" + this.appointmentID);
        if(rs.getInt(1) == (this.appointmentID)) {
          flag = true;
        }
      }
      
      System.out.println("\n" + flag);
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
        ps.setInt(4, this.statusID);
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
  
  public int getBillingRecordID() {
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
  
  public void setAmount(float amount) {
    System.out.println(this.amount);
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
    
  public int getStatus() {
    return this.statusID;
  }
    
  public void setStatus(int status) {
    System.out.println(this.statusID);
    this.statusID = status;
    
    PreparedStatement ps = DBUtil.insertQuery("UPDATE BillingRecord SET StatusId = ?" + "WHERE BillingRecordID = ? ;");
    try {
      ps.setInt(1,  this.statusID);
      ps.setInt(2, this.billingRecordID);
      ps.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
 }
}
