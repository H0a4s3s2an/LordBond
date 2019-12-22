package com.weds.lordbond.fragment;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.os.Build;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

public class BaseFragment extends Fragment {

    private ProgressDialog progressDialog;
    private PermissionListener permissionListener;


    public BaseFragment() {
    }

    public interface FragmentChangeListener{
        void onFragmentChange(Fragment fragment, boolean addToBackStack, String fragmentTag);
    }

    protected void showSnackbar(View view, String msg){
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show();
    }

    protected void hideKeyboard(){
        try {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
            View view = getActivity().getCurrentFocus();
            if (view == null) {
                view = new View(getActivity());
            }
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

        } catch (Exception e) {
        }
    }

    protected void showProgressDialog(String msg){
        if(getActivity() != null) {
            if (progressDialog == null) progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage(msg);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
        }
    }

    protected void dismissProgressDialog(){
        if(progressDialog != null && progressDialog.isShowing()) progressDialog.dismiss();
    }

    protected void askForPermission(String[] permissions, int requestCode) {
        if (getGrantedPermissionsCount(permissions) == permissions.length) {
            if(permissionListener != null) permissionListener.onPermissonGranted(requestCode);
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
            if(permissionListener != null) permissionListener.onPermissonGranted(requestCode);
        } else {
            Snackbar.make(getView(), "Permission not granted", Snackbar.LENGTH_LONG).show();
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public boolean checkPermissions(String permission) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M || getActivity().checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }

    public interface PermissionListener{
        public void onPermissonGranted(int requestCode);
    }

    public void setPermissionListener(PermissionListener permissionListener) {
        this.permissionListener = permissionListener;
    }


}

