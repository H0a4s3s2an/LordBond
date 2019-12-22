package com.weds.lordbond.model;

import com.google.gson.annotations.SerializedName;

public class Qualifications {
	
	@SerializedName("QualificationID")
	private int qualificationId;
	
	@SerializedName("Qualification")
	private String userQualification;
	
	public int getQualificationId() {
		return qualificationId;
	}
	
	public void setQualificationId(int qualificationId) {
		this.qualificationId = qualificationId;
	}
	
	public String getUserQualification() {
		return userQualification;
	}
	
	public void setUserQualification(String userQualification) {
		this.userQualification = userQualification;
	}
}
