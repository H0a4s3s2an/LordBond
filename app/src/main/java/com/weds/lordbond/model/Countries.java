package com.weds.lordbond.model;

import com.google.gson.annotations.SerializedName;

public class Countries {
	
	@SerializedName("CountryID")
	private int countryId;
	
	@SerializedName("Country")
	private String country;
	
	public int getCountryId() {
		return countryId;
	}
	
	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
}
