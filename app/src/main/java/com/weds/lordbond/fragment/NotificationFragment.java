package com.weds.lordbond.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.weds.lordbond.R;

public class NotificationFragment extends BaseFragment {

    private View rootView;

    public NotificationFragment() {
        // Required Constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_notification, container, false);
            initView();
        }

        return rootView;
    }

    public void initView() {

    }
}
