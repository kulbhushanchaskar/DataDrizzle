package com.datadrizzle.services;

import static com.datadrizzle.share.Either.left;
import static com.datadrizzle.share.Either.right;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.teiid.webui.share.beans.TeiidConnection;
import org.teiid.webui.share.services.ITeiidService;

import com.datadrizzle.connection.translators.ConnectionTranslator;
import com.datadrizzle.connection.translators.StockTranslator;
import com.datadrizzle.entities.Chart;
import com.datadrizzle.entities.DataDrizzleConnection;
import com.datadrizzle.entities.MutualFundIndex;
import com.datadrizzle.entities.StockAndIndexRealTime;
import com.datadrizzle.share.ApplicationConstants;
import com.datadrizzle.share.Either;
import com.datadrizzle.share.Notification;
import com.datadrizzle.share.dao.IConnectionDAO;
import com.datadrizzle.share.dao.IStockDao;
import com.datadrizzle.share.services.IDataDrizzleService;
import com.datadrizzle.ui.model.MutualFundCompany;
import com.datadrizzle.vo.MutualFundCompanyVO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Service
@Transactional
public class DataDrizzleService implements IDataDrizzleService {

	@Autowired
	private IConnectionDAO connectionDAO;
	
	@Autowired
	private IStockDao stockDAO;

	@Autowired
	private ITeiidService teiidService;

	@Autowired
	ConnectionTranslator connectionTranslator;
	
	@Autowired
	StockTranslator stockTranslator;

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

		// return teiidService.testConnection(teiidConnection);
	}

	public Either<Notification, List<Chart<String, Double>>> getStockAndIndexPrice(List<String> companyNames) {

		List<Chart<String, Double>> barChart = new ArrayList<>();

		// retrieve stock and index price data from world trading data API.
		try {
			JSONObject stockJSON = new JSONObject(
					sendGet(buildURL(companyNames, ApplicationConstants.stockAndRealTimeIndexAPI)));
			JSONArray dataArray = stockJSON.getJSONArray("data");

			Gson gson = new Gson();
			String data = dataArray.toString().replace("52_week_high", "fifty_two_week_high").replace("52_week_low",
					"fifty_two_week_low");
			List<StockAndIndexRealTime> stockList = gson.fromJson(data, new TypeToken<List<StockAndIndexRealTime>>() {
			}.getType());

			System.out.println(stockList);

			stockList.stream().forEach(stock -> DataDrizzleService.createChart(barChart, stock));
		} catch (JSONException | IOException e) {
			Notification notification = new Notification();
			notification.addMessage("Technical difficulties occured during processing the data");
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
		yAxisValues.add(stock.getPrice());
		yAxisValues.add(stock.getPrice_open());
		yAxisValues.add(stock.getDay_high());
		yAxisValues.add(stock.getDay_low());
		yAxisValues.add(stock.getFifty_two_week_high());
		yAxisValues.add(stock.getFifty_two_week_low());
		yAxisValues.add(stock.getDay_change());
		yAxisValues.add(stock.getChange_pct());
		yAxisValues.add(stock.getClose_yesterday());
//		yAxisValues.add(stock.getMarket_cap());
//		yAxisValues.add(stock.getVolume());
//		yAxisValues.add(stock.getShares());

		Chart<String, Double> chart = new Chart<>("bar", stock.getName(), ApplicationConstants.stockIndexText,
				yAxisValues);

		barChart.add(chart);

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
			notification.addMessage("Technical difficulties occured during processing the data");
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

		List<Double> yAxisValues = new ArrayList<>();
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

		barChart.add(chart);

	}
	
	public Either<Notification, List<MutualFundCompany>> getMutualfundSymbols() {
		
		List<MutualFundCompanyVO> companies = stockDAO.getAllMutualFundCompanies();
		return right(stockTranslator.mutualFundVOList2UIModelList(companies));
		
	}
	
}
