package com.weds.lordbond.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public abstract class BaseActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    abstract void onPermissonGranted(int permissionCode);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void showProgressDialog(String msg){
        if(progressDialog == null) progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(msg);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
    }
    
    protected void hideKeyboard(){
        try {
            InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
            View view = getCurrentFocus();
            if (view == null) {
                view = new View(this);
            }
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            
        } catch (Exception e) {
        }
    }

    protected void dismissProgressDialog(){
        if(progressDialog.isShowing()) progressDialog.dismiss();
    }

    protected void askForPermission(String[] permissions, int requestCode) {
        if (getGrantedPermissionsCount(permissions) == permissions.length) {
            onPermissonGranted(requestCode);
        } else {
            requestPermissions(requestCode, permissions);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void requestPermissions(int requestCode, @NonNull String[] permissions) {
        requestPermissions(permissions, requestCode);
    }

    public int getGrantedPermissionsCount(String[] permissions) {
        int grantedPermissionsCount = 0;
        for (String permission : permissions) {
            if (checkPermissions(permission)) {
                grantedPermissionsCount++;
            }
        }

        return grantedPermissionsCount;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults.length > 0 && getGrantedPermissionsCount(permissions) == permissions.length) {
            onPermissonGranted(requestCode);
        } else {
            Snackbar.make(findViewById(android.R.id.content), "Permission not granted", Snackbar.LENGTH_LONG).show();
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public boolean checkPermissions(String permission) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M || checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }
}

