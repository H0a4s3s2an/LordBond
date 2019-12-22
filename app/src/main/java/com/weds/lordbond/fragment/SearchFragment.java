package com.weds.lordbond.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.weds.lordbond.R;
import com.weds.lordbond.util.Constants;

public class SearchFragment extends BaseFragment {

    private View rootView;

    public SearchFragment() {
        // Required Constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_search, container, false);
            initView();
        }

        return rootView;
    }

    private void initView() {

    }
}
