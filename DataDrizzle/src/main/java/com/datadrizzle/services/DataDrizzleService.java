package com.datadrizzle.services;

import static com.datadrizzle.share.Either.right;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.teiid.webui.share.beans.TeiidConnection;
import org.teiid.webui.share.services.ITeiidService;

import com.datadrizzle.connection.translators.ConnectionTranslator;
import com.datadrizzle.entities.Chart;
import com.datadrizzle.entities.DataDrizzleConnection;
import com.datadrizzle.share.Either;
import com.datadrizzle.share.Notification;
import com.datadrizzle.share.dao.IConnectionDAO;
import com.datadrizzle.share.services.IDataDrizzleService;

@Service
public class DataDrizzleService implements IDataDrizzleService {

	@Autowired
	private IConnectionDAO connectionDAO;

	@Autowired
	private ITeiidService teiidService;

	@Autowired
	ConnectionTranslator connectionTranslator;

	private final String USER_AGENT = "Mozilla/5.0";

	public Either<Notification, List<Chart<String, Integer>>> testConnection(DataDrizzleConnection connection) {
		TeiidConnection teiidConnection = connectionTranslator.dataDrizzleConn2TeiidConn(connection);

		List<Chart<String, Integer>> barChart = new ArrayList<>();

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

		return right(barChart);

//	 	return teiidService.testConnection(teiidConnection);
	}

	public Either<Notification, List<Chart<String, Integer>>> getStockAndIndexPriceAsChart(List<String> companyNames) {

		List<Chart<String, Integer>> barChart = new ArrayList<>();

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

		// retrieve stock and index price data from world trading data API.

		try {
			JSONObject stockJSON = new JSONObject(sendGet());

			JSONArray dataArray = stockJSON.getJSONArray("data");

			for (int arrayIndex = 0; arrayIndex < dataArray.length(); arrayIndex++) {
				JSONObject companyInfo = (JSONObject) dataArray.get(arrayIndex);
				companyInfo.remove("symbol");
				companyInfo.remove("currency");
				companyInfo.remove("stock_exchange_long");
				companyInfo.remove("stock_exchange_short");
				companyInfo.remove("timezone");
				companyInfo.remove("timezone_name");
				companyInfo.remove("gmt_offset");
				companyInfo.remove("last_trade_time");
				System.out.println(companyInfo);
			}
			System.out.println("response");

		} catch (JSONException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return right(barChart);
	}

	// HTTP GET request
	private String sendGet() throws IOException {

		String urlStr = "http://www.google.com/search?q=mkyong";

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

}
