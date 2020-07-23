package com.datadrizzle.entities;

import java.util.List;

public class RetensionJobEntity extends AbstractConnnection {
	List<String> tables;
	String retensionDate;
	
	public List<String> getTables() {
		return tables;
	}
	public void setTables(List<String> tables) {
		this.tables = tables;
	}
	public String getRetensionDate() {
		return retensionDate;
	}
	public void setRetensionDate(String retensionDate) {
		this.retensionDate = retensionDate;
	}
	
	@Override
	public String toString() {
		return "RetensionJob [tables=" + tables + ", retensionDate=" + retensionDate + "]";
	}
}
