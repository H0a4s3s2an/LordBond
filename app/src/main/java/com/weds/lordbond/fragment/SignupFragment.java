package com.weds.lordbond.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.weds.lordbond.R;
import com.weds.lordbond.activity.AuthActivity;
import com.weds.lordbond.activity.DashBoradActivity;
import com.weds.lordbond.dataSource.LoginPresenter;
import com.weds.lordbond.util.Constants;
import com.weds.lordbond.util.Utils;

public class SignupFragment extends BaseFragment implements  LoginPresenter.onEventClickLIstener,
        View.OnClickListener {

    private EditText userNameET, emailTV, passwordTV, mobileET, mProfileET, countryET,
    genderET, martialStatusET;
    private TextView doLoginTv;
    private Button signupBtn;
    private View rootView;
    private BaseFragment.FragmentChangeListener fragmentChangeListener;
    private LoginPresenter loginPresenter;
    private String[] martialStatusList;
    private String[] profileForList;
    private String[] genderSelectionList;

    public SignupFragment() {
        // Required Constructor
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        try {
            fragmentChangeListener = (BaseFragment.FragmentChangeListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement FragmentChangeListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_signup, container, false);
            initView();
        }

        return rootView;
    }

    private void initView() {
        signupBtn = rootView.findViewById(R.id.signup_btn);
        doLoginTv = rootView.findViewById(R.id.do_sign_in_tv);
        userNameET = rootView.findViewById(R.id.name_et);
        emailTV = rootView.findViewById(R.id.email_et);
        passwordTV = rootView.findViewById(R.id.password_et);
        mobileET = rootView.findViewById(R.id.mobile_number_et);
        countryET = rootView.findViewById(R.id.country_et);
        mProfileET = rootView.findViewById(R.id.profile_et);
        genderET = rootView.findViewById(R.id.gender_et);
        martialStatusET = rootView.findViewById(R.id.martial_status_et);
        
        countryET.setText(R.string.living_contry);
        countryET.setEnabled(false);
        
        mProfileET.setOnClickListener(this);
        martialStatusET.setOnClickListener(this);
        genderET.setOnClickListener(this);
        signupBtn.setOnClickListener(this);
        doLoginTv.setOnClickListener(this);
        
        martialStatusList = new String[] {
          getString(R.string.never_married),
          getString(R.string.widowed),
          getString(R.string.divorced),
          getString(R.string.divorce_awaiting)
        };
    
        profileForList = new String[] {
                getString(R.string.profile_for_seld),
                getString(R.string.profile_for_daughter),
                getString(R.string.profile_for_son)
        };
        
        genderSelectionList = new String[] {
          getString(R.string.male),
          getString(R.string.female)
        };
        
        loginPresenter = new LoginPresenter(this);
    }
    
    private void validateForm(String errorMsg) {
        if (errorMsg.equalsIgnoreCase("")) {
            // Not using Parcelable object
            CodeVerificationFragment codeVerificationFragment = new CodeVerificationFragment();
            Bundle bundle = new Bundle();
            bundle.putString(Constants.MOBILE_NUMBER_CODE_KEY, mobileET.getText().toString().trim());
            bundle.putString(Constants.USER_NAME_CODE_KEY, userNameET.getText().toString().trim());
            bundle.putString(Constants.USER_EMAIL_CODE_KEY, emailTV.getText().toString().trim());
            bundle.putString(Constants.USER_PASSWORD_CODE_KEY, passwordTV.getText().toString().trim());
            bundle.putString(Constants.PROFILE_FOR_CODE_KEY, mProfileET.getText().toString().trim());
            bundle.putString(Constants.GENDER_CODE_KEY, genderET.getText().toString().trim());
            bundle.putString(Constants.MARTIAL_STAUS_CODE_KEY, martialStatusET.getText().toString().trim());
            codeVerificationFragment.setArguments(bundle);
            fragmentChangeListener.onFragmentChange(codeVerificationFragment, false,
                    CodeVerificationFragment.class.getSimpleName());
        } else {
            hideKeyboard();
            showSnackbar(getView(), errorMsg);
        }
    }
    
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.martial_status_et:
                hideKeyboard();
                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity(), R.style.MyAlertDialogTheme);
                dialog.setIcon(R.mipmap.ic_launcher);
                dialog.setTitle(getString(R.string.martial_status));
                dialog.setSingleChoiceItems(martialStatusList, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        martialStatusET.setText(martialStatusList[which]);
                        dialog.dismiss();
                    }
                });
                dialog.create().show();
                break;
            case R.id.gender_et:
                hideKeyboard();
                AlertDialog.Builder dialogGender = new AlertDialog.Builder(getActivity(), R.style.MyAlertDialogTheme);
                dialogGender.setIcon(R.mipmap.ic_launcher);
                dialogGender.setTitle(getString(R.string.gender));
                dialogGender.setSingleChoiceItems(genderSelectionList, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        genderET.setText(genderSelectionList[which]);
                        dialog.dismiss();
                    }
                });
                dialogGender.create().show();
                break;
            case R.id.profile_et:
                hideKeyboard();
                AlertDialog.Builder dialogProfile = new AlertDialog.Builder(getActivity(), R.style.MyAlertDialogTheme);
                dialogProfile.setIcon(R.mipmap.ic_launcher);
                dialogProfile.setTitle(getString(R.string.matrimony_profile));
                dialogProfile.setSingleChoiceItems(profileForList, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mProfileET.setText(profileForList[which]);
                        dialog.dismiss();
                    }
                });
                dialogProfile.create().show();
                break;
            case R.id.signup_btn:
                validateForm(loginPresenter.validateInputData(userNameET, emailTV, passwordTV, mobileET, genderET,
                        mProfileET, martialStatusET));
                break;
            case R.id.do_sign_in_tv:
                fragmentChangeListener.onFragmentChange(new LoginFragment(), false, LoginFragment.class.getSimpleName());
        }
    }
    
    @Override
    public void onLoginSuccess(String msg) {
    }
    
    @Override
    public void onLoginFail(String errorMsg) {
    }
}
