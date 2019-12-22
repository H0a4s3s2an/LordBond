package com.weds.lordbond.fragment;

import android.content.Context;
import android.content.Intent;
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

public class LoginFragment extends BaseFragment implements LoginPresenter.onEventClickLIstener,
        View.OnClickListener {
    
    private EditText emailET, passwordET;
    private View rootView;
    private Button loginBtn;
    private TextView forgetPasswordTv, signupTv;
    private BaseFragment.FragmentChangeListener fragmentChangeListener;
    private LoginPresenter loginPresenter;
    
    public LoginFragment() {
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
            rootView = inflater.inflate(R.layout.fragment_login, container, false);
            initView();
        }

        return rootView;
    }

    private void initView() {
        loginBtn = rootView.findViewById(R.id.login_btn);
        forgetPasswordTv = rootView.findViewById(R.id.forget_password_tv);
        signupTv = rootView.findViewById(R.id.create_sign_up_tv);
        emailET = rootView.findViewById(R.id.email_et);
        passwordET = rootView.findViewById(R.id.password_et);
        
        loginPresenter = new LoginPresenter(this);

        loginBtn.setOnClickListener(this);
        forgetPasswordTv.setOnClickListener(this);
        signupTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_btn:
                validateLogin(loginPresenter.validateLoginInput(emailET, passwordET));
                break;
            case R.id.forget_password_tv:
                fragmentChangeListener.onFragmentChange(new ForgetPasswordFragment(), true, ForgetPasswordFragment.class.getSimpleName());
                break;
            case R.id.create_sign_up_tv:
                fragmentChangeListener.onFragmentChange(new SignupFragment(), true, SignupFragment.class.getSimpleName());
                break;
                default:
                    break;

        }
    }
    
    private void validateLogin(String errorMsg) {
        if (errorMsg.equalsIgnoreCase("")) {
            hideKeyboard();
            showProgressDialog("Loading...");
            loginPresenter.login(emailET, passwordET);
        } else {
            hideKeyboard();
            showSnackbar(getView(), errorMsg);
        }
    }
    
    @Override
    public void onLoginSuccess(String successMsg) {
        dismissProgressDialog();
        Toast.makeText(getActivity(), successMsg, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getActivity(), DashBoradActivity.class));
        getActivity().finish();
    }
    
    @Override
    public void onLoginFail(String errorMsg) {
        dismissProgressDialog();
        Toast.makeText(getActivity(), errorMsg, Toast.LENGTH_SHORT).show();
    }
}
