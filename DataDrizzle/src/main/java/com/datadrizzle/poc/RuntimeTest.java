package com.datadrizzle.poc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RuntimeTest {
	
	public static void main(String[] args) throws IOException {
		Runtime rt = Runtime.getRuntime();
		
		Process proc = null;
		try {
			String strcmd = "sqoop import --connect jdbc:mysql://localhost:3306/movies --username hadoop --query 'select * from actors where $CONDITIONS and  id = 4' --password-file /sqoop_pass/sqoop_pass_file --split-by id --target-dir /user/hive/warehouse/sqoop_import --hive-import --hive-table movies.actors_updateJob -m 2";
			String[] strcmdArr = new String[] {"sqoop", "import", "--connect", "jdbc:mysql://localhost:3306/movies", "--username", "hadoop", "--query", 
					"select * from actors where $CONDITIONS and  id = 4",
					"--password-file", "/sqoop_pass/sqoop_pass_file", "--split-by", "id", "--target-dir", "/user/hive/warehouse/sqoop_import",
					"--hive-import", "--hive-table","movies.actors_updateJob" , "-m", "2"};
			proc = rt.exec(strcmdArr);
		} catch (IOException e) {
			e.printStackTrace();
//			notification.addErrorMessage("Technical difficulties while running navtive command");
//			return notification;
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
//			notification.addErrorMessage("Cannot read native command output");
//			return notification;
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
//			notification.addErrorMessage("Cannot read native command output");
//			return notification;
		}
		
		
	}

}
