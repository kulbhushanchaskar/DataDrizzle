package org.teiid.webui.share.beans;

import java.util.Map;

public class TeiidConnection {

	private Map<String, String> connectionParameters;

	public TeiidConnection() {

	}

	public TeiidConnection(Map<String, String> connectionParameters, String name) {
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
		TeiidConnection other = (TeiidConnection) obj;
		if (connectionParameters == null) {
			if (other.connectionParameters != null)
				return false;
		} else if (!connectionParameters.equals(other.connectionParameters))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TeiidConnection [connectionParameters=" + connectionParameters + "]";
	}

}
