package com.weds.lordbond.model;

import com.google.gson.annotations.SerializedName;

public class GanericModel {
	
	@SerializedName("Status")
	private String status;
	
	@SerializedName("Message")
	private String message;
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
}
