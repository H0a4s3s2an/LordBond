package com.weds.lordbond.fragment;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.view.ViewGroup;

import androidx.fragment.app.DialogFragment;

public abstract class BaseDialogFragment extends DialogFragment {

    private ProgressDialog progressDialog;

    public BaseDialogFragment(){

    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
        }
    }

    protected void showProgressDialog(String msg){
        if(progressDialog == null) progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(msg);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
    }

    protected void dismissProgressDialog(){
        if(progressDialog != null && progressDialog.isShowing()) progressDialog.dismiss();
    }
}
