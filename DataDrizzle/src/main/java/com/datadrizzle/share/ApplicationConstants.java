package com.datadrizzle.share;

import java.util.Arrays;
import java.util.List;

public class ApplicationConstants {
	public static String teiidServerPropertiesFileName = "teiidServer.properties";
	public static String dozerBeanMappingXML = "dozerBeanMapping.xml";
	public static String worldTradingDataAPIToken = "Na6uxOw5RNI4y97S4InbbNLggeObFp7C9bDwTyeOpQ6Awj6LfxzmgVByV2ly";
	public static String stockAndRealTimeIndexAPI = "https://www.worldtradingdata.com/api/v1/stock";
	public static List<String> stockIndexText = Arrays.asList("price","price_open","day_high","day_low","fifty_two_week_high",
			"fifty_two_week_low", "day_change","change_pct","close_yesterday","market_cap","volume","shares");
	
	
			
			/*"data":[{"symbol":"AAAAX","name":"DWS RREEF Real Assets Fund - Class A","price":"9.61",
				"close_yesterday":"9.61","return_ytd":"1.42",
				"net_assets":"98267850","change_asset_value":"0.05",
				"change_pct":"0.52","yield_pct":"1.39","return_day":"0.52",
				"return_1week":"-0.41","return_4week":"-0.66","return_13week":"3.36",
				"return_52week":"8.41","return_156week":"3.97","return_260week":"2.76",
				"income_dividend":"0.09","income_dividend_date":"2018-06-22 16:00:00",
				"capital_gain":"0.05","expense_ratio":"1.22"}]*/
	
	public static List<String> mutualFundIndexText = Arrays.asList("price","close_yesterday","return_ytd",
			/*"net_assets",*/"change_asset_value",
			"change_pct", "yield_pct","change_pct","return_day","return_1week","return_4week","return_13week",
			"return_52week","return_156week","return_260week","income_dividend","income_dividend_date","capital_gain",
			"expense_ratio");
	
	public static String mutualFundRealTimeIndexAPI = "https://www.worldtradingdata.com/api/v1/mutualfund";
	
	//DWS RREEF Real Assets Fund - Class A
	//Aberdeen Income Builder Fund Class A
	//Thrivent Large Cap Growth Fund Class A
	public static List<String> MUTUAL_FUND_COMPANY_SYMBOLS = Arrays.asList(new String[]{"YI","PIH","PIHPP","TURN"}); 
}
