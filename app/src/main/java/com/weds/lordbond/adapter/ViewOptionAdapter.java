package com.weds.lordbond.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class ViewOptionAdapter extends FragmentStatePagerAdapter {

    private ArrayList<Fragment> fragmentArray;

    public ViewOptionAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
        super(fm);
        fragmentArray = fragments;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentArray.get(position);
    }

    @Override
    public int getCount() {
        return fragmentArray.size();
    }
}
