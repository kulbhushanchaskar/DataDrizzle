package com.datadrizzle.scheduler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.StringJoiner;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.datadrizzle.share.Notification;

public class RetensionJob implements Job {

	private Logger log = Logger.getLogger(RetensionJob.class);
	private static String driverName = "org.apache.hive.jdbc.HiveDriver";
	final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public void execute(JobExecutionContext jExeCtx) throws JobExecutionException {
		JobDataMap dataMap = jExeCtx.getJobDetail().getJobDataMap();

//		executeSqoopJOB(dataMap);
		applyRetension(dataMap);
//		deleteDataFromJobDB();
	}

	private void applyRetension(JobDataMap dataMap) {
		Notification notification = new Notification();
		String databaseName = dataMap.getString("databaseName");
		String fromTable = dataMap.getString("tableName");
		String toTable = dataMap.getString("tableName") + "_main";
		int retensionDate = Integer.parseInt(dataMap.getString("retensionDate"));
		Connection conTemp = null;
		Statement stmt = null;
		
		System.out.println("applying retension to table "+toTable);
		
		Date currentDate = new Date();
		Calendar calendarCurrent = Calendar.getInstance();
		calendarCurrent.setTime(currentDate);
		
		calendarCurrent.add(Calendar.DATE, -retensionDate);
		
		Timestamp timestamp = new Timestamp(calendarCurrent.getTimeInMillis());
		System.out.println(timestamp);
		try {
		// replace "hduser" here with the name of the user the queries should run as
//			    Connection con = DriverManager.getConnection("jdbc:hive2://localhost:10000/default", "", "");
		conTemp = (Connection) dataMap.get("dbConnection");//DriverManager.getConnection("jdbc:hive2://localhost:10000/"+databaseName, "", "");
		System.out.println("connection made");
		stmt = conTemp.createStatement();
		System.out.println("executing query");
		
		String query = "DELETE FROM "+ toTable + " WHERE rec_insert_time < '"+ timestamp +"'";
		System.out.println(query);
		int rowsDeleted = stmt.executeUpdate(query);
		System.out.println("number of rows added "+rowsDeleted);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(stmt != null) {
				try {
//					conTemp.commit();
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}

	private void executeSqoopJOB(JobDataMap dataMap) {
		Notification notification = new Notification();
		String connectionName = dataMap.getString("connectionName");
		String databaseName = dataMap.getString("databaseName");
		String table = dataMap.getString("tableName");
		String jobName = new StringBuilder().append(connectionName).append(".").append(databaseName)
				.append(".").append(table).toString();

		String[] commands = { "sqoop", "job", "--exec", jobName };
		StringJoiner joiner = new StringJoiner(" ");
		for (int i = 0; i < commands.length; i++) {
			joiner.add(commands[i]);
		}

		String str = joiner.toString();
		System.out.println(str);

		Process proc = null;
		Runtime rt = Runtime.getRuntime();
		try {
			proc = rt.exec(str);
		} catch (IOException e) {
			e.printStackTrace();
			notification.addErrorMessage("Technical difficulties while running navtive command");
//				return notification;
		}

		BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));

		BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));

		try {
			// Read the output from the command
			System.out.println("Here is the standard output of the command:\n");
			String s = null;
			while ((s = stdInput.readLine()) != null) {
				System.out.println(s);
			}
		} catch (IOException e) {
			notification.addErrorMessage("Cannot read native command output");
//				return notification;
		}

		try {
			// Read any errors from the attempted command
			System.out.println("Here is the standard error of the command (if any):\n");
			String s = null;
			while ((s = stdError.readLine()) != null) {
				System.out.println(s);
			}
		} catch (IOException e) {
			notification.addErrorMessage("Cannot read native command output");
//				return notification;
		}
	}

	private void deleteDataFromJobDB() {

	}
}