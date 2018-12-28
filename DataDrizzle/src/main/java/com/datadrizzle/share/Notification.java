package com.datadrizzle.share;

import java.util.ArrayList;
import java.util.List;

public class Notification {
	
	List<String> notificationMessages;
	//List<String> successMsgs
	//List<String> errorMsgs
	//List<String> warningMsgs
	
	public Notification() {
		notificationMessages = new ArrayList<String>();
	}
	
	public void addMessage(String msg) {
		notificationMessages.add(msg);
	}

	public List<String> getNotificationMessages() {
		return notificationMessages;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((notificationMessages == null) ? 0 : notificationMessages.hashCode());
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
		Notification other = (Notification) obj;
		if (notificationMessages == null) {
			if (other.notificationMessages != null)
				return false;
		} else if (!notificationMessages.equals(other.notificationMessages))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Notification [notificationMessages=" + notificationMessages + "]";
	}
}
