package com.datadrizzle.share;

import java.util.List;

public class Response<T> {
	
//	List<String> notificationMsgs;//List contains error msgs
//	List<String> warningMsgs;//List contains warning alerts
//	List<String> successMsgs;//List contains operation success msgs
	List<String> message;
	T data;//A generic data which needs to be sent from server to front-end/client
	String httpStatus;//optional field for now we are not using this
	
	public Response(List<String> message, T data, String httpStatus) {
		super();
		this.message = message;
		this.data = data;
		this.httpStatus = httpStatus;
	}

	public List<String> getMessage() {
		return message;
	}

	public void setMessage(List<String> message) {
		this.message = message;
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

	@Override
	public String toString() {
		return "Response [message=" + message + ", data=" + data + ", httpStatus=" + httpStatus + "]";
	}
}
