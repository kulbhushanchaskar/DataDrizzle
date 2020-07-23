package com.datadrizzle.entities;

import java.util.List;
import java.util.Map;

public class ArchiveJob extends AbstractConnnection {
	
//	private Map<String,String> connectionParameters;
	private List<String> tables;
	
	public ArchiveJob() {
		super();
	}

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((connectionParameters == null) ? 0 : connectionParameters.hashCode());
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
		ArchiveJob other = (ArchiveJob) obj;
		if (connectionParameters == null) {
			if (other.connectionParameters != null)
				return false;
		} else if (!connectionParameters.equals(other.connectionParameters))
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
		return "ArchiveJob [connectionParameters=" + connectionParameters + ", tables=" + tables + "]";
	}
}
