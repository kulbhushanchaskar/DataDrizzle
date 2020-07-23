package com.datadrizzle.entities;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class TableRow {

	
	HashMap<String, String> rowMap = new LinkedHashMap<String,String>();//<key = titile, value =value>
	
	public void put(String key, String value) {
		rowMap.put(key, value);
	}
	
	public String get(String key) {
		return this.rowMap.get(key);
	}

	public HashMap<String, String> getRowMap() {
		return rowMap;
	}

	@Override
	public String toString() {
		return "Row [rowMap=" + rowMap + "]";
	}
	
}
