package com.datadrizzle.poc;

import java.util.List;

import com.datadrizzle.entities.StockAndIndexRealTime;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Test {
	
	public static void main(String[] args) {
		String data = "[{\"day_change\":\"-1.02\",\"change_pct\":\"-0.65\",\"symbol\":\"AAPL\",\"stock_exchange_long\":\"NASDAQ Stock Exchange\",\"timezone\":\"EST\",\"price_open\":\"155.84\",\"fifty_two_week_low\":\"146.59\",\"close_yesterday\":\"157.17\",\"last_trade_time\":\"2018-12-27 16:00:01\",\"volume\":\"53117065\",\"shares\":\"4745398000\",\"day_high\":\"156.77\",\"market_cap\":\"740993868736\",\"price\":\"156.15\",\"name\":\"Apple Inc.\",\"timezone_name\":\"America/New_York\",\"stock_exchange_short\":\"NASDAQ\",\"currency\":\"USD\",\"day_low\":\"150.07\",\"fifty_two_week_high\":\"233.47\",\"gmt_offset\":\"-18000\"},{\"day_change\":\"-9.00\",\"change_pct\":\"-1.39\",\"symbol\":\"HSBA.L\",\"stock_exchange_long\":\"London Stock Exchange\",\"timezone\":\"GMT\",\"price_open\":\"652.40\",\"fifty_two_week_low\":\"596.40\",\"close_yesterday\":\"647.00\",\"last_trade_time\":\"2018-12-27 16:35:05\",\"volume\":\"19537426\",\"shares\":\"20034182675\",\"day_high\":\"653.18\",\"market_cap\":\"1278180839380\",\"price\":\"638.00\",\"name\":\"HSBC Holdings plc\",\"timezone_name\":\"Europe/London\",\"stock_exchange_short\":\"LSE\",\"currency\":\"GBX\",\"day_low\":\"634.70\",\"fifty_two_week_high\":\"798.60\",\"gmt_offset\":\"0\"},{\"day_change\":\"0.62\",\"change_pct\":\"0.62\",\"symbol\":\"MSFT\",\"stock_exchange_long\":\"NASDAQ Stock Exchange\",\"timezone\":\"EST\",\"price_open\":\"99.30\",\"fifty_two_week_low\":\"83.83\",\"close_yesterday\":\"100.56\",\"last_trade_time\":\"2018-12-27 16:00:01\",\"volume\":\"49498509\",\"shares\":\"7677512000\",\"day_high\":\"101.19\",\"market_cap\":\"776810666502\",\"price\":\"101.18\",\"name\":\"Microsoft Corporation\",\"timezone_name\":\"America/New_York\",\"stock_exchange_short\":\"NASDAQ\",\"currency\":\"USD\",\"day_low\":\"96.40\",\"fifty_two_week_high\":\"116.18\",\"gmt_offset\":\"-18000\"}]";
		
//		data = "[{\"symbol\":\"AAPL\", \"day_change\":\"-1.02\"},{\"symbol\":\"AAPL\", \"day_change\":\"-1.02\"},{\"symbol\":\"AAPL\", \"day_change\":\"-1.02\"}]";
		
//		data = "[{\"day_change\":\"-1.02\",\"change_pct\":\"-0.65\",\"symbol\":\"AAPL\",\"stock_exchange_long\":\"NASDAQ Stock Exchange\",\"timezone\":\"EST\",\"price_open\":\"155.84\",\"52_week_low\":\"146.59\",\"close_yesterday\":\"157.17\",\"last_trade_time\":\"2018-12-27 16:00:01\",\"volume\":\"53117065\",\"shares\":\"20034182675\",\"day_high\":\"653.18\",\"market_cap\":\"1278180839380\",\"name\":\"Microsoft Corporation\",\"timezone_name\":\"America/New_York\",\"stock_exchange_short\":\"NASDAQ\",\"currency\":\"USD\",\"day_low\":\"96.40\",\"52_week_high\":\"116.18\",\"gmt_offset\":\"-18000\"},{\"day_change\":\"-1.02\",\"change_pct\":\"-0.65\",\"symbol\":\"AAPL\",\"stock_exchange_long\":\"NASDAQ Stock Exchange\",\"timezone\":\"EST\",\"price_open\":\"155.84\",\"52_week_low\":\"146.59\",\"close_yesterday\":\"157.17\",\"last_trade_time\":\"2018-12-27 16:00:01\",\"volume\":\"53117065\",\"shares\":\"20034182675\",\"day_high\":\"653.18\",\"market_cap\":\"1278180839380\",\"name\":\"Microsoft Corporation\",\"timezone_name\":\"America/New_York\",\"stock_exchange_short\":\"NASDAQ\",\"currency\":\"USD\",\"day_low\":\"96.40\",\"52_week_high\":\"116.18\",\"gmt_offset\":\"-18000\"}]";
		
		Gson gson = new Gson();
		List<StockAndIndexRealTime> stockList = gson.fromJson(data, new TypeToken<List<StockAndIndexRealTime>>(){}.getType());
		
		System.out.println(stockList);
		
	}
	
}
