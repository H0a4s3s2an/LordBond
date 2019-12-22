package com.weds.lordbond.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.weds.lordbond.R;

public class SearchFilterDialogFragment extends BaseDialogFragment implements View.OnClickListener {

   private ImageView cancelBtn;
    private View rootView;

    public SearchFilterDialogFragment() {
        // Required Constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_search_filter_dialog, container, false);
            initView();
        }

        return rootView;
    }

    public void initView() {
        cancelBtn = rootView.findViewById(R.id.cancle_btn);

        cancelBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancle_btn:
                dismiss();
                break;
        }
    }
}
