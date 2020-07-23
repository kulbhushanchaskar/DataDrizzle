package com.datadrizzle.poc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SqoopIntergrationPOC {
	
	public static void main(String[] args) throws IOException {
		System.out.println("sqoop integration poc");
		//createSqoopConn();
		runShellCmd();
	}
	
	private void loadTable() {
		
	}
	
	private static void createSqoopConn() {
		/*String url = "http://localhost:12000/sqoop/";
		SqoopClient sc = new SqoopClient(url);
		List<MJob> mjob = sc.getJobs();
		System.out.println(mjob);*/
//		sc.createJob(fromLinkName, toLinkName)
		
		
	}
	
	private static void runShellCmd() throws IOException {
		Runtime rt = Runtime.getRuntime();
		String[] commands = {"/home/hadoop/sqoop/bin/sqoop", "job", "--list"};
		Process proc = rt.exec(commands);

		BufferedReader stdInput = new BufferedReader(new 
		     InputStreamReader(proc.getInputStream()));

		BufferedReader stdError = new BufferedReader(new 
		     InputStreamReader(proc.getErrorStream()));

		// Read the output from the command
		System.out.println("Here is the standard output of the command:\n");
		String s = null;
		while ((s = stdInput.readLine()) != null) {
		    System.out.println(s);
		}

		// Read any errors from the attempted command
		System.out.println("Here is the standard error of the command (if any):\n");
		while ((s = stdError.readLine()) != null) {
		    System.out.println(s);
		}
	}
}
