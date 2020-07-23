package com.datadrizzle.services;

import static com.datadrizzle.share.Either.left;
import static com.datadrizzle.share.Either.right;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import javax.sql.rowset.serial.SerialBlob;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.teiid.webui.share.beans.TeiidConnection;

import com.datadrizzle.connection.translators.ConnectionTranslator;
import com.datadrizzle.connection.translators.StockTranslator;
import com.datadrizzle.entities.ArchiveJob;
import com.datadrizzle.entities.Chart;
import com.datadrizzle.entities.DataDrizzleConnection;
import com.datadrizzle.entities.MutualFundHistoryData;
import com.datadrizzle.entities.MutualFundIndex;
import com.datadrizzle.entities.RetensionJobEntity;
import com.datadrizzle.entities.SearchForm;
import com.datadrizzle.entities.StockAndIndexRealTime;
import com.datadrizzle.entities.TableRow;
import com.datadrizzle.scheduler.RetensionJob;
import com.datadrizzle.scheduler.TestJob;
import com.datadrizzle.scheduler.UpdateModeJob;
import com.datadrizzle.services.translators.MutualFundTranslator;
import com.datadrizzle.share.ApplicationConstants;
import com.datadrizzle.share.Either;
import com.datadrizzle.share.Notification;
import com.datadrizzle.share.Response;
import com.datadrizzle.share.dao.IConnectionDAO;
//import com.datadrizzle.share.dao.IStockDao;
import com.datadrizzle.share.services.IDataDrizzleService;
import com.datadrizzle.ui.model.MutualFundCompany;
//import com.datadrizzle.vo.MutualFundCompanyVO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Service
//@Transactional
public class DataDrizzleService implements IDataDrizzleService {

	@Autowired
	private IConnectionDAO connectionDAO;
	
//	@Autowired
//	private IStockDao stockDAO;

	/*@Autowired
	private ITeiidService teiidService;*/

	@Autowired
	ConnectionTranslator connectionTranslator;
	
	@Autowired
	StockTranslator stockTranslator;

	private final String USER_AGENT = "Mozilla/5.0";

	public Response<List<String>> testConnection(DataDrizzleConnection connection) {
		TeiidConnection teiidConnection = connectionTranslator.dataDrizzleConn2TeiidConn(connection);
		
		
		//We are going to check connectivity with the database using jdbc
		Response<List<String>> dbConnectionResp = getDatabaseTables(connection);
		
		
		return dbConnectionResp;

		/*List<Chart<String, Integer>> barChart = new ArrayList<>();

		String type = "bar";
		String name = "SF Zoo";
		List<String> chart1XValues = new ArrayList<>(Arrays.asList("giraffes", "orangutans", "monkeys", "lion"));
		List<Integer> chart1YValues = new ArrayList<>(Arrays.asList(20, 14, 30, 10));
		Chart<String, Integer> chart1 = new Chart<>(type, name, chart1XValues, chart1YValues);

		name = "LA Zoo";
		List<String> chart2XValues = new ArrayList<>(Arrays.asList("giraffes", "orangutans", "monkeys", "lion"));
		List<Integer> chart2YValues = new ArrayList<>(Arrays.asList(12, 18, 23, 5));
		Chart<String, Integer> chart2 = new Chart<>(type, name, chart2XValues, chart2YValues);

		barChart.add(chart1);
		barChart.add(chart2);

		return right(barChart);*/

		// return teiidService.testConnection(teiidConnection);
	}
	
