package com.weds.lordbond.helper;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

public class PreferencesManager {
    public static final String PREFERENCES_FILE = "LordBondPreferenceFile";

    private static PreferencesManager sInstance;
    private static SharedPreferences mPref;

    private PreferencesManager(){

    }

    public static synchronized PreferencesManager getInstance(Context context)
    {
        if (sInstance == null)
        {
            mPref = context.getSharedPreferences(PREFERENCES_FILE, Context.MODE_MULTI_PROCESS);
            sInstance = new PreferencesManager();
        }
        return sInstance;
    }

    public void setStringValue(String key, String value)
    {
        mPref.edit()
                .putString(key, value)
                .apply();
    }

    public void setIntValue(String key, int value)
    {
        mPref.edit()
                .putInt(key, value)
                .commit();
    }

    public void setLongValue(String key, long value)
    {
        mPref.edit()
                .putLong(key, value)
                .commit();
    }

    public void setBooleanValue(String key, boolean value)
    {
        mPref.edit()
                .putBoolean(key, value)
                .commit();
    }

    public void setStringSet(String key, Set<String> value)
    {
        remove(key);
        mPref.edit()
                .putStringSet(key, value)
                .apply();
    }

    public long getLongValue(String key)
    {
        return mPref.getLong(key, 0);
    }

    public Set<String> getStringSet(String key)
    {
        return mPref.getStringSet(key, null);
    }

    public String getStringValue(String key)
    {
        return mPref.getString(key, "");
    }

    public int getIntValue(String key)
    {
        return mPref.getInt(key, 0);
    }

    public boolean getBooleanValue(String key)
    {
        return mPref.getBoolean(key, false);
    }

    public void remove(String key)
    {
        mPref.edit()
                .remove(key)
                .commit();
    }

    public boolean clear()
    {
        return mPref.edit()
                .clear()
                .commit();
    }
}
