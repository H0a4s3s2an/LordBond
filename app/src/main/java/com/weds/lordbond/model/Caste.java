package com.weds.lordbond.model;

import com.google.gson.annotations.SerializedName;

public class Caste {
	
	@SerializedName("CasteID")
	private int castId;
	
	@SerializedName("Caste")
	private String caste;
	
	public int getCastId() {
		return castId;
	}
	
	public void setCastId(int castId) {
		this.castId = castId;
	}
	
	public String getCaste() {
		return caste;
	}
	
	public void setCaste(String caste) {
		this.caste = caste;
	}
}
