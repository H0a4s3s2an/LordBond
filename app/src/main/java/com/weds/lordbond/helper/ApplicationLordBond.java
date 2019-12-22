package com.weds.lordbond.helper;

import android.app.Application;

public class ApplicationLordBond extends Application {
    public static PreferencesManager preferencesManager;
    public static ApplicationLordBond applicationLordBond;

    @Override
    public void onCreate() {
        super.onCreate();
        
        applicationLordBond = this;
        preferencesManager = PreferencesManager.getInstance(this);
    }
}
