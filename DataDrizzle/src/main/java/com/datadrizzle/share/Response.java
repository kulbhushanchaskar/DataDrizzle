package com.datadrizzle.share;

import lombok.EqualsAndHashCode;
import lombok.ToString;


@EqualsAndHashCode
@ToString
public class Response<T> {
	
	Notification notifications;
	T data;//A generic data filed which needs to be sent from server to front-end/client
	String httpStatus;//optional field for now we are not using this
	
	public Response(Notification notifications, T data, String httpStatus) {
		super();
		this.notifications = notifications;
		this.data = data;
		this.httpStatus = httpStatus;
	}

	public Notification getNotifications() {
		return notifications;
	}

	public void setNotifications(Notification notifications) {
		this.notifications = notifications;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(String httpStatus) {
		this.httpStatus = httpStatus;
	}
	
	
}
