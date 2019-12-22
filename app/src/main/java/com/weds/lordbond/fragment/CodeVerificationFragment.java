package com.weds.lordbond.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.weds.lordbond.R;
import com.weds.lordbond.activity.DashBoradActivity;
import com.weds.lordbond.dataSource.LoginPresenter;
import com.weds.lordbond.util.Constants;

import java.util.concurrent.TimeUnit;

public class CodeVerificationFragment extends BaseFragment implements LoginPresenter.onEventClickLIstener,
        View.OnClickListener {

    private View rootView;
    private Button confirmBtn;
    private String mVerificationId;
    private EditText verificationCodeOne, verificationCodeTwo, verificationCodeThree,
            verificationCodeFourth, verificationCodeFifth, verificationCodeSixth;
    private BaseFragment.FragmentChangeListener fragmentChangeListener;
    private LoginPresenter loginPresenter;
    private String userName, email, password, phoneNumber, martialStatus, profileFor, gender;
    
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userName = getArguments().getString(Constants.USER_NAME_CODE_KEY);
            email = getArguments().getString(Constants.USER_EMAIL_CODE_KEY);
            password = getArguments().getString(Constants.USER_PASSWORD_CODE_KEY);
            phoneNumber = getArguments().getString(Constants.MOBILE_NUMBER_CODE_KEY);
            martialStatus = getArguments().getString(Constants.MARTIAL_STAUS_CODE_KEY);
            profileFor = getArguments().getString(Constants.PROFILE_FOR_CODE_KEY);
            gender = getArguments().getString(Constants.GENDER_CODE_KEY);
        }
    
        sendVerificationCode(phoneNumber);
    }
    
    public CodeVerificationFragment() {
        // Default Constructor
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
            rootView = inflater.inflate(R.layout.fragment_codeverification, container, false);
            initView();
        }

        return rootView;
    }

    private void initView() {
        confirmBtn = rootView.findViewById(R.id.confirm_btn);
        verificationCodeOne = rootView.findViewById(R.id.first_number_et);
        verificationCodeTwo = rootView.findViewById(R.id.second_number_et);
        verificationCodeThree = rootView.findViewById(R.id.third_number_et);
        verificationCodeFourth = rootView.findViewById(R.id.fourth_number_et);
        verificationCodeFifth = rootView.findViewById(R.id.fifth_number_et);
        verificationCodeSixth = rootView.findViewById(R.id.sixth_number_et);
        
        confirmBtn.setOnClickListener(this);
        loginPresenter = new LoginPresenter(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.confirm_btn:
                verifyCode();
                break;
        }
    }
    
    //the method is sending verification code
    //the country id is concatenated
    //you can take the country id as user input as well
    private void sendVerificationCode(String mobile) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(mobile, 60, TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallbacks);
    }
    
    private void verifyCode() {
        String code = verificationCodeOne.getText().toString().concat(verificationCodeTwo.getText().toString())
                .concat(verificationCodeThree.getText().toString()).concat(verificationCodeFourth.getText().toString())
                .concat(verificationCodeFifth.getText().toString()).concat(verificationCodeSixth.getText().toString());
        if(verificationCodeOne.getText().toString().isEmpty() || verificationCodeTwo.getText().toString().isEmpty()
                || verificationCodeThree.getText().toString().isEmpty() || verificationCodeFourth.getText().toString().isEmpty() ||
                verificationCodeFifth.getText().toString().isEmpty() || verificationCodeSixth.getText().toString().isEmpty()) {
            showSnackbar(getView(), "Enter valid Code ...");
        } else {
            verifyVerificationCode(code);
        }
    }
    
    //the callback to detect the verification status
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            
            //Getting the code sent by SMS
            String code = phoneAuthCredential.getSmsCode();
            
            //sometime the code is not detected automatically
            //in this case the code will be null
            //so user has to manually enter the code
            if (code != null) {
                verificationCodeOne.setText(code.substring(0, 1));
                verificationCodeTwo.setText(code.substring(1, 2));
                verificationCodeThree.setText(code.substring(2, 3));
                verificationCodeFourth.setText(code.substring(3, 4));
                verificationCodeFifth.setText(code.substring(4, 5));
                verificationCodeSixth.setText(code.substring(5, 6));
                
                verifyVerificationCode(code);
            }
        }
        
        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        
        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            
            //storing the verification id that is sent to the user
            mVerificationId = s;
        }
    };
    
    private void verifyVerificationCode(String code) {
        //creating the credential
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, code);
        showProgressDialog("Loading...");
        loginPresenter.register(userName, email, password, phoneNumber, gender, profileFor, martialStatus);
        //      signing the user
//        signInWithPhoneAuthCredential(credential);
    }
    
    @Override
    public void onLoginSuccess(String msg) {
        dismissProgressDialog();
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getActivity(), DashBoradActivity.class));
        getActivity().finish();
    }
    
    @Override
    public void onLoginFail(String errorMsg) {
        dismissProgressDialog();
        Toast.makeText(getActivity(), errorMsg, Toast.LENGTH_SHORT).show();
    }
}