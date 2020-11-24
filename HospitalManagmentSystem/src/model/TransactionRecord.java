package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.DBUtil;

public class TransactionRecord {
  
  private int transactionRecordID;
  private int billingID;
  private int transactionTypeID;
  private int referenceNumber;
  private double amount;
  
  public TransactionRecord(int transactionTypeID, int billingID, int referenceNum, double amount) {
    
    this.transactionTypeID = transactionTypeID;
    this.billingID = billingID;
    this.referenceNumber = referenceNum;
    this.amount = amount;

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
      PreparedStatement ps = DBUtil.insertQuery("INSERT INTO TransactionRecord(billingId, transactionTypeID, referenceNumber, amount, statusID) VALUES (?,?,?,?,?);");
      try {
	    ps.setInt(1, this.billingID);
        ps.setInt(2, this.transactionTypeID);
        ps.setInt(3, this.referenceNumber);
        ps.setDouble(4, this.amount);
        ps.setInt(5,1);
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
  
  public TransactionRecord(int transactionRecordId) {
		ResultSet rs = DBUtil.selectQuery("SELECT * FROM TransactionRecord WHERE TransactionRecordId = '" + transactionRecordId + "' LIMIT 1;");
		
		try {
			if(rs.next()) {
				this.transactionRecordID = rs.getInt(1);
				this.billingID = rs.getInt(2);
				this.transactionTypeID = rs.getInt(3);
				this.referenceNumber = rs.getInt(4);
				this.amount = rs.getDouble(5);	
			} else {
				System.out.println("Invalid TransactionRecordId.");
			}
						
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
  
  public int getTransactionRecordID() {
    return this.transactionRecordID;
  }
  
  public void setTransactionRecordID(int id) {
    this.transactionRecordID = id;
  }
  
  public int getBillingID() {
    return this.billingID;
  }
	  
  public void setBillingID(int id) {
    this.billingID = id;
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
    
}