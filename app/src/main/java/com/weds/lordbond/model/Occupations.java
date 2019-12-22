package com.weds.lordbond.model;

import com.google.gson.annotations.SerializedName;

public class Occupations {
	
	@SerializedName("OccupationID")
	private int occupationId;
	
	@SerializedName("Occupation")
	private String userOccupation;
	
	public int getOccupationId() {
		return occupationId;
	}
	
	public void setOccupationId(int occupationId) {
		this.occupationId = occupationId;
	}
	
	public String getUserOccupation() {
		return userOccupation;
	}
	
	public void setUserOccupation(String userOccupation) {
		this.userOccupation = userOccupation;
	}
}
