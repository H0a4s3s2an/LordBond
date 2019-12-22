package com.weds.lordbond.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.weds.lordbond.R;
import com.weds.lordbond.helper.ApplicationLordBond;
import com.weds.lordbond.util.Constants;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        setContentView(R.layout.activity_splash);
    
        if (ApplicationLordBond.preferencesManager.getBooleanValue(Constants.IS_USERLOGGED_IN_KEY)) {
            startActivity(new Intent(this, DashBoradActivity.class));
            finish();
        } else {
            Thread myThread = new Thread() {
                @Override
                public void run() {
                    try {
                        sleep(3000);
                        startActivity(new Intent(SplashActivity.this, AuthActivity.class));
                        finish();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            myThread.start();
        }
    }
}
