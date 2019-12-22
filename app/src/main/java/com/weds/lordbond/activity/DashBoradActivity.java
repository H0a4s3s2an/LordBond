package com.weds.lordbond.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.weds.lordbond.R;
import com.weds.lordbond.adapter.MainPagerAdapter;
import com.weds.lordbond.custom_view.LordBondViewPager;
import com.weds.lordbond.dataSource.LoginPresenter;
import com.weds.lordbond.helper.ApplicationLordBond;
import com.weds.lordbond.util.Constants;

public class DashBoradActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {


    private LordBondViewPager mainViewPager;
    private BottomNavigationView bottomNavigationView;
    private MainPagerAdapter mainPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_borad);

        initUI();
    }

    private void initUI() {
        mainViewPager = findViewById(R.id.main_view_pager);
        bottomNavigationView = findViewById(R.id.navigation);
        mainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
        mainViewPager.beginFakeDrag();
        mainViewPager.setPagingEnabled(false);
        mainViewPager.setOffscreenPageLimit(2);

        mainViewPager.setAdapter(mainPagerAdapter);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        ImageView imageView = findViewById(R.id.user_avatar);
        ImageView logoutImage = findViewById(R.id.logout);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashBoradActivity.this, EditProfileActivity.class));
            }
        });
        logoutImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder dialog = new AlertDialog.Builder(DashBoradActivity.this);
                dialog.setIcon(R.mipmap.ic_launcher)
                .setMessage("Are you sure you want to logout ?")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ApplicationLordBond.preferencesManager.clear();
                        startActivity(new Intent(DashBoradActivity.this, AuthActivity.class));
                        finish();
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialog.create().dismiss();
                    }
                });
                dialog.create().show();
            }
        });
    }

    public void changeFragment(Fragment fragment, boolean isBackStackAdded, String fragmentTag) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.mainContainer, fragment);
        if (isBackStackAdded) {
            ft.addToBackStack(fragmentTag);
        }
        ft.commitAllowingStateLoss();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            for (int i = 0; i < getSupportFragmentManager().getBackStackEntryCount(); i++) {
                getSupportFragmentManager().popBackStack();
            }
        }

        switch (menuItem.getItemId()) {
            case R.id.navigation_home:
                mainViewPager.setCurrentItem(0);
                break;
            case R.id.navigation_notification:
                mainViewPager.setCurrentItem(1);
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    void onPermissonGranted(int permissionCode) {

    }
}
