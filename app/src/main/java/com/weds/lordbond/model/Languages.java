package com.weds.lordbond.model;

import com.google.gson.annotations.SerializedName;

public class Languages {
	
	@SerializedName("LanguageID")
	private int languageId;
	
	@SerializedName("Language")
	private String language;
	
	public int getLanguageId() {
		return languageId;
	}
	
	public void setLanguageId(int languageId) {
		this.languageId = languageId;
	}
	
	public String getLanguage() {
		return language;
	}
	
	public void setLanguage(String language) {
		this.language = language;
	}
}
