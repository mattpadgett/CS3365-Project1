package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.DBUtil;

public class Transaction {
  
  private int transactionRecordID;
  private int transactionTypeID;
  private int referenceNumber;
  private double amount;
  private int statusID;
  
  public Transaction(int transactionTypeID, int referenceNum, double amount, int statusID) {
    
    this.transactionTypeID = transactionTypeID;
    this.referenceNumber = referenceNum;
    this.amount = amount;
    this.statusID = statusID;

    System.out.println("here");
    boolean flag = false;
    
    ResultSet rs = DBUtil.selectQuery("SELECT ReferenceNumber FROM TransactionRecord");
    
    try {
      while(rs.next()) {
        System.out.println(this.transactionRecordID + "\t" + rs.getInt(1) + "\t" + this.referenceNumber);
        if(rs.getInt(1) == (this.referenceNumber)) {
          flag = true;
        }
      }
      
      System.out.println("\n" + flag);
      rs.close();
      
    } catch (SQLException e) {
      e.printStackTrace();
    }
    
    if(flag == false) {
      PreparedStatement ps = DBUtil.insertQuery("INSERT INTO TransactionRecord(transactionTypeID, referenceNumber, amount, statusID) VALUES (?,?,?,?);");
      try {
        System.out.println("here");
        ps.setInt(1, this.transactionTypeID);
        ps.setInt(2, this.referenceNumber);
        ps.setDouble(3, this.amount);
        ps.setInt(4, this.statusID);
        ps.executeUpdate();
      } catch (SQLException e) {
        e.printStackTrace();
      }
      
      ResultSet r = DBUtil.selectQuery("SELECT MAX(TransactionRecordId) FROM TransactionRecord");
      
      try {
        this.transactionRecordID = r.getInt(1);
        r.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
  
  public int getTransactionRecordID() {
    return this.transactionRecordID;
  }
  
  public void setTransactionRecordID(int id) {
    this.transactionRecordID = id;
  }
  
  public int getTransactionTypeID() {
    return this.transactionTypeID;
  }
  
  public void setTransactionTypeID(int typeID) {
    System.out.println(this.transactionRecordID);
    this.transactionTypeID = typeID;
    
    PreparedStatement ps = DBUtil.insertQuery("UPDATE TransactionRecord SET TransactionTypeID = ?" + "WHERE TransactionRecordID = ? ;");
    try {
      ps.setInt(1,  this.transactionTypeID);
      ps.setInt(2, this.transactionRecordID);
      ps.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  
  public int getReferenceNum() {
    return this.referenceNumber;
  }
  
  public void setReferenceNum(int num) {
    System.out.println(this.transactionRecordID);
    this.referenceNumber = num;
    
    PreparedStatement ps = DBUtil.insertQuery("UPDATE TransactionRecord SET ReferenceNumber = ?" + "WHERE TransactionRecordID = ? ;");
    try {
      ps.setInt(1,  this.referenceNumber);
      ps.setInt(2, this.transactionRecordID);
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
    
    PreparedStatement ps = DBUtil.insertQuery("UPDATE TransactionRecord SET Amount = ?" + "WHERE TransactionRecordID = ? ;");
    try {
      ps.setDouble(1,  this.amount);
      ps.setInt(2, this.transactionRecordID);
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
    
    PreparedStatement ps = DBUtil.insertQuery("UPDATE TransactionRecord SET StatusId = ?" + "WHERE TransactionRecordID = ? ;");
    try {
      ps.setInt(1,  this.statusID);
      ps.setInt(2, this.transactionRecordID);
      ps.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
 }
}