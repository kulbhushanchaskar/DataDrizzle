package com.datadrizzle.entities;

import java.util.List;
import java.util.Map;

public class SearchForm extends AbstractConnnection {
	
	List<String> tables;
	String searchFromDate;
	String searchToDate;
	
	public Map<String, String> getConnectionParameters() {
		return connectionParameters;
	}

	public void setConnectionParameters(Map<String, String> connectionParameters) {
		this.connectionParameters = connectionParameters;
	}

	public List<String> getTables() {
		return tables;
	}

	public void setTables(List<String> tables) {
		this.tables = tables;
	}

	public String getSearchFromDate() {
		return searchFromDate;
	}

	public void setSearchFromDate(String searchFromDate) {
		this.searchFromDate = searchFromDate;
	}
	
	public String getSearchToDate() {
		return searchToDate;
	}

	public void setSearchToDate(String searchToDate) {
		this.searchToDate = searchToDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((searchFromDate == null) ? 0 : searchFromDate.hashCode());
		result = prime * result + ((searchToDate == null) ? 0 : searchToDate.hashCode());
		result = prime * result + ((tables == null) ? 0 : tables.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SearchForm other = (SearchForm) obj;
		if (searchFromDate == null) {
			if (other.searchFromDate != null)
				return false;
		} else if (!searchFromDate.equals(other.searchFromDate))
			return false;
		if (searchToDate == null) {
			if (other.searchToDate != null)
				return false;
		} else if (!searchToDate.equals(other.searchToDate))
			return false;
		if (tables == null) {
			if (other.tables != null)
				return false;
		} else if (!tables.equals(other.tables))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SearchForm [tables=" + tables + ", searchFromDate=" + searchFromDate + ", searchToDate=" + searchToDate
				+ ", connectionParameters=" + connectionParameters + "]";
	}

}
