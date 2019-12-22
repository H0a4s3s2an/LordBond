package com.weds.lordbond.model;

import com.google.gson.annotations.SerializedName;

public class LoginDataPresponse extends GanericModel {

    @SerializedName("SignUpViewModel")
    private LoginResponse loginResponse;

    public LoginResponse getLoginResponse() {
        return loginResponse;
    }

    public void setLoginResponse(LoginResponse loginResponse) {
        this.loginResponse = loginResponse;
    }
}
