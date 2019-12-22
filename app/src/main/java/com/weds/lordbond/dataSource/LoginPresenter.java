package com.weds.lordbond.dataSource;

import android.widget.EditText;

import com.google.gson.Gson;
import com.weds.lordbond.helper.ApplicationLordBond;
import com.weds.lordbond.model.LoginDataPresponse;
import com.weds.lordbond.model.LoginResponse;
import com.weds.lordbond.model.SignUp;
import com.weds.lordbond.model.CanLogin;
import com.weds.lordbond.model.UpdatePassword;
import com.weds.lordbond.network.APIClient;
import com.weds.lordbond.network.APIRoute;
import com.weds.lordbond.util.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter {
	
	private onEventClickLIstener eventListener;
	private profileEventListener profileEventListener;
	private APIRoute apiRoute;
	
	public LoginPresenter(onEventClickLIstener eventListener){
		this.eventListener = eventListener;
		apiRoute = APIClient.getAPIClient();
	}
	
	public LoginPresenter(profileEventListener profileEventListener) {
		this.profileEventListener = profileEventListener;
		apiRoute = APIClient.getAPIClient();
	}
	
	public void register(String userName, String email, String password, String number, String gender,
	                     String profileFor, String martialStatus) {
		final SignUp signUp = new SignUp();
		signUp.setName(userName);
		signUp.setGender(gender);
		signUp.setDateOfBirth("1997-11-03");
		signUp.setMobile(number);
		signUp.setCountryID(1);
		signUp.setUserName(userName);
		signUp.setEmail(email);
		signUp.setPassword(password);
		signUp.setProfileFor(profileFor);
		
		Call<LoginDataPresponse> call = apiRoute.register(signUp);
		call.enqueue(new Callback<LoginDataPresponse>() {
			@Override
			public void onResponse(Call<LoginDataPresponse> call, Response<LoginDataPresponse> response) {
				if (response.isSuccessful() && response.code() == 200) {
						ApplicationLordBond.preferencesManager.setStringValue(Constants.LOGGED_IN_USER_KEY,
								new Gson().toJson(signUp));
						ApplicationLordBond.preferencesManager.setIntValue(Constants.LOGGED_IN_USER_ID,
								response.body().getLoginResponse().getUserId());
						ApplicationLordBond.preferencesManager.setBooleanValue(Constants.IS_USERLOGGED_IN_KEY,
								true);
					eventListener.onLoginSuccess("user created successfully");
				} else {
					eventListener.onLoginFail("error");
				}
			}
			
			@Override
			public void onFailure(Call<LoginDataPresponse> call, Throwable t) {
				eventListener.onLoginFail(t.getMessage());
			}
		});
	}
	
	
	public void login(EditText emailET, EditText passwordET) {
		CanLogin log = new CanLogin();
		log.setUserName(emailET.getText().toString());
		log.setPassword(passwordET.getText().toString());
		
		Call<LoginDataPresponse> call = APIClient.getAPIClient().login(log);
		call.enqueue(new Callback<LoginDataPresponse>() {
			@Override
			public void onResponse(Call<LoginDataPresponse> call, Response<LoginDataPresponse> response) {
				if (response.body() != null && response.code() == 200) {
					ApplicationLordBond.preferencesManager.setStringValue(Constants.LOGGED_IN_USER_KEY,
							new Gson().toJson(response.body()));
					ApplicationLordBond.preferencesManager.setIntValue(Constants.LOGGED_IN_USER_ID,
							response.body().getLoginResponse().getUserId());
					ApplicationLordBond.preferencesManager.setBooleanValue(Constants.IS_USERLOGGED_IN_KEY,
							true);
					eventListener.onLoginSuccess("user Logged In");
				} else {
					eventListener.onLoginFail("error");
				}
			}
			
			@Override
			public void onFailure(Call<LoginDataPresponse> call, Throwable t) {
				eventListener.onLoginFail(t.getMessage());
			}
		});
	}
	
	
	public void forgetPassword(EditText passwordET) {
		
		Call<UpdatePassword> call = APIClient.getAPIClient().forgetPassword("8989764523", passwordET.getText().toString());
		call.enqueue(new Callback<UpdatePassword>() {
			@Override
			public void onResponse(Call<UpdatePassword> call, Response<UpdatePassword> response) {
				if (response.body() != null && response.code() == 200) {
					eventListener.onLoginSuccess("password updated");
				} else {
					eventListener.onLoginFail("error");
				}
			}
			
			@Override
			public void onFailure(Call<UpdatePassword> call, Throwable t) {
				eventListener.onLoginFail(t.getMessage());
			}
		});
	}
	
	
	public void getProfileList() {
		Call<List<SignUp>> call = APIClient.getAPIClient().getProfileList(ApplicationLordBond.preferencesManager.getIntValue(Constants.LOGGED_IN_USER_ID));
		call.enqueue(new Callback<List<SignUp>>() {
			@Override
			public void onResponse(Call<List<SignUp>> call, Response<List<SignUp>> response) {
				if (response.body() != null && response.code() == 200) {
					profileEventListener.onSuccess(new Gson().toJson(response.body()), response.message(), "");
				} else {
					profileEventListener.onFail(response.errorBody().toString(), response.message(), "");
				}
			}
			
			@Override
			public void onFailure(Call<List<SignUp>> call, Throwable t) {
				profileEventListener.onFail(t.getLocalizedMessage(), t.getMessage(), "");
			}
		});
	}
	
	public void editProfile() {
		Call<SignUp> call = APIClient.getAPIClient().editProfile(ApplicationLordBond.preferencesManager.getIntValue(Constants.LOGGED_IN_USER_ID));
		call.enqueue(new Callback<SignUp>() {
			@Override
			public void onResponse(Call<SignUp> call, Response<SignUp> response) {
				if (response.body() != null && response.code() == 200) {
					profileEventListener.onSuccess(new Gson().toJson(response.body()), response.message(), "");
				} else {
					profileEventListener.onFail(response.errorBody().toString(), response.message(), "");
				}
			}
			
			@Override
			public void onFailure(Call<SignUp> call, Throwable t) {
				profileEventListener.onFail(t.getLocalizedMessage(), t.getMessage(), "");
			}
		});
	}
	
	public void updateProfile(SignUp profileData) {
		Call<String> call = APIClient.getAPIClient().updateProfile(profileData);
		call.enqueue(new Callback<String>() {
			@Override
			public void onResponse(Call<String> call, Response<String> response) {
				if (response.body() != null && response.code() == 200) {
					profileEventListener.onSuccess(new Gson().toJson(response.body()), response.message(), Constants.PROFILE_UPDATE_CODE_KEY);
				} else {
					profileEventListener.onFail(response.errorBody().toString(), response.message(), "");
				}
			}
			
			@Override
			public void onFailure(Call<String> call, Throwable t) {
				profileEventListener.onFail(t.getLocalizedMessage(), t.getMessage(), "");
			}
		});
	}
	
	
	public void viewProfile(int profileId) {
		Call<LoginResponse> call = APIClient.getAPIClient().getProfileDetail(profileId);
		call.enqueue(new Callback<LoginResponse>() {
			@Override
			public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
				if (response.body() != null && response.code() == 200) {
					profileEventListener.onSuccess(new Gson().toJson(response.body()), response.message(), "");
				} else {
					profileEventListener.onFail(response.errorBody().toString(), response.message(), "");
				}
			}
			
			@Override
			public void onFailure(Call<LoginResponse> call, Throwable t) {
				profileEventListener.onFail(t.getLocalizedMessage(), t.getMessage(), "");
			}
		});
	}
	
	public String validateInputData(EditText userNameET, EditText emailTV, EditText passwordTV, EditText mobile,
	                                EditText genderET, EditText profileFor, EditText martialStatus) {
		if (userNameET.getText().toString().equalsIgnoreCase("")) {
			return "username should not be empty";
		} else if (emailTV.getText().toString().equalsIgnoreCase("")) {
			return "email should not be empty";
		} else if (passwordTV.getText().toString().equalsIgnoreCase("")) {
			return "password should not be empty";
		} else if (mobile.getText().toString().equalsIgnoreCase("")) {
			return "Enter Mobile Number";
		}
		else if (genderET.getText().toString().equalsIgnoreCase("")) {
			return "gender should not be empty";
		} else if (profileFor.getText().toString().equalsIgnoreCase("")) {
			return "select profile for from list.";
		} else if (martialStatus.getText().toString().equalsIgnoreCase("")) {
			return " please select martial status";
		}
		
		return "";
	}
	
	public String validateLoginInput(EditText emailET, EditText passwordET) {
		if (emailET.getText().toString().equalsIgnoreCase("")) {
			return "email should not be empty";
		} else if (passwordET.getText().toString().equalsIgnoreCase("")) {
			return "password should not be empty";
		}
		
		return "";
	}
	
	public String validateForgetPasswordInput(EditText passwordET, EditText confirmPassworsET) {
		if (! passwordET.getText().toString().equalsIgnoreCase(confirmPassworsET.getText().toString())) {
			return "Password should match";
		}
		
		return "";
	}
	
	public interface onEventClickLIstener{
		void onLoginSuccess(String successMsg);
		void onLoginFail(String errorMsg);
	}
	
	public interface profileEventListener {
		void onSuccess(String success, String status, String extra);
		void onFail(String errMsg, String status, String extra);
	}
}