	public Response<List<TableRow>> getData(SearchForm form) {
		Notification n = new Notification();
		Connection connection = getDBConnection("movies");
		System.out.println("executing query");
		Statement stmt = null;
		ResultSet res = null;
		List<TableRow> table = new ArrayList<>();
		try {
		stmt = connection.createStatement();
		String query = "SELECT * from actors_main WHERE rec_insert_time >= '"+ form.getSearchFromDate() +"' AND rec_insert_time <= '"+ form.getSearchToDate() +"'";
		System.out.println(query);
		res = stmt.executeQuery(query);
		while(res.next()) {
			String id = res.getString(1);
			String name = res.getString(2);
			TableRow row = new TableRow();
			row.put("id", id);
			row.put("name", name);
//			row.put("pic", blobToBase64(res.getString(3)) );
			row.put("rec_archive_time", res.getString(3));
			table.add(row);
		}
		} catch(Exception e) {
			e.printStackTrace();
		}
		finally {
				try {
					res.close();
					stmt.close();
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		return new Response<List<TableRow>>(n, table, "200");
	}
	
	
	
	private Response<List<String>> getDatabaseTables(DataDrizzleConnection connection) {
		
		Notification notification = new Notification();
		
		List<String> tableList = new ArrayList<>();
		try {
			tableList = getDatabaseTableList(connection.getConnectionParameters());
		} catch (SQLException e) {
			return new Response<List<String>>(notification, tableList, "200");
		}
		
		notification.addSuccess("Connection to the database is successful");
		return new Response<List<String>>(notification, tableList, "200");
	}
	
	private List<String> getDatabaseTableList(Map<String,String> connectionParameters) throws SQLException {
		
//		Map<String,String> connectionParameters = connection.getConnectionParameters();
		String connectionName = connectionParameters.get("connectionName");
		String host = connectionParameters.get("host");
		String username = connectionParameters.get("username");
		String pwd = connectionParameters.get("pwd");
		String databaseName = connectionParameters.get("databaseName");
		String port = connectionParameters.get("port");
		List<String> tableList = new ArrayList<String>();
		
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+databaseName, username, pwd)) {
	        if (conn != null) {
	            System.out.println("Connected to the database!");
	        } else {
	        	throw new SQLException("Unable to connect to the database");
	        }
	        
	        //Get database tables
	        DatabaseMetaData dbmd = conn.getMetaData();
            String[] types = {"TABLE"};
            ResultSet rs = dbmd.getTables(databaseName, null, "%", types);
			while (rs.next()) {
				tableList.add(rs.getString(3));
			}

        } catch (SQLException e) {
            throw new SQLException("Error while fetching details from the database");
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Unable to connect to the database");
        }
		return tableList;

	}

	public Either<Notification, List<Chart<String, Double>>> getStockAndIndexPrice(List<String> companyNames) {

		List<Chart<String, Double>> barChart = new ArrayList<>();

		// retrieve stock and index price data from world trading data API.
		try {
			JSONObject stockJSON = new JSONObject(
					sendGet(buildURL(companyNames, ApplicationConstants.stockAndRealTimeIndexAPI)));
			JSONArray dataArray = stockJSON.getJSONArray("data");

			Gson gson = new Gson();
			String data = dataArray.toString().replace("52", "fifty_two");
			List<StockAndIndexRealTime> stockList = gson.fromJson(data, new TypeToken<List<StockAndIndexRealTime>>() {
			}.getType());

			System.out.println(stockList);

			stockList.stream().forEach(stock -> DataDrizzleService.createChart(barChart, stock));
		} catch (JSONException | IOException e) {
			Notification notification = new Notification();
			notification.addErrorMessage("Technical difficulties occured during processing the data");
			return left(notification);
		}

		return right(barChart);
	}

	// HTTP GET request
	private String sendGet(String urlStr) throws IOException {

		URL obj = new URL(urlStr);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		// add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + urlStr);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// return result
		return response.toString();

	}

	private String buildURL(List<String> companyNames, String baseURL) {
		StringBuilder urlBuilder = new StringBuilder(baseURL);
		urlBuilder.append("?");
		urlBuilder.append("symbol");
		urlBuilder.append("=");

		urlBuilder.append(String.join(",", companyNames));

		urlBuilder.append("&");
		urlBuilder.append("api_token=");
		urlBuilder.append(ApplicationConstants.worldTradingDataAPIToken);

		return urlBuilder.toString();
	}

