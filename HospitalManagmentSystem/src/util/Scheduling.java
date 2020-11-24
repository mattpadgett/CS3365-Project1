package util;

import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Scheduling {
  public static ArrayList<java.sql.Date> getAvailability(int doctorId, Date day) {
    
    // Turn date into string
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");   
    String formattedDate = df.format(day);
    
    // Create various date objects with the different times of availability
    Date time1 = new Date(day.getYear(),day.getMonth(),day.getDate(),9,0,0);
    Date time2 = new Date(day.getYear(),day.getMonth(),day.getDate(),9,30,0);
    Date time3 = new Date(day.getYear(),day.getMonth(),day.getDate(),10,0,0);
    Date time4 = new Date(day.getYear(),day.getMonth(),day.getDate(),10,30,0);
    Date time5 = new Date(day.getYear(),day.getMonth(),day.getDate(),11,0,0);
    Date time6 = new Date(day.getYear(),day.getMonth(),day.getDate(),11,30,0);
    Date time7 = new Date(day.getYear(),day.getMonth(),day.getDate(),12,0,0);
    Date time8 = new Date(day.getYear(),day.getMonth(),day.getDate(),12,30,0);
    Date time9 = new Date(day.getYear(),day.getMonth(),day.getDate(),13,0,0);
    Date time10 = new Date(day.getYear(),day.getMonth(),day.getDate(),13,30,0);
    Date time11 = new Date(day.getYear(),day.getMonth(),day.getDate(),14,0,0);
    Date time12 = new Date(day.getYear(),day.getMonth(),day.getDate(),14,30,0);
    Date time13 = new Date(day.getYear(),day.getMonth(),day.getDate(),15,0,0);
    Date time14 = new Date(day.getYear(),day.getMonth(),day.getDate(),15,30,0);
    Date time15 = new Date(day.getYear(),day.getMonth(),day.getDate(),16,0,0);
    Date time16 = new Date(day.getYear(),day.getMonth(),day.getDate(),16,30,0);
    
    // Create array list and add dates to it
    ArrayList<Date> availability = new ArrayList<Date>();
    
    availability.add(time1); //9
    availability.add(time2); //9:30
    availability.add(time3); //10
    availability.add(time4); //10:30
    availability.add(time5); //11
    availability.add(time6); //11:30
    availability.add(time7); //12
    availability.add(time8); //12:30
    availability.add(time9); //1
    availability.add(time10); //1:30
    availability.add(time11); //2
    availability.add(time12); //2:30
    availability.add(time13); //3
    availability.add(time14); //3:30
    availability.add(time15); //4
    availability.add(time16); //4:30
    
    // Query appointment database for requested doctor and day
    ResultSet rs = DBUtil.selectQuery("SELECT * FROM Appointment WHERE DoctorId = '" + doctorId + "' AND AppointmentTime LIKE '%" + formattedDate.substring(0,10) + "%';");
    try {
      while(rs.next()) { // Traverse what was found
        if (rs.getString(4).substring(11,16).equals("09:00")) { // If time matches remove that time from list
          availability.remove(time1);
        }
        if (rs.getString(4).substring(11,16).equals("09:30")) {
          availability.remove(time2);
        }
        if (rs.getString(4).substring(11,16).equals("10:00")) {
          availability.remove(time3);
        }
        if (rs.getString(4).substring(11,16).equals("10:30")) {
          availability.remove(time4);
        }
        if (rs.getString(4).substring(11,16).equals("11:00")) {
          availability.remove(time5);
        }
        if (rs.getString(4).substring(11,16).equals("11:30")) {
          availability.remove(time6);
        }
        if (rs.getString(4).substring(11,16).equals("12:00")) {
          availability.remove(time7);
        }
        if (rs.getString(4).substring(11,16).equals("12:30")) {
          availability.remove(time8);
        }
        if (rs.getString(4).substring(11,16).equals("13:00")) {
          availability.remove(time9);
        }
        if (rs.getString(4).substring(11,16).equals("13:30")) {
          availability.remove(time10);
        }
        if (rs.getString(4).substring(11,16).equals("14:00")) {
          availability.remove(time11);
        }
        if (rs.getString(4).substring(11,16).equals("14:30")) {
          availability.remove(time12);
        }
        if (rs.getString(4).substring(11,16).equals("15:00")) {
          availability.remove(time13);
        }
        if (rs.getString(4).substring(11,16).equals("15:30")) {
          availability.remove(time14);
        }
        if (rs.getString(4).substring(11,16).equals("16:00")) {
          availability.remove(time15);
        }
        if (rs.getString(4).substring(11,16).equals("16:30")) {
          availability.remove(time16);
        }
      }
    } catch(SQLException e) {
      e.printStackTrace();
    }
    
    // Create java.sql.Date list to return back to program
    ArrayList<java.sql.Date> avail = new ArrayList<java.sql.Date>();
    
    for(Date x : availability) {
      //System.out.println(x.toLocaleString());  UNCOMMENT TO SEE ALL TIMES PRINTED
      avail.add(new java.sql.Date(x.getTime()));
    }
    
    return avail;
    
  }
}
