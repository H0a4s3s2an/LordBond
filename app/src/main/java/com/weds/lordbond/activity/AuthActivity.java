package com.weds.lordbond.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.weds.lordbond.R;
import com.weds.lordbond.fragment.BaseFragment;
import com.weds.lordbond.fragment.LoginFragment;

public class AuthActivity extends BaseActivity implements BaseFragment.FragmentChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        onFragmentChange(new LoginFragment(), false, LoginFragment.class.getSimpleName());
    }

    @Override
    void onPermissonGranted(int permissionCode) {

    }

    @Override
    public void onFragmentChange(Fragment fragment, boolean isBackStackAdded, String fragmentTag) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.main_container, fragment);
        if (isBackStackAdded) {
            ft.addToBackStack(fragmentTag);
        }
        ft.commitAllowingStateLoss();
    }
}
