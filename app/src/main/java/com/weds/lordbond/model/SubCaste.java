package com.weds.lordbond.model;

import com.google.gson.annotations.SerializedName;

public class SubCaste {
	
	@SerializedName("SubCasteID")
	private int subCastId;
	
	@SerializedName("CasteID")
	private int castId;
	
	@SerializedName("SubCaste")
	private String subCast;
	
	public int getSubCastId() {
		return subCastId;
	}
	
	public void setSubCastId(int subCastId) {
		this.subCastId = subCastId;
	}
	
	public int getCastId() {
		return castId;
	}
	
	public void setCastId(int castId) {
		this.castId = castId;
	}
	
	public String getSubCast() {
		return subCast;
	}
	
	public void setSubCast(String subCast) {
		this.subCast = subCast;
	}
}