	private static void createChart(List<Chart<String, Double>> barChart, StockAndIndexRealTime stock) {

		List<Double> yAxisValues = new ArrayList<>();
		//convert price to USD
		/*yAxisValues.add(stock.getPrice());
		yAxisValues.add(stock.getPrice_open());
		yAxisValues.add(stock.getDay_high());
		yAxisValues.add(stock.getDay_low());
		yAxisValues.add(stock.getFifty_two_week_high());
		yAxisValues.add(stock.getFifty_two_week_low());
		yAxisValues.add(stock.getDay_change());
		yAxisValues.add(stock.getChange_pct());
		yAxisValues.add(stock.getClose_yesterday());*/
//		yAxisValues.add(stock.getMarket_cap());
//		yAxisValues.add(stock.getVolume());
//		yAxisValues.add(stock.getShares());

		/*Chart<String, Double> chart = new Chart<>("bar", stock.getName(), ApplicationConstants.stockIndexText,
				yAxisValues);

		barChart.add(chart);*/

	}

	public Either<Notification, List<Chart<String, Double>>> getMutualFundIndexes(List<String> companyNames) {

		List<Chart<String, Double>> barChart = new ArrayList<>();

		// retrieve stock and index price data from world trading data API.

		try {
			JSONObject indexJSON = new JSONObject(
					sendGet(buildURL(companyNames, ApplicationConstants.mutualFundRealTimeIndexAPI)));
			JSONArray dataArray = indexJSON.getJSONArray("data");

			Gson gson = new Gson();
			String data = dataArray.toString()/*.replace("52_week_high", "fifty_two_week_high").replace("52_week_low",
					"fifty_two_week_low")*/;
			List<MutualFundIndex> mutualFunds = gson.fromJson(data, new TypeToken<List<MutualFundIndex>>() {
			}.getType());

			System.out.println("========== " + indexJSON);

			mutualFunds.stream().forEach(mutualFund -> createMutualFundChart(barChart, mutualFund));
		} catch (JSONException | IOException e) {
			Notification notification = new Notification();
			notification.addErrorMessage("Technical difficulties occured during processing the data");
			return left(notification);
		}

		return right(barChart);
	}

	private void createMutualFundChart(List<Chart<String, Double>> barChart, MutualFundIndex mutualFund) {
		
		/*
		 * public static List<String> mutualFundIndexText = Arrays.asList("price","close_yesterday","return_ytd",
			"net_assets","change_asset_value",
			"change_pct", "yield_pct","change_pct","return_day","return_1week","return_4week","return_13week",
			"return_52week","return_156week","return_260week","income_dividend","income_dividend_date","capital_gain",
			"expense_ratio");
		 */

		/*List<Double> yAxisValues = new ArrayList<>();
		yAxisValues.add(mutualFund.getPrice());
		yAxisValues.add(mutualFund.getClose_yesterday());
		yAxisValues.add(mutualFund.getReturn_ytd());
//		yAxisValues.add(mutualFund.getNet_assets());
		yAxisValues.add(mutualFund.getChange_asset_value());
		yAxisValues.add(mutualFund.getChange_pct());
		yAxisValues.add(mutualFund.getYield_pct());
		yAxisValues.add(mutualFund.getReturn_day());
		yAxisValues.add(mutualFund.getReturn_1week());
		yAxisValues.add(mutualFund.getReturn_4week());
		yAxisValues.add(mutualFund.getReturn_13week());
		yAxisValues.add(mutualFund.getReturn_52week());
		yAxisValues.add(mutualFund.getReturn_156week());
		yAxisValues.add(mutualFund.getReturn_260week());
		yAxisValues.add(mutualFund.getIncome_dividend());
//		yAxisValues.add(mutualFund.getIncome_dividend_date());
		yAxisValues.add(mutualFund.getCapital_gain());
		yAxisValues.add(mutualFund.getExpense_ratio());

		Chart<String, Double> chart = new Chart<>("bar", mutualFund.getName(), ApplicationConstants.mutualFundIndexText,
				yAxisValues);

		barChart.add(chart);*/

	}
	
	public Either<Notification, List<MutualFundCompany>> getMutualfundSymbols() {
		/*List<MutualFundCompanyVO> companies = stockDAO.getAllMutualFundCompanies();
		return right(stockTranslator.mutualFundVOList2UIModelList(companies));*/
		return null;
	}
	
	public Either<Notification, List<MutualFundHistoryData>> getMutualfundHistory(String symbol) {
		try {
			JSONObject indexJSON = new JSONObject(
					sendGet(buildURL(Arrays.asList(symbol), ApplicationConstants.mutualFundHistoryAPI)));
			JSONObject mutualFundHistoryJSON = (JSONObject) indexJSON.get("history");
			
			List<MutualFundHistoryData> mutualFundHistory = MutualFundTranslator.mutualFundHistoryData(mutualFundHistoryJSON);
			
			System.out.println(mutualFundHistory);
			return right(mutualFundHistory);
		}
		catch(JSONException | IOException e) {
			Notification notification = new Notification();
			notification.addErrorMessage("Technical difficulties occured during processing the data");
			return left(notification);
		}
	}
	
	private void ScheduleJOb() {
		
	}

	@Override
	public Response<String> scheduleArchiveJob(ArchiveJob job) {
		
		Runtime rt = Runtime.getRuntime();
		Notification notification = new Notification();
		
		//cretePrimaryHiveDB(); //create hive database if not exist
		//createPrimaryHiveTables();
		//truncatePrimaryHiveTable();
		
		//creteMainHiveDB(); //create hive database if not exist
		//createMainHiveTables();
		createSqoopJob(rt,  job);
		scheduleSqoopJob(rt, job);
		
//		scheduleUpdateJob(rt, job);

		notification.addSuccess("Archive Job succcessfully scheduled");
		return new Response<String>(notification, job.getConnectionParameters().get("connectionName"), "200");
	}
	
	private Notification createSqoopJob(Runtime rt, ArchiveJob job) {
		
		Notification notification = new Notification();
		Map<String, String> connectionParameter = job.getConnectionParameters();
		List<String> tableList = new ArrayList<>();
		try {
			tableList = job.getTables();//getDatabaseTableList(job.getConnectionParameters());
		} catch (/*SQL*/Exception e1) {
			e1.printStackTrace();
			notification.addErrorMessage("Cannot connection and fetching table details from database");
		}
		
		String jdbcURL = "jdbc:mysql://" + connectionParameter.get("host") + "/" + connectionParameter.get("databaseName");
		String userName = connectionParameter.get("username");
		String password = connectionParameter.get("pwd");
		String mValue = "2";
		
		for(String table: tableList) {
			String jobName = connectionParameter.get("connectionName") + "." + connectionParameter.get("databaseName") + "." + table;
			/*
			 * sqoop job --create moviesActorArchieve.abc -- import --connect jdbc:mysql://localhost/movies --username hadoop 
			 * --password-file sqoop_pass/sqoop_pass_file --table actors  --m 3 --split-by id --check-column id 
			 * --incremental append --last-value 0 --hive-import --hive-table movies.actors
			 */
	 		
			/*String[] commands = {"sqoop", "job", "--create", jobName, "-- import", "--connect", jdbcURL, "--username",userName,"--map-column-hive", "pic=binary",
					"--password-file", "/sqoop_pass/sqoop_pass_file", "--table", table, "--m", mValue, "--check-column", "id","--split-by","id",  
					"--incremental", "append", "--hive-import", "--hive-table", connectionParameter.get("databaseName") + "." + table };*/
			
			String[] commands = {"sqoop", "job", "--create", jobName, "-- import", "--connect", jdbcURL, "--username",userName,
					"--password-file", "/sqoop_pass/sqoop_pass_file", "--table", table, "--m", mValue, "--check-column", "id","--split-by","id",  
					"--incremental", "append", "--hive-import", "--hive-table", connectionParameter.get("databaseName") + "." + table };
			
			System.out.println("creating job");
			System.out.println(commands);
			
			 StringJoiner joiner = new StringJoiner(" ");
		      for(int i = 0; i < commands.length; i++) {
		         joiner.add(commands[i]);
		      }
		      
		      String str = joiner.toString();
		      System.out.println(str);
			
			Process proc = null;
			try {
				proc = rt.exec(str);
			} catch (IOException e) {
				e.printStackTrace();
				notification.addErrorMessage("Technical difficulties while running navtive command");
				return notification;
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
				notification.addErrorMessage("Cannot read native command output");
				return notification;
			}
	
			try{
				// Read any errors from the attempted command
				System.out.println("Here is the standard error of the command (if any):\n");
				String s = null;
				while ((s = stdError.readLine()) != null) {
				    System.out.println(s);
				}
			} catch(IOException e) {
				notification.addErrorMessage("Cannot read native command output");
				return notification;
			}
		}
		notification.addSuccess("Archive Job succcessfully scheduled");
		return notification;
	}
	
	private Notification executeSqoopJob(Runtime rt, ArchiveJob job) {
		return null;
		
	}
	
	private Notification scheduleUpdateJob(Runtime rt, ArchiveJob archiveJob) {
		
		Notification n = new Notification();
		List<String> tableList = archiveJob.getTables();//getDatabaseTableList(job.getConnectionParameters());
		String connectionName = archiveJob.getConnectionParameters().get("connectionName");
		String databaseName = archiveJob.getConnectionParameters().get("databaseName");
		
//		String sqoopLastUpdateValue = getSqoopLastIncrValue(rt, archiveJob);
		Connection connection = getDBConnection(databaseName);
		
		for(String table: tableList) {
//		int lastUpdatedValue = getLastUpdatedValue(databaseName, connection);
		
		System.out.println("starting a scheduler");
		 // specify the job' s details..
		JobDetail job = JobBuilder.newJob()
       						  .ofType(UpdateModeJob.class)
//                                 .withIdentity("testJob")
                                 .build();
		job.getJobDataMap().put("connectionName", connectionName);
		job.getJobDataMap().put("databaseName", databaseName);
		job.getJobDataMap().put("tableName", table);
//		job.getJobDataMap().put("tableLastUpdtedValue", lastUpdatedValue);
		job.getJobDataMap().put("dbConnection", connection);

       // specify the running period of the job
       Trigger trigger = TriggerBuilder.newTrigger()
	                       .withSchedule(SimpleScheduleBuilder.simpleSchedule()
	                       .withIntervalInSeconds(120)
	                       .withMisfireHandlingInstructionNextWithRemainingCount()
	                       .repeatForever())
                           .build();

       //schedule the job
       SchedulerFactory schFactory = new StdSchedulerFactory();
       Scheduler sch;
		try {
			sch = schFactory.getScheduler();
			sch.start();
			sch.scheduleJob(job, trigger);
		} catch (SchedulerException e) {
			e.printStackTrace();
			n.addErrorMessage("Unable to schedule the job");
		}
	}
       System.out.println("scheduler started");
		
		return null;
	}

	private String getSqoopLastIncrValue(Runtime rt, ArchiveJob archiveJob) {
		String command = "sqoop job --show aaaa.movies.actors";
		return executeNativeCommand(command);
	}
	
	private String executeNativeCommand(String command) {
		Notification notification = new Notification();
		
		Runtime rt = Runtime.getRuntime();
		Process proc = null;
		
		try {
			proc = rt.exec(command);
		} catch (IOException e) {
			e.printStackTrace();
			notification.addErrorMessage("Technical difficulties while running navtive command");
			return "";
		}

		BufferedReader stdInput = new BufferedReader(new 
		     InputStreamReader(proc.getInputStream()));

		BufferedReader stdError = new BufferedReader(new 
		     InputStreamReader(proc.getErrorStream()));

		StringBuilder commandOutput = new StringBuilder();
		try{
			// Read the output from the command
			String commandOutputLines;
			System.out.println("Here is the standard output of the command:\n");
			while ((commandOutputLines = stdInput.readLine()) != null) {
				System.out.println(commandOutputLines);
				if(commandOutputLines.contains("incremental.last.value =")) {
					return commandOutputLines.substring(commandOutputLines.lastIndexOf(" ") + 1);
				}
				commandOutput.append(commandOutputLines);
			}
		} catch(IOException e) {
			notification.addErrorMessage("Cannot read native command output");
			return "";
		}

		try{
			// Read any errors from the attempted command
			System.out.println("Here is the standard error of the command (if any):\n");
			String s = null;
			while ((s = stdError.readLine()) != null) {
			    System.out.println(s);
			}
		} catch(IOException e) {
			notification.addErrorMessage("Cannot read native command output");
			return "";
		}
		
		return commandOutput.toString();
	}
	
	private Notification scheduleSqoopJob(Runtime rt, ArchiveJob archiveJob) {
		
		Notification n = new Notification();
		List<String> tableList = archiveJob.getTables();//getDatabaseTableList(job.getConnectionParameters());
		String connectionName = archiveJob.getConnectionParameters().get("connectionName");
		String databaseName = archiveJob.getConnectionParameters().get("databaseName");
		
		
		for(String table: tableList) {
		
		System.out.println("starting a scheduler");
		 // specify the job' s details..
		JobDetail job = JobBuilder.newJob()
       						  .ofType(TestJob.class)
//                                 .withIdentity("testJob")
                                 .build();
		job.getJobDataMap().put("connectionName", connectionName);
		job.getJobDataMap().put("databaseName", databaseName);
		job.getJobDataMap().put("tableName", table);
		job.getJobDataMap().put("dbConnection", getDBConnection(databaseName));
		job.getJobDataMap().put("jobCompleted", true);

       // specify the running period of the job
       Trigger trigger = TriggerBuilder.newTrigger()
	                       .withSchedule(SimpleScheduleBuilder.simpleSchedule()
	                       .withIntervalInSeconds(120)
	                       .withMisfireHandlingInstructionNextWithRemainingCount()
	                       .repeatForever())
	                       .startNow()
                           .build();

       //schedule the job
       SchedulerFactory schFactory = new StdSchedulerFactory();
       Scheduler sch;
		try {
			sch = schFactory.getScheduler();
			sch.start();
			sch.scheduleJob(job, trigger);
		} catch (SchedulerException e) {
			e.printStackTrace();
			n.addErrorMessage("Unable to schedule the job");
		}
	}
       System.out.println("scheduler started");
		
		return null;
	}
	
	public Response<String> scheduleRetensionJob(RetensionJobEntity retentionJob) {
		
		Notification n = new Notification();
		List<String> tableList = retentionJob.getTables();//getDatabaseTableList(job.getConnectionParameters());
		String connectionName = retentionJob.getConnectionParameters().get("connectionName");
		String databaseName = retentionJob.getConnectionParameters().get("databaseName");
		String retensionDate = retentionJob.getRetensionDate();
		
		for(String table: tableList) {
		
		System.out.println("starting a scheduler for retension");
		 // specify the job' s details..
		JobDetail job = JobBuilder.newJob()
       						  .ofType(RetensionJob.class)
//                                 .withIdentity("testJob")
                                 .build();
		job.getJobDataMap().put("connectionName", connectionName);
		job.getJobDataMap().put("databaseName", databaseName);
		job.getJobDataMap().put("tableName", table);
		job.getJobDataMap().put("dbConnection", getDBConnection(databaseName));
		job.getJobDataMap().put("retensionDate", retensionDate);

       // specify the running period of the job
       Trigger trigger = TriggerBuilder.newTrigger()
	                       .withSchedule(SimpleScheduleBuilder.simpleSchedule()
	                       .withIntervalInSeconds(120)
	                       .withMisfireHandlingInstructionNextWithRemainingCount()
	                       .repeatForever())
                           .build();

       //schedule the job
       SchedulerFactory schFactory = new StdSchedulerFactory();
       Scheduler sch;
		try {
			sch = schFactory.getScheduler();
			sch.start();
			sch.scheduleJob(job, trigger);
		} catch (SchedulerException e) {
			e.printStackTrace();
			n.addErrorMessage("Unable to schedule the retension job");
		}
	}
       System.out.println("retension scheduler started");
		
		return null;
		
	}
	
	private Notification createRetensionJob(Runtime rt, RetensionJobEntity retentionJob) {
		
		Notification notification = new Notification();
		Map<String, String> connectionParameter = retentionJob.getConnectionParameters();
		List<String> tableList = new ArrayList<>();
		try {
			tableList = retentionJob.getTables();//getDatabaseTableList(job.getConnectionParameters());
		} catch (/*SQL*/Exception e1) {
			e1.printStackTrace();
			notification.addErrorMessage("Cannot connection and fetching table details from database");
		}
		
		String jdbcURL = "jdbc:mysql://" + connectionParameter.get("host") + "/" + connectionParameter.get("databaseName");
		String userName = connectionParameter.get("username");
		String password = connectionParameter.get("pwd");
		String mValue = "1";
//		String incrementalAppend = "--incremental append";
//		String lastValue = "--last value 0";
//		String hiveImport = "--hive-import";
//		String hiveTable = "--hive-table";
//		String hiveTableName = jobName;
		
		for(String table: tableList) {
			String jobName = connectionParameter.get("connectionName") + "." + connectionParameter.get("databaseName") + "." + table;
			/*
			 * sqoop job --create moviesActorArchieve.abc -- import --connect jdbc:mysql://localhost/movies --username hadoop 
			 * --password-file sqoop_pass/sqoop_pass_file --table actors  --m 3 --split-by id --check-column id 
			 * --incremental append --last-value 0 --hive-import --hive-table movies.actors
			 */
	 		
			String[] commands = {"sqoop", "job", "--create", jobName, "-- import", "--connect", jdbcURL, "--username",userName,
					"--password-file", "sqoop_pass/sqoop_pass_file", "--table", table, "--m", mValue, "--check-column", "id","--split-by","id",  
					"--incremental", "append", "--hive-import", "--hive-table", connectionParameter.get("databaseName") + "." + table };
			
			System.out.println("creating job");
			System.out.println(commands);
			
			 StringJoiner joiner = new StringJoiner(" ");
		      for(int i = 0; i < commands.length; i++) {
		         joiner.add(commands[i]);
		      }
		      
		      String str = joiner.toString();
		      System.out.println(str);
			
			Process proc = null;
			try {
				proc = rt.exec(str);
			} catch (IOException e) {
				e.printStackTrace();
				notification.addErrorMessage("Technical difficulties while running navtive command");
				return notification;
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
				notification.addErrorMessage("Cannot read native command output");
				return notification;
			}
	
			try{
				// Read any errors from the attempted command
				System.out.println("Here is the standard error of the command (if any):\n");
				String s = null;
				while ((s = stdError.readLine()) != null) {
				    System.out.println(s);
				}
			} catch(IOException e) {
				notification.addErrorMessage("Cannot read native command output");
				return notification;
			}
		}
		notification.addSuccess("Archive Job succcessfully scheduled");
		return notification;
		
	}

	private Connection getDBConnection(String databaseName) {
		Connection conTemp = null;
		try {
			System.out.println("loading driver");
			Class.forName("org.apache.hive.jdbc.HiveDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
		System.out.println("driver loaded");
		try {
		// replace "hduser" here with the name of the user the queries should run as
//			    Connection con = DriverManager.getConnection("jdbc:hive2://localhost:10000/default", "", "");
		return DriverManager.getConnection("jdbc:hive2://localhost:10000/"+databaseName, "", "");
		} catch(Exception e) {
			e.printStackTrace();
		}
		return conTemp;
	}
	
	private String blobToBase64(String blob) {
        try {
        	byte[] byteConent = blob.getBytes();
            Blob realBlob = new SerialBlob(byteConent);
            
//			byte [] bytes = blob.getBytes(1l, (int)blob.length());
			String decoded = new String(Base64.getDecoder().decode(realBlob.getBytes(1l, (int)blob.length())));
			return decoded;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		return "";
	}
	
	public Response<byte[]> getFile(String id) {
		Notification n = new Notification();
		Connection connection = getDBConnection("movies");
		System.out.println("executing query");
		Statement stmt = null;
		ResultSet res = null;
		try {
		stmt = connection.createStatement();
		String query = "SELECT pic from actors_main WHERE id = "+id;
		System.out.println(query);
		res = stmt.executeQuery(query);
		if(res.next()) {
			return new Response<byte[]>(n, res.getString(1).getBytes(), "200");
		}
		} catch(Exception e) {
			e.printStackTrace();
		}
		finally {
				try {
					res.close();
					stmt.close();
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		return new Response<byte[]>(n, "".getBytes(), "200");
	}
}
