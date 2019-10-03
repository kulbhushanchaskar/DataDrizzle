package com.datadrizzle.share;

import java.util.ArrayList;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

public class Notification {
	
	List<String> errors;
	List<String> success;
	List<String> warnings;
	
	public Notification() {
		errors = new ArrayList<String>();
		success = new ArrayList<String>();
		warnings = new ArrayList<String>();
	}
	
	public void addErrorMessage(String msg) {
		errors.add(msg);
	}
	
	public void addSuccess(String msg) {
		success.add(msg);
	}
	
	public void addWarnings(String msg) {
		warnings.add(msg);
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	public List<String> getSuccess() {
		return success;
	}

	public void setSuccess(List<String> success) {
		this.success = success;
	}

	public List<String> getWarnings() {
		return warnings;
	}

	public void setWarnings(List<String> warnings) {
		this.warnings = warnings;
	}
	
	public boolean hasErrors() {
		return errors.size() > 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Notification other = (Notification) obj;
		if (errors == null) {
			if (other.errors != null)
				return false;
		} else if (!errors.equals(other.errors))
			return false;
		if (success == null) {
			if (other.success != null)
				return false;
		} else if (!success.equals(other.success))
			return false;
		if (warnings == null) {
			if (other.warnings != null)
				return false;
		} else if (!warnings.equals(other.warnings))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((errors == null) ? 0 : errors.hashCode());
		result = prime * result + ((success == null) ? 0 : success.hashCode());
		result = prime * result + ((warnings == null) ? 0 : warnings.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "Notification [errors=" + errors + ", success=" + success + ", warnings=" + warnings + "]";
	}
}
