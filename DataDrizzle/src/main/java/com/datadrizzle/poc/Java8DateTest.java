package com.datadrizzle.poc;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Java8DateTest {
	
	public static void main(String[] args) throws ParseException {
	    final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		/*LocalDate localDate = LocalDate.now();
		LocalDateTime localDateTime = LocalDateTime.now();
		Date date = new Date();
		Timestamp ts = new Timestamp(date.getTime());
		
		String toTable = "actors_main";
		String fromTable = "actors";
		String timeStamp = "from_unixtime(unix_timestamp('"+ ts.toString() +"','yyyy-MM-dd HH:mm:ss'))";
		String query = "INSERT INTO " + toTable + " SELECT *,"+ timeStamp +" as rec_insert_time FROM "+fromTable;
		System.out.println(query);*/
		/*Date date = new Date();
		long time = date.getTime();
		Timestamp ts = new Timestamp(time);
		System.out.println("Current Time Stamp: " + sdf.format(ts));*/
	    //2013-05-02 08:15:59
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	    Date parsedDate = dateFormat.parse("2020-02-03 14:15:59");
	    System.out.println(parsedDate);
	    
	    Calendar calendar = Calendar.getInstance();
		calendar.setTime(parsedDate);
		
		calendar.add(Calendar.DATE, 5);
		
		Date currentDate = new Date();
		Calendar calendarCurrent = Calendar.getInstance();
		calendarCurrent.setTime(currentDate);
		
		calendarCurrent.add(Calendar.DATE, -5);
		
		Timestamp timestamp = new Timestamp(calendarCurrent.getTimeInMillis());
		System.out.println(timestamp);
		
		/*if(calendarCurrent.compareTo(calendar) == 1 ) {
			System.out.println("record deleted");
		}*/
		
//		System.out.println(calendarCurrent.get(Calendar.DATE));
		
		
	    
	}
}
