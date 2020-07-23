package com.datadrizzle.entities;

import java.util.Map;

public abstract class AbstractConnnection {
	
	public Map<String,String> connectionParameters;

	public Map<String, String> getConnectionParameters() {
		return connectionParameters;
	}

	public void setConnectionParameters(Map<String, String> connectionParameters) {
		this.connectionParameters = connectionParameters;
	}

	@Override
	public String toString() {
		return "AbstractConnnection [connectionParameters=" + connectionParameters + "]";
	}
}
