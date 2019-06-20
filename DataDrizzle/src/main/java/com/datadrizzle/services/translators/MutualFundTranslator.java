package com.datadrizzle.services.translators;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.datadrizzle.entities.MutualFundHistoryData;

public class MutualFundTranslator {
	
	public static List<MutualFundHistoryData> mutualFundHistoryData(JSONObject mutualFundHistoryJSON) throws JSONException {
		Iterator<String> keys = mutualFundHistoryJSON.keys();
		List<MutualFundHistoryData> historyList = new ArrayList<>();
		while(keys.hasNext()) {
		    String key = keys.next();
		    if (mutualFundHistoryJSON.get(key) instanceof JSONObject) {
		    	String date = key;
		    	JSONObject mutualFundDataByDate = (JSONObject) mutualFundHistoryJSON.get(key);
		    	//{"open":"196.05","close":"198.45","high":"200.29","low":"195.21","volume":"26551004"}
		    	String open = mutualFundDataByDate.getString("open");
		    	String close = mutualFundDataByDate.getString("close");
		    	String high = mutualFundDataByDate.getString("high");
		    	String low = mutualFundDataByDate.getString("low");
		    	String volume = mutualFundDataByDate.getString("volume");
		    	
		    	MutualFundHistoryData mutualFundHistoryDataOFDate = new MutualFundHistoryData(date, open, close, high, low, volume);
		    	historyList.add(mutualFundHistoryDataOFDate);
		    }
		}
		return historyList;
	}

}
