package com.weds.lordbond.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class LoginResponse {
    @SerializedName("UserID")
    private int userId;
    
    @SerializedName("Name")
    private String name;
    
    @SerializedName("Gender")
    private String gender;
    
    @SerializedName("DOB")
    private String dateOfBirth;
    
    @SerializedName("Mobile")
    private String mobile;
    
    @SerializedName("Age")
    private int age;
    
    @SerializedName("MaritalStatus")
    private String martialStatus;
    
    @SerializedName("FKCountryID")
    private int countryID;
    
    @SerializedName("UserName")
    private String userName;
    
    @SerializedName("Email")
    private String email;
    
    @SerializedName("Password")
    private String password;
    
    @SerializedName("ProfileFor")
    private String profileFor;
    
    @SerializedName("Country")
    private String country;
    
    @SerializedName("Language")
    private String language;
    
    @SerializedName("Qualification")
    private String qualification;
    
    @SerializedName("Religion")
    private String religion;
    
    @SerializedName("UserProfileID")
    private int userProfileId;
    
    @SerializedName("EmployedIn")
    private String employedIn;
    
    @SerializedName("Occupation")
    private String occupation;
    
    @SerializedName("Height")
    private String height;
    
    @SerializedName("ProfilePic")
    private String profileImage;
    
    @SerializedName("FKUserID")
    private int fkUserId;
    
    @SerializedName("FamilyStatus")
    private String familyStatus;
    
    @SerializedName("FamilyType")
    private String familyType;
    
    @SerializedName("FamilyValue")
    private String familyValue;
    
    @SerializedName("AnyDisabilities")
    private String disablility;
    
    @SerializedName("Complexation")
    private String complextion;
    
    @SerializedName("BloodGroup")
    private String bloodGroup;
    
    @SerializedName("AnnualIncome")
    private String annualIncome;
    
    @SerializedName("AboutYou")
    private String bio;
    
    @SerializedName("CreateDate")
    private String createDate;
    
    @SerializedName("CasteID")
    private int castId;
    
    @SerializedName("FKCasteID")
    private int fkCasteId;
    
    @SerializedName("SubCasteID")
    private int subCastId;
    
    @SerializedName("FKSubCasteID")
    private int fkSubCasteId;
    
    @SerializedName("CountryID")
    private int countryId;
    
    @SerializedName("LanguageID")
    private int languageId;
    
    @SerializedName("FKLanguageID")
    private int fkLanguageId;
    
    @SerializedName("OccupationID")
    private int occupationId;
    
    @SerializedName("FKOccupationID")
    private int fkOccupationId;
    
    @SerializedName("QualificationID")
    private int qualificationId;
    
    @SerializedName("FKQualificationID")
    private int fkQualificationId;
    
    @SerializedName("FKReligionID")
    private int fkReligionId;
    
    @SerializedName("ReligionID")
    private int religionId;
    
    @SerializedName("Caste")
    private String caste;
    
    public int getUserId() {
        return userId;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getGender() {
        return gender;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public String getDateOfBirth() {
        return dateOfBirth;
    }
    
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    
    public String getMobile() {
        return mobile;
    }
    
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    
    public int getCountryID() {
        return countryID;
    }
    
    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }
    
    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getProfileFor() {
        return profileFor;
    }
    
    public void setProfileFor(String profileFor) {
        this.profileFor = profileFor;
    }
    
    public String getCountry() {
        return country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
    
    public String getLanguage() {
        return language;
    }
    
    public void setLanguage(String language) {
        this.language = language;
    }
    
    public String getQualification() {
        return qualification;
    }
    
    public void setQualification(String qualification) {
        this.qualification = qualification;
    }
    
    public String getReligion() {
        return religion;
    }
    
    public void setReligion(String religion) {
        this.religion = religion;
    }
    
    public int getUserProfileId() {
        return userProfileId;
    }
    
    public void setUserProfileId(int userProfileId) {
        this.userProfileId = userProfileId;
    }
    
    public String getEmployedIn() {
        return employedIn;
    }
    
    public void setEmployedIn(String employedIn) {
        this.employedIn = employedIn;
    }
    
    public String getOccupation() {
        return occupation;
    }
    
    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }
    
    public String getHeight() {
        return height;
    }
    
    public void setHeight(String height) {
        this.height = height;
    }
    
    public String getProfileImage() {
        return profileImage;
    }
    
    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
    
    public int getFkUserId() {
        return fkUserId;
    }
    
    public void setFkUserId(int fkUserId) {
        this.fkUserId = fkUserId;
    }
    
    public String getFamilyStatus() {
        return familyStatus;
    }
    
    public void setFamilyStatus(String familyStatus) {
        this.familyStatus = familyStatus;
    }
    
    public String getFamilyType() {
        return familyType;
    }
    
    public void setFamilyType(String familyType) {
        this.familyType = familyType;
    }
    
    public String getFamilyValue() {
        return familyValue;
    }
    
    public void setFamilyValue(String familyValue) {
        this.familyValue = familyValue;
    }
    
    public String getDisablility() {
        return disablility;
    }
    
    public void setDisablility(String disablility) {
        this.disablility = disablility;
    }
    
    public String getComplextion() {
        return complextion;
    }
    
    public void setComplextion(String complextion) {
        this.complextion = complextion;
    }
    
    public String getBloodGroup() {
        return bloodGroup;
    }
    
    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }
    
    public String getAnnualIncome() {
        return annualIncome;
    }
    
    public void setAnnualIncome(String annualIncome) {
        this.annualIncome = annualIncome;
    }
    
    public String getBio() {
        return bio;
    }
    
    public void setBio(String bio) {
        this.bio = bio;
    }
    
    public String getCreateDate() {
        return createDate;
    }
    
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
    
    public int getCastId() {
        return castId;
    }
    
    public void setCastId(int castId) {
        this.castId = castId;
    }
    
    public int getFkCasteId() {
        return fkCasteId;
    }
    
    public void setFkCasteId(int fkCasteId) {
        this.fkCasteId = fkCasteId;
    }
    
    public int getSubCastId() {
        return subCastId;
    }
    
    public void setSubCastId(int subCastId) {
        this.subCastId = subCastId;
    }
    
    public int getFkSubCasteId() {
        return fkSubCasteId;
    }
    
    public void setFkSubCasteId(int fkSubCasteId) {
        this.fkSubCasteId = fkSubCasteId;
    }
    
    public int getCountryId() {
        return countryId;
    }
    
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }
    
    public int getLanguageId() {
        return languageId;
    }
    
    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }
    
    public int getFkLanguageId() {
        return fkLanguageId;
    }
    
    public void setFkLanguageId(int fkLanguageId) {
        this.fkLanguageId = fkLanguageId;
    }
    
    public int getOccupationId() {
        return occupationId;
    }
    
    public void setOccupationId(int occupationId) {
        this.occupationId = occupationId;
    }
    
    public int getFkOccupationId() {
        return fkOccupationId;
    }
    
    public void setFkOccupationId(int fkOccupationId) {
        this.fkOccupationId = fkOccupationId;
    }
    
    public int getQualificationId() {
        return qualificationId;
    }
    
    public void setQualificationId(int qualificationId) {
        this.qualificationId = qualificationId;
    }
    
    public int getFkQualificationId() {
        return fkQualificationId;
    }
    
    public void setFkQualificationId(int fkQualificationId) {
        this.fkQualificationId = fkQualificationId;
    }
    
    public int getFkReligionId() {
        return fkReligionId;
    }
    
    public void setFkReligionId(int fkReligionId) {
        this.fkReligionId = fkReligionId;
    }
    
    public int getReligionId() {
        return religionId;
    }
    
    public void setReligionId(int religionId) {
        this.religionId = religionId;
    }
    
    public String getCaste() {
        return caste;
    }
    
    public void setCaste(String caste) {
        this.caste = caste;
    }
    
    public int getAge() {
        return age;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
    
    public String getMartialStatus() {
        return martialStatus;
    }
    
    public void setMartialStatus(String martialStatus) {
        this.martialStatus = martialStatus;
    }
}
