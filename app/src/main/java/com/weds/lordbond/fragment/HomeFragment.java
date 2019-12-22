package com.weds.lordbond.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.weds.lordbond.R;
import com.weds.lordbond.activity.ProfileActivity;
import com.weds.lordbond.adapter.HomeAdapter;
import com.weds.lordbond.dataSource.LoginPresenter;
import com.weds.lordbond.model.SignUp;
import com.weds.lordbond.util.Constants;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment implements HomeAdapter.ItemClickListener,
        LoginPresenter.profileEventListener, View.OnClickListener {

    private RecyclerView recyclerView;
    private View rootView;
    private ImageView filterImg;
    private LoginPresenter loginPresenter;
    private ArrayList<SignUp> arrayList;

    public HomeFragment() {
        // Required Constructor
    }
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_home, container, false);
            initView();
        }

        return rootView;
    }

    public void initView() {
        filterImg = rootView.findViewById(R.id.filter_iv);
        recyclerView = rootView.findViewById(R.id.profile_rv);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.app_item_divider));
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
    
        filterImg.setOnClickListener(this);
        
        showProgressDialog("please wait...");
        loginPresenter = new LoginPresenter(this);
        loginPresenter.getProfileList();
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getActivity(), ProfileActivity.class);
        intent.putExtra(Constants.VIEW_PROFILE_CODE_KEY, arrayList.get(position).getFkUserId());
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        SearchFilterDialogFragment searchFilterDialogFragment = new SearchFilterDialogFragment();
        searchFilterDialogFragment.setTargetFragment(this, Constants.SEARCH_FILTER_CODE_KEY);
        searchFilterDialogFragment.show(getFragmentManager(), SearchFilterDialogFragment.class.getSimpleName());
    }
    
    @Override
    public void onSuccess(String response, String status, String extra) {
    	dismissProgressDialog();
    	arrayList = new Gson().fromJson(response, new TypeToken<List<SignUp>>(){
	    }.getType());
    	recyclerView.setAdapter(new HomeAdapter(arrayList, this));
    }
    
    @Override
    public void onFail(String errMsg, String status, String extra) {
    dismissProgressDialog();
    hideKeyboard();
    showSnackbar(getView(), errMsg);
    }
}

