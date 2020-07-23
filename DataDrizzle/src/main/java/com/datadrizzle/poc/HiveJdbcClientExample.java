package com.datadrizzle.poc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class HiveJdbcClientExample {
  private static String driverName = "org.apache.hive.jdbc.HiveDriver";
  /**
   * @param args
   * @throws SQLException
   */
  public static void main(String[] args) throws SQLException {
      try {
      Class.forName(driverName);
    } catch (ClassNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      System.exit(1);
    }
    System.out.println("driver found");
    //replace "hduser" here with the name of the user the queries should run as
//    Connection con = DriverManager.getConnection("jdbc:hive2://localhost:10000/default", "", "");
    Connection con = DriverManager.getConnection("jdbc:hive2://localhost:10000/movies", "", "");
    System.out.println("connection made");
    Statement stmt = con.createStatement();
//    executeSelect(stmt);
    executeUpdate(stmt);
    
  }
  
  private static void executeSelect(Statement stmt) throws SQLException {
	  System.out.println("executing query");
	    ResultSet rs = stmt.executeQuery("SELECT * FROM actors_main");
	    System.out.println("query executed");
	    List<String> names = new ArrayList<>();
	    while(rs.next()) {
	    	names.add(rs.getString("name"));
	    }
	    System.out.println(names);
  }
  
  private static void executeUpdate(Statement stmt) throws SQLException {
	  String sql = "UPDATE actors_main SET rec_insert_time = '2020-02-21 11:05:40.0' WHERE id = 1";
	  
	  sql = "";
	  
	  stmt.executeUpdate(sql);
  }
  
}