package com.irecover.exception;

import java.util.Date;
import java.util.List;

public class ErrorDetails {

	private List<Object> data;
	private Date timestamp;
	private String message;
	private String details;

	public ErrorDetails(Date timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}

	public ErrorDetails(List<Object> data, Date timestamp, String message, String details) {
		super();
		this.data = data;
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}

	public List<Object> getData() {
		return data;
	}

	public void setData(List<Object> data) {
		this.data = data;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}

}
