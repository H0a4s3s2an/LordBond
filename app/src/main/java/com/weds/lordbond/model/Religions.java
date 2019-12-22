package com.weds.lordbond.model;

import com.google.gson.annotations.SerializedName;

public class Religions {
	
	@SerializedName("ReligionID")
	private int religionId;
	
	@SerializedName("ReligionName")
	private String religionName;
	
	public int getReligionId() {
		return religionId;
	}
	
	public void setReligionId(int religionId) {
		this.religionId = religionId;
	}
	
	public String getReligionName() {
		return religionName;
	}
	
	public void setReligionName(String religionName) {
		this.religionName = religionName;
	}
}
