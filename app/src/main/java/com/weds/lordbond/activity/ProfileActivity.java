package com.weds.lordbond.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.weds.lordbond.R;
import com.weds.lordbond.dataSource.LoginPresenter;
import com.weds.lordbond.model.LoginResponse;
import com.weds.lordbond.model.SignUp;
import com.weds.lordbond.util.Constants;

public class ProfileActivity extends BaseActivity implements LoginPresenter.profileEventListener,
        View.OnClickListener {
    
    private ImageView user_avatar, backImg;
    private TextView fullName, userFigure, userBio, userNameTv, genderTv, ageTv, heightTv, languageTv,
            martialStatusTv, religionTv, castTv, qualificationTv, occupationTv, incomeTv, familyStatusTv,
            familyTypeTv, familyValueTv, bloodGroupTv;
    private LoginPresenter loginPresenter;
    private LoginResponse profileData;
    private int profileId;
   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        
        if (getIntent() != null) {
            profileId = getIntent().getIntExtra(Constants.VIEW_PROFILE_CODE_KEY, 1);
        }

        initUI();
    }

    private void initUI() {
        user_avatar = findViewById(R.id.user_avatar);
        backImg = findViewById(R.id.back);
        fullName = findViewById(R.id.name_tv);
        userFigure = findViewById(R.id.figure_tv);
        userBio = findViewById(R.id.intro_tv);
        userNameTv = findViewById(R.id.user_name_tv);
        genderTv = findViewById(R.id.user_gender_tv);
        ageTv = findViewById(R.id.user_age_tv);
        heightTv = findViewById(R.id.height_tv);
        languageTv = findViewById(R.id.lang_tv);
        martialStatusTv = findViewById(R.id.martial_status_tv);
        religionTv = findViewById(R.id.user_religion_tv);
        castTv = findViewById(R.id.user_cast_tv);
        qualificationTv = findViewById(R.id.qualification_tv);
        occupationTv = findViewById(R.id.occupation_tv);
        incomeTv = findViewById(R.id.annual_income_tv);
        familyStatusTv = findViewById(R.id.user_family_status_tv);
        familyTypeTv = findViewById(R.id.family_type_tv);
        familyValueTv = findViewById(R.id.user_family_value_tv);
        bloodGroupTv = findViewById(R.id.blood_group_tv);

        backImg.setOnClickListener(this);
        loginPresenter = new LoginPresenter(this);
        showProgressDialog("Loading...");
        loginPresenter.viewProfile(profileId);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
        }
    }
    
    @Override
    public void onSuccess(String response, String status, String extra) {
        profileData = new Gson().fromJson(response, new TypeToken<LoginResponse>(){}.getType());
        setData();
        dismissProgressDialog();
    }
    
    private void setData() {
        if (profileData.getBio() != null) userBio.setText(profileData.getBio());
        if (profileData.getName() != null) fullName.setText(profileData.getName());
        if (profileData.getUserName() != null) userNameTv.setText(profileData.getUserName());
        if (profileData.getGender() != null) genderTv.setText(profileData.getGender());
        if (profileData.getLanguage() != null) languageTv.setText(profileData.getLanguage());
        if (profileData.getReligion() != null) religionTv.setText(profileData.getReligion());
        if (profileData.getQualification() != null) qualificationTv.setText(profileData.getQualification());
        if (profileData.getOccupation() != null) occupationTv.setText(profileData.getOccupation());
        if (profileData.getCaste() != null) castTv.setText(profileData.getCaste());
        if (profileData.getAnnualIncome() != null) incomeTv.setText(profileData.getAnnualIncome());
        if (profileData.getFamilyStatus() != null) familyStatusTv.setText(profileData.getFamilyStatus());
        if (profileData.getFamilyType() != null) familyTypeTv.setText(profileData.getFamilyType());
        if (profileData.getFamilyValue() != null) familyValueTv.setText(profileData.getFamilyValue());
        if (profileData.getBloodGroup() != null) bloodGroupTv.setText(profileData.getBloodGroup());
        if (profileData.getHeight() != null )
            heightTv.setText(profileData.getHeight());
            userFigure.setText(profileData.getHeight().concat(",").concat(" ").concat("India"));
    }
    
    @Override
    public void onFail(String errMsg, String status, String extra) {
        dismissProgressDialog();
        Toast.makeText(this, errMsg, Toast.LENGTH_SHORT).show();
    }
    
    @Override
    void onPermissonGranted(int permissionCode) {
        dismissProgressDialog();
    }
    
}