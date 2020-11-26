package util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Scheduling {
	public static ArrayList<LocalDateTime> getAvailability(int doctorId, LocalDate day) {
		String stringTimes[] = {
			"09:00:00",
			"09:30:00",
			"10:00:00",
			"10:30:00",
			"11:00:00",
			"11:30:00",
			"12:00:00",
			"12:30:00",
			"13:00:00",
			"13:30:00",
			"14:00:00",
			"14:30:00",
			"15:00:00",
			"15:30:00",
			"16:00:00",
			"16:30:00",
		};
		
		ArrayList<LocalDateTime> times = new ArrayList<LocalDateTime>();
		
		times.add(LocalDateTime.of(day, LocalTime.of(9, 0, 0)));
    	times.add(LocalDateTime.of(day, LocalTime.of(9, 30, 0)));
    	times.add(LocalDateTime.of(day, LocalTime.of(10, 0, 0)));
    	times.add(LocalDateTime.of(day, LocalTime.of(10, 30, 0)));
    	times.add(LocalDateTime.of(day, LocalTime.of(11, 0, 0)));
    	times.add(LocalDateTime.of(day, LocalTime.of(11, 30, 0)));
    	times.add(LocalDateTime.of(day, LocalTime.of(12, 0, 0)));
    	times.add(LocalDateTime.of(day, LocalTime.of(12, 30, 0)));
    	times.add(LocalDateTime.of(day, LocalTime.of(13, 0, 0)));
    	times.add(LocalDateTime.of(day, LocalTime.of(13, 30, 0)));
    	times.add(LocalDateTime.of(day, LocalTime.of(14, 0, 0)));
    	times.add(LocalDateTime.of(day, LocalTime.of(14, 30, 0)));
    	times.add(LocalDateTime.of(day, LocalTime.of(15, 0, 0)));
    	times.add(LocalDateTime.of(day, LocalTime.of(15, 30, 0)));
    	times.add(LocalDateTime.of(day, LocalTime.of(16, 0, 0)));
    	times.add(LocalDateTime.of(day, LocalTime.of(16, 30, 0)));    	

		ResultSet rs = DBUtil.selectQuery("SELECT * FROM Appointment WHERE DoctorId = '" + doctorId + "' AND AppointmentTime LIKE '%" + day.format(DateTimeFormatter.ISO_DATE) + "%' AND StatusId <> 2;");
		
		try {
			while(rs.next()) {
				LocalDateTime storedDateTime = LocalDateTime.parse(rs.getString(4), DateTimeFormatter.ISO_DATE_TIME);
				
				for(int i = 0; i < stringTimes.length; i++) {
					if(stringTimes[i].equalsIgnoreCase(storedDateTime.format(DateTimeFormatter.ISO_TIME))) {
						times.remove(i);
					}
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return times;
	}
}