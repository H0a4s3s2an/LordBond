package com.weds.lordbond.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.weds.lordbond.fragment.HomeFragment;
import com.weds.lordbond.fragment.NotificationFragment;
import com.weds.lordbond.fragment.SearchFragment;

public class MainPagerAdapter extends FragmentStatePagerAdapter {

    private Fragment[] fragmentArray;

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
        fragmentArray = new Fragment[]{new HomeFragment(), new NotificationFragment()};
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentArray[position];
    }

    @Override
    public int getCount() {
        return fragmentArray.length;
    }
}
