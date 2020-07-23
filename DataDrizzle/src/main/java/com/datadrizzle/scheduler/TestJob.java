package com.datadrizzle.scheduler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.StringJoiner;

import org.apache.log4j.Logger;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.teiid.adminapi.Admin;

import com.datadrizzle.share.Notification;

@DisallowConcurrentExecution
public class TestJob implements Job {

	private Logger log = Logger.getLogger(TestJob.class);
	private static String driverName = "org.apache.hive.jdbc.HiveDriver";
	final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	static String host = "localhost";
    static String port = "31000";
    static String vdb = "combine";
    static String modelName = "MysqlXADS3";
    static String pwd = "user@123";
    private static Admin admin;

	public void execute(JobExecutionContext jExeCtx) throws JobExecutionException {
		JobDataMap dataMap = jExeCtx.getJobDetail().getJobDataMap();

		synchronized (dataMap) {
			if(!dataMap.getBoolean("jobCompleted")) {
				return;
			}
		}
		
		dataMap.put("jobCompleted", false);
		
		executeSqoopJOB(dataMap);
		moveDataToMainDB(dataMap);
//		deleteDataFromJobDB();
		
		//update operations
		int recordsUpdated = moveDataToUpdateJobTable(jExeCtx);
		
		if(recordsUpdated > 0) {
			deleteDataFromMainTable(dataMap);
			moveDataToMainTable(dataMap);
		}
		dataMap.put("jobCompleted", true);
	}

