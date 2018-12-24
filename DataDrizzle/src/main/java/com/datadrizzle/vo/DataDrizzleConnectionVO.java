package com.datadrizzle.vo;

import java.util.Map;

public class DataDrizzleConnectionVO {
	
	private Map<String,String> connectionParameters;

	public DataDrizzleConnectionVO(Map<String, String> connectionParameters) {
		super();
		this.connectionParameters = connectionParameters;
	}

	public Map<String, String> getConnectionParameters() {
		return connectionParameters;
	}

	public void setConnectionParameters(Map<String, String> connectionParameters) {
		this.connectionParameters = connectionParameters;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((connectionParameters == null) ? 0 : connectionParameters.hashCode());
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
		DataDrizzleConnectionVO other = (DataDrizzleConnectionVO) obj;
		if (connectionParameters == null) {
			if (other.connectionParameters != null)
				return false;
		} else if (!connectionParameters.equals(other.connectionParameters))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DataDrizzleConnectionVO [connectionParameters=" + connectionParameters + "]";
	}
}
