package com.datadrizzle.share;

import java.util.Arrays;
import java.util.List;

public class ApplicationConstants {
	public static String teiidServerPropertiesFileName = "teiidServer.properties";
	public static String dozerBeanMappingXML = "dozerBeanMapping.xml";
	public static String worldTradingDataAPIToken = "Na6uxOw5jfkjsdfjkjsadfaRNI4y97S4I1252nbbdjsfkjsdlfjNLggeObFp7C9bDwTyeOpQ6Awj6LfxzmgVByV2ly";
	public static String stockAndRealTimeIndexAPI = "https://www.worldtradingdata.com/api/v1/stock";
	public static List<String> stockIndexText = Arrays.asList("price","price_open","day_high","day_low","fifty_two_week_high",
			"fifty_two_week_low", "day_change","change_pct","close_yesterday","market_cap","volume","shares");
	
}