	private void moveDataToMainDB(JobDataMap dataMap) {
		System.out.println("moving data to main db");
		Notification notification = new Notification();
		String databaseName = dataMap.getString("databaseName");
		String fromTable = dataMap.getString("tableName");
		String toTable = dataMap.getString("tableName") + "_main";
		Connection conTemp = null;
		Statement stmt = null;
		try {
			System.out.println("loading driver");
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
		System.out.println("driver loaded");
		try {
		// replace "hduser" here with the name of the user the queries should run as
//			    Connection con = DriverManager.getConnection("jdbc:hive2://localhost:10000/default", "", "");
		conTemp = (Connection) dataMap.get("dbConnection");//DriverManager.getConnection("jdbc:hive2://localhost:10000/"+databaseName, "", "");
		System.out.println("connection made");
		stmt = conTemp.createStatement();
		System.out.println("executing query");
		
		Date date = new Date();
		Timestamp ts = new Timestamp(date.getTime());
		//
		String timeStamp = "from_unixtime(unix_timestamp('"+ ts.toString() +"','yyyy-MM-dd HH:mm:ss'))";
		System.out.println(timeStamp);
		//INSERT INTO actors_main SELECT *,
		//from_unixtime(unix_timestamp('2013-05-02 08:15:59', 'yyyy-MM-dd HH:mm:ss')) 
		//as rec_insert_time FROM actors;
		String query = "INSERT INTO " + toTable + " SELECT *,"+ timeStamp +" as rec_insert_time FROM "+fromTable;
		System.out.println(query);
		int newRowsAdded = stmt.executeUpdate(query);
		System.out.println("number of rows added "+newRowsAdded);
		int rowsDeleted = stmt.executeUpdate("truncate table "+fromTable);
		System.out.println("number of rows removed from temp table "+rowsDeleted);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(stmt != null) {
				try {
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

	private void deleteDataFromMainTable(JobDataMap dataMap) {
		System.out.println("deleting data from main db");
		Notification notification = new Notification();
		String databaseName = dataMap.getString("databaseName");
		String fromTable = dataMap.getString("tableName");
		String toTable = dataMap.getString("tableName") + "_main";
		Connection conTemp = null;
		Statement stmt = null;
		try {
			System.out.println("loading driver");
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
		System.out.println("driver loaded");
		try {
		// replace "hduser" here with the name of the user the queries should run as
//			    Connection con = DriverManager.getConnection("jdbc:hive2://localhost:10000/default", "", "");
		conTemp = (Connection) dataMap.get("dbConnection");//DriverManager.getConnection("jdbc:hive2://localhost:10000/"+databaseName, "", "");
		System.out.println("connection made");
		stmt = conTemp.createStatement();
		System.out.println("executing query");
		
		String query = "DELETE FROM " + toTable + " WHERE id IN (SELECT id FROM actors_updateJob)";
		System.out.println(query);
		int rowsDeleted = stmt.executeUpdate(query);
		System.out.println("number of rows added from main table "+rowsDeleted);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	private void moveDataToMainTable(JobDataMap dataMap) {
		System.out.println("moving data to main db");
		Notification notification = new Notification();
		String databaseName = dataMap.getString("databaseName");
		String fromTable = dataMap.getString("tableName");
		String toTable = dataMap.getString("tableName") + "_main";
		Connection conTemp = null;
		Statement stmt = null;
		try {
			System.out.println("loading driver");
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
		System.out.println("driver loaded");
		try {
		// replace "hduser" here with the name of the user the queries should run as
//			    Connection con = DriverManager.getConnection("jdbc:hive2://localhost:10000/default", "", "");
		conTemp = (Connection) dataMap.get("dbConnection");//DriverManager.getConnection("jdbc:hive2://localhost:10000/"+databaseName, "", "");
		System.out.println("connection made");
		stmt = conTemp.createStatement();
		System.out.println("executing query");
		
		Date date = new Date();
		Timestamp ts = new Timestamp(date.getTime());
		//
		String timeStamp = "from_unixtime(unix_timestamp('"+ ts.toString() +"','yyyy-MM-dd HH:mm:ss'))";
		System.out.println(timeStamp);
		//INSERT INTO actors_main SELECT *,
		//from_unixtime(unix_timestamp('2013-05-02 08:15:59', 'yyyy-MM-dd HH:mm:ss')) 
		//as rec_insert_time FROM actors;
		String query = "INSERT INTO " + toTable + " SELECT *,"+ timeStamp +" as rec_insert_time FROM " + fromTable + "_updateJob";
		System.out.println(query);
		int newRowsAdded = stmt.executeUpdate(query);
		System.out.println("number of rows added "+newRowsAdded);
		int rowsDeleted = stmt.executeUpdate("truncate table "+fromTable + "_updateJob");
		System.out.println("number of rows removed from temp table "+rowsDeleted);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	
	private int moveDataToUpdateJobTable(JobExecutionContext jExeCtx) {

		JobDataMap dataMap = jExeCtx.getJobDetail().getJobDataMap();
		List<HashMap<String, String>> rowsChangeList = new ArrayList<>();
		
		int lastInsertValue = getLastUpdatedValue((Connection) dataMap.get("dbConnection"));//dataMap.getInt("tableLastUpdtedValue");
		
		try {
			Connection connection = getDriverConnection();
			
			String select_par_sql = "SELECT s.* from MysqlXADS3.movies.actors s full outer join TEST_hive_movies.actors_main t on s.id = t.id "
					+ "where s.name != t.name and s.id <= " + lastInsertValue + " limit 100";
			rowsChangeList = executeSelect(connection, select_par_sql);
			
			if(rowsChangeList.isEmpty()) {
				return rowsChangeList.size();
			}
			
			//import data to temp update table by sqoop jobz
			StringBuilder conditionBuilder = new StringBuilder();
			for(int listIndex = 0; listIndex <  rowsChangeList.size() - 1; listIndex++) {
				HashMap<String, String> row = rowsChangeList.get(listIndex);
				
				conditionBuilder.append(" id = ");
				conditionBuilder.append(row.get("id"));
				conditionBuilder.append(" OR ");
			}
			
			conditionBuilder.append(" id = ");
			conditionBuilder.append(rowsChangeList.get(rowsChangeList.size() - 1).get("id"));
			
			String sqoopImport = "sqoop import --connect jdbc:mysql://localhost:3306/movies --username hadoop --query 'select actors.* from actors where $CONDITIONS and " + conditionBuilder.toString() + "' "
					+ "--password-file /sqoop_pass/sqoop_pass_file --split-by id "
					+ "--target-dir /user/hive/warehouse/sqoop_import --hive-import --hive-table movies.actors_updateJob -m 2";
			
			String[] sqoopImportArray = new String[] {"sqoop", "import", "--connect", "jdbc:mysql://localhost:3306/movies", "--username", "hadoop", "--query", 
					"select * from actors where $CONDITIONS and " + conditionBuilder.toString(),
					"--password-file", "/sqoop_pass/sqoop_pass_file", "--split-by", "id", "--target-dir", "/user/hive/warehouse/sqoop_import",
					"--hive-import", "--hive-table","movies.actors_updateJob" , "-m", "2"};
			
			System.out.println("update job: ");
			System.out.println(sqoopImport);
			
			Runtime rt = Runtime.getRuntime();
			
			Process proc = null;
			try {
				proc = rt.exec(sqoopImportArray);
			} catch (IOException e) {
				e.printStackTrace();
//				notification.addErrorMessage("Technical difficulties while running navtive command");
//				return notification;
			}
	
			BufferedReader stdInput = new BufferedReader(new 
			     InputStreamReader(proc.getInputStream()));
	
			BufferedReader stdError = new BufferedReader(new 
			     InputStreamReader(proc.getErrorStream()));
	
			try{
				// Read the output from the command
				System.out.println("Here is the standard output of the command:\n");
				String s = null;
				while ((s = stdInput.readLine()) != null) {
					System.out.println(s);
				}
			} catch(IOException e) {
				e.printStackTrace();
//				notification.addErrorMessage("Cannot read native command output");
//				return notification;
			}
	
			try{
				// Read any errors from the attempted command
				System.out.println("Here is the standard error of the command (if any):\n");
				String s = null;
				while ((s = stdError.readLine()) != null) {
				    System.out.println(s);
				}
			} catch(IOException e) {
				e.printStackTrace();
//				notification.addErrorMessage("Cannot read native command output");
//				return notification;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rowsChangeList.size();
	
	}
	
	static Connection getDriverConnection() throws Exception {
		System.out.println("Used values{host = " + host + "}{Port = " + port + " }{vdb name = " + vdb + " }");
		String url = "jdbc:teiid:" + vdb + "@mm://" + host + ":" + port + ";showplan=on"; // note
		                                                                                    // showplan
		Class.forName("org.teiid.jdbc.TeiidDriver");
		
		return DriverManager.getConnection(url, "user", pwd);
	}
	
	public static void execute(Connection connection, String sql) throws Exception {
        if (sql == null)
            return;
        try {
            System.out.println("Used values{SQL = " + sql + "}");
            System.out.println("Setting autocommit false");
            Statement statement = connection.createStatement();

            ResultSet results = null;
            if (sql.contains("INSERT")) {
                int rwo_insert = statement.executeUpdate(sql);
                System.out.println(rwo_insert);

            } else {}

            /*
             * PreparedStatement ps1 = connection.prepareStatement(sql);
             * ps1.setString(1, "xxx"); ps1.setString(2, "abc"); ps1.addBatch();
             * ps1.executeBatch(); System.out.println("excuted");
             */

        } catch (SQLException e) {
            System.out.print(e.getMessage());
            e.printStackTrace();
        } finally {
        	
        }
        System.out.println(" ");
    }
	
	public static List<HashMap<String, String>> executeSelect(Connection connection, String sql) throws Exception {
		Statement statement = connection.createStatement();
		List<String> primaryKeyValueList = new ArrayList<>();
        ResultSet results = statement.executeQuery(sql);
        ResultSetMetaData metadata = results.getMetaData();
        int columns = metadata.getColumnCount();
        List<String> columnList = new ArrayList<>();
        List<HashMap<String, String>> rowsList = new ArrayList<>();
        for (int i = 1; i <= columns; i++ ) {
              columnList.add(metadata.getColumnName(i));
        }
        int rowCnt = 0;
        System.out.println("\nResults");
        for (int row = 1; results.next(); row++) {
        	HashMap<String, String> rowMap = new HashMap<>();
            System.out.print(row + ": ");
            for (int i = 0; i < columns; i++) {
                if (i > 0) {
                    System.out.print(",");
                }
                //primaryKeyValueList.add(results.getString("id"));
                rowMap.put(metadata.getColumnName(i + 1), results.getString(i + 1));
            }
            rowsList.add(rowMap);
            System.out.println();
            rowCnt++;
        }
        System.out.println("rowCnt "+rowCnt);

        results.close();
        statement.close();
        
        return rowsList;
	}
	
	private int getLastUpdatedValue(Connection connection) {
		
		System.out.println("executing query");
		Statement stmt = null;
		try {
		stmt = connection.createStatement();
		//
		String query = "SELECT MAX(id) from actors_main";
		System.out.println(query);
		ResultSet res = stmt.executeQuery(query);
		if(res.next()) {
			return res.getInt(1);
		}
		} catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return 0;
	}
}