package com.weds.lordbond.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.weds.lordbond.R;
import com.weds.lordbond.activity.AuthActivity;
import com.weds.lordbond.dataSource.LoginPresenter;

public class ForgetPasswordFragment extends BaseFragment implements LoginPresenter.onEventClickLIstener,
        View.OnClickListener {

    private EditText passwordET, confirmPassworsET;
    private View rootView;
    private Button forgetPasswordBtn;
    private LoginPresenter loginPresenter;
    
    public ForgetPasswordFragment() {
        // Required Constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_forget_password, container, false);
            initView();
        }

        return rootView;
    }

    private void initView() {
        passwordET = rootView.findViewById(R.id.password_et);
        confirmPassworsET = rootView.findViewById(R.id.confirm_password_et);
        forgetPasswordBtn = rootView.findViewById(R.id.forget_password_btn);
        
        loginPresenter = new LoginPresenter(this);
        
        forgetPasswordBtn.setOnClickListener(this);
    }
    
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.forget_password_btn:
                doForgetPassword(loginPresenter.validateForgetPasswordInput(passwordET, confirmPassworsET));
                break;
        }
    }
    
    private void doForgetPassword(String errorMsg) {
        if (errorMsg.equalsIgnoreCase("")) {
            showProgressDialog("Loading...");
            loginPresenter.forgetPassword(passwordET);
        } else {
            hideKeyboard();
            showSnackbar(getView(), errorMsg);
        }
    }
    
    @Override
    public void onLoginSuccess(String successMsg) {
        dismissProgressDialog();
        Toast.makeText(getActivity(), successMsg, Toast.LENGTH_SHORT).show();
        ((AuthActivity) getActivity()).onFragmentChange(new LoginFragment(), false, LoginFragment.class.getSimpleName());
    }
    
    @Override
    public void onLoginFail(String errorMsg) {
        dismissProgressDialog();
        Toast.makeText(getActivity(), errorMsg, Toast.LENGTH_SHORT).show();
    }
}
