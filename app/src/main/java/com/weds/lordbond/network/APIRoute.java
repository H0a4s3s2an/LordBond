package com.weds.lordbond.network;

import com.weds.lordbond.model.LoginDataPresponse;
import com.weds.lordbond.model.LoginResponse;
import com.weds.lordbond.model.SignUp;
import com.weds.lordbond.model.CanLogin;
import com.weds.lordbond.model.UpdatePassword;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface APIRoute {
	
	@POST("User/SignUp")
	Call<LoginDataPresponse> register(@Body SignUp signUp);
	
	@POST("User/CanLogin")
	Call<LoginDataPresponse> login(@Body  CanLogin log);
	
	@PUT("User/UpdatePassword")
	Call<UpdatePassword> forgetPassword(@Query("number") String number, @Query("password") String password);
	
	@GET("User/GetUsersProfiles")
	Call<List<SignUp>> getProfileList(@Query("userId") int intValue);
	
	@GET("User/GetUserProfileEdit")
	Call<SignUp> editProfile(@Query("userId") int intValue);
	
	@GET("User/GetUserProfileDetail")
	Call<LoginResponse> getProfileDetail(@Query("id") int id);
	
	@POST("User/AddUpdateUserProfile")
	Call<String> updateProfile(@Body SignUp signUp);
}
