package com.weds.lordbond.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kbeanie.multipicker.api.CameraImagePicker;
import com.kbeanie.multipicker.api.callbacks.ImagePickerCallback;
import com.kbeanie.multipicker.api.entity.ChosenImage;
import com.weds.lordbond.R;
import com.weds.lordbond.dataSource.LoginPresenter;
import com.weds.lordbond.helper.ApplicationLordBond;
import com.weds.lordbond.helper.GlideHelper;
import com.weds.lordbond.model.Caste;
import com.weds.lordbond.model.Countries;
import com.weds.lordbond.model.Languages;
import com.weds.lordbond.model.Occupations;
import com.weds.lordbond.model.Qualifications;
import com.weds.lordbond.model.Religions;
import com.weds.lordbond.model.SignUp;
import com.weds.lordbond.util.Constants;
import com.weds.lordbond.util.Utils;

import java.util.List;

public class EditProfileActivity extends BaseActivity implements LoginPresenter.profileEventListener,
        ImagePickerCallback, View.OnClickListener {
    
    private ImageView backImg, userImg, editImg;
    private TextView previewTv;
    private EditText userInfoET, userNameET, userGenderET, userAgeET, userHeightET, userLanguageET,
            userMartialStatusET, userReligion, userCastET, userEducationET, userOccupationET,
            userIncomeET, userFamilyType, userFamilyStatusET, userFamilyValueET, userDobET, userLocationET,
            userCountryET, userComplextionET, anyDisabilityET, userBloodGroupET, employedInET;
    private LoginPresenter loginPresenter;
    private SignUp editProfileData;
    private ProgressBar horProgressBar;
    private Button submitBtn;
    private String[] languageList;
    private String[] casteList;
    private String[] educationList;
    private String[] occupationList;
    private String[] religionList;
    private String[] countryList;
    private String[] martialStatusList;
    private String[] profileForList;
    private String[] genderSelectionList;
    private String[] familyValueList;
    private String[] familyTypeList;
    private String[] familyStatusList;
    private String[] anyDisabilityList;
    private String[] complextionList;
    private String[] bloodGroupList;
    
    
    private CameraImagePicker cameraPicker;
    private int countryItem, langItem, religionItem, castItem, educationItem, qualificationItem;
    private String filePath = null;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        
        initUI();
    }
    
    private void initUI() {
        loginPresenter = new LoginPresenter(this);
        backImg = findViewById(R.id.back);
        userImg = findViewById(R.id.user_avatar);
        editImg = findViewById(R.id.edit_img);
        previewTv = findViewById(R.id.preview_tv);
        userInfoET = findViewById(R.id.user_info_et);
        userNameET = findViewById(R.id.user_name_et);
        userCountryET = findViewById(R.id.user_country_et);
        userGenderET = findViewById(R.id.user_gender_et);
        userAgeET = findViewById(R.id.user_age_et);
        userHeightET = findViewById(R.id.user_height_et);
        userLanguageET = findViewById(R.id.user_tongue_et);
        userMartialStatusET = findViewById(R.id.user_martial_status_et);
        userReligion = findViewById(R.id.user_religion_et);
        userCastET = findViewById(R.id.user_cast_et);
        userEducationET = findViewById(R.id.user_education_et);
        userOccupationET = findViewById(R.id.user_occupation_et);
        userIncomeET = findViewById(R.id.user_income_et);
        userFamilyType = findViewById(R.id.user_family_type_et);
        userFamilyStatusET = findViewById(R.id.user_family_status_et);
        userFamilyValueET = findViewById(R.id.user_family_value_et);
        userDobET = findViewById(R.id.user_dob_et);
        userLocationET = findViewById(R.id.user_location_et);
        anyDisabilityET = findViewById(R.id.user_disability_et);
        userComplextionET = findViewById(R.id.user_complextion_et);
        userBloodGroupET = findViewById(R.id.user_blood_group_et);
        employedInET = findViewById(R.id.user_employed_in_et);
        submitBtn = findViewById(R.id.submit_btn);
        
        backImg.setOnClickListener(this);
        editImg.setOnClickListener(this);
        previewTv.setOnClickListener(this);
        userCountryET.setOnClickListener(this);
        userMartialStatusET.setOnClickListener(this);
        userGenderET.setOnClickListener(this);
        userLanguageET.setOnClickListener(this);
        userReligion.setOnClickListener(this);
        userEducationET.setOnClickListener(this);
        userOccupationET.setOnClickListener(this);
        userCastET.setOnClickListener(this);
        userFamilyType.setOnClickListener(this);
        userFamilyStatusET.setOnClickListener(this);
        userFamilyValueET.setOnClickListener(this);
        submitBtn.setOnClickListener(this);
        editImg.setOnClickListener(this);
        userComplextionET.setOnClickListener(this);
        anyDisabilityET.setOnClickListener(this);
        userBloodGroupET.setOnClickListener(this);
        
        martialStatusList = new String[] {
                getString(R.string.never_married),
                getString(R.string.widowed),
                getString(R.string.divorced),
                getString(R.string.divorce_awaiting)
        };
        
        profileForList = new String[] {
                getString(R.string.profile_for_seld),
                getString(R.string.profile_for_daughter),
                getString(R.string.profile_for_son)
        };
        
        genderSelectionList = new String[] {
                getString(R.string.male),
                getString(R.string.female)
        };
        
        familyValueList = new String[] {
                getString(R.string.orthodox_family_value),
                getString(R.string.traditional_family_value),
                getString(R.string.moderate_family_value),
                getString(R.string.liberal_family_value)
        };
        
        familyTypeList = new String[] {
                getString(R.string.joint_family_type),
                getString(R.string.nuclear_family_type)
        };
        
        familyStatusList = new String[] {
                getString(R.string.middle_class_family_status),
                getString(R.string.rich_family_type),
                getString(R.string.upper_class_family_type),
                getString(R.string.afluent_family_type)
        };
        
        anyDisabilityList = new String[] {
        		getString(R.string.yes),
		        getString(R.string.no)
        };
        
        complextionList = new String[] {
        		getString(R.string.light),
		        getString(R.string.fair),
		        getString(R.string.medium),
		        getString(R.string.olive),
		        getString(R.string.brown),
		        getString(R.string.black)
        };
        
        bloodGroupList = new String[] {
        		getString(R.string.aplus),
		        getString(R.string.anegative),
		        getString(R.string.bplus),
		        getString(R.string.bnegative),
		        getString(R.string.abplus),
		        getString(R.string.abnegative)
        };
        
        showProgressDialog("Loading...");
        loginPresenter.editProfile();
    }
    
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.user_cast_et:
                hideKeyboard();
                casteList = new String[editProfileData.getCaste().size()];
                for (Caste cl: editProfileData.getCaste()) casteList[editProfileData.getCaste().indexOf(cl)]
                        = cl.getCaste();
                AlertDialog.Builder dialogCast = new AlertDialog.Builder(this, R.style.MyAlertDialogTheme);
                dialogCast.setIcon(R.mipmap.ic_launcher);
                dialogCast.setTitle("Caste");
                dialogCast.setSingleChoiceItems(casteList, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        userCastET.setText(casteList[which]);
                        castItem = ((AlertDialog) dialog).getListView().getCheckedItemPosition();
                        dialog.dismiss();
                    }
                });
                dialogCast.create().show();
                break;
            case R.id.user_country_et:
                hideKeyboard();
                countryList = new String[editProfileData.getCountries().size()];
                for (Countries ls: editProfileData.getCountries()) countryList[editProfileData.getCountries().indexOf(ls)]
                        = ls.getCountry();
                final AlertDialog.Builder dialogCountryList = new AlertDialog.Builder(this, R.style.MyAlertDialogTheme);
                dialogCountryList.setIcon(R.mipmap.ic_launcher);
                dialogCountryList.setTitle("Country");
                dialogCountryList.setSingleChoiceItems(countryList, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        userCountryET.setText(countryList[which]);
                        countryItem = ((AlertDialog)dialog).getListView().getCheckedItemPosition();
                        dialog.dismiss();
                    }
                });
                dialogCountryList.create().show();
                break;
            case R.id.user_tongue_et:
                hideKeyboard();
                languageList = new String[editProfileData.getLanguages().size()];
                for (Languages ls: editProfileData.getLanguages()) languageList[editProfileData.getLanguages().indexOf(ls)]
                        = ls.getLanguage();
                AlertDialog.Builder dialogProfile = new AlertDialog.Builder(this, R.style.MyAlertDialogTheme);
                dialogProfile.setIcon(R.mipmap.ic_launcher);
                dialogProfile.setTitle("Language");
                dialogProfile.setSingleChoiceItems(languageList, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        userLanguageET.setText(languageList[which]);
                        langItem = ((AlertDialog)dialog).getListView().getCheckedItemPosition();
                        dialog.dismiss();
                    }
                });
                dialogProfile.create().show();
                break;
            case R.id.user_martial_status_et:
                hideKeyboard();
                AlertDialog.Builder dialog = new AlertDialog.Builder(this, R.style.MyAlertDialogTheme);
                dialog.setIcon(R.mipmap.ic_launcher);
                dialog.setTitle(getString(R.string.martial_status));
                dialog.setSingleChoiceItems(martialStatusList, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        userMartialStatusET.setText(martialStatusList[which]);
                        dialog.dismiss();
                    }
                });
                dialog.create().show();
                break;
            case R.id.user_gender_et:
                hideKeyboard();
                AlertDialog.Builder dialogGender = new AlertDialog.Builder(this, R.style.MyAlertDialogTheme);
                dialogGender.setIcon(R.mipmap.ic_launcher);
                dialogGender.setTitle(getString(R.string.gender));
                dialogGender.setSingleChoiceItems(genderSelectionList, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        userGenderET.setText(genderSelectionList[which]);
                        dialog.dismiss();
                    }
                });
                dialogGender.create().show();
                break;
            case R.id.user_religion_et:
                hideKeyboard();
                religionList = new String[editProfileData.getReligions().size()];
                for (Religions rl: editProfileData.getReligions()) religionList[editProfileData.getReligions().indexOf(rl)]
                        = rl.getReligionName();
                AlertDialog.Builder dialogReligion = new AlertDialog.Builder(this, R.style.MyAlertDialogTheme);
                dialogReligion.setIcon(R.mipmap.ic_launcher);
                dialogReligion.setTitle("Religion");
                dialogReligion.setSingleChoiceItems(religionList, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        userReligion.setText(religionList[which]);
                        religionItem = ((AlertDialog)dialog).getListView().getCheckedItemPosition();
                        dialog.dismiss();
                    }
                });
                dialogReligion.create().show();
                break;
            case R.id.user_education_et:
                hideKeyboard();
                educationList = new String[editProfileData.getQualifications().size()];
                for (Qualifications ql: editProfileData.getQualifications()) educationList[editProfileData.getQualifications().indexOf(ql)]
                        = ql.getUserQualification();
                AlertDialog.Builder dialogQualification = new AlertDialog.Builder(this, R.style.MyAlertDialogTheme);
                dialogQualification.setIcon(R.mipmap.ic_launcher);
                dialogQualification.setTitle("Education");
                dialogQualification.setSingleChoiceItems(educationList, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        userEducationET.setText(educationList[which]);
                        educationItem = ((AlertDialog)dialog).getListView().getCheckedItemPosition();
                        dialog.dismiss();
                    }
                });
                dialogQualification.create().show();
                break;
            case R.id.user_occupation_et:
                hideKeyboard();
                occupationList = new String[editProfileData.getOccupations().size()];
                for (Occupations ol: editProfileData.getOccupations()) occupationList[editProfileData.getOccupations().indexOf(ol)]
                        = ol.getUserOccupation();
                AlertDialog.Builder dialogOccupation = new AlertDialog.Builder(this, R.style.MyAlertDialogTheme);
                dialogOccupation.setIcon(R.mipmap.ic_launcher);
                dialogOccupation.setTitle("Occupation");
                dialogOccupation.setSingleChoiceItems(occupationList, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        userOccupationET.setText(occupationList[which]);
                        qualificationItem = ((AlertDialog)dialog).getListView().getCheckedItemPosition();
                        dialog.dismiss();
                    }
                });
                dialogOccupation.create().show();
                break;
            case R.id.user_family_type_et:
                hideKeyboard();
                AlertDialog.Builder dialogFamilyType = new AlertDialog.Builder(this, R.style.MyAlertDialogTheme);
                dialogFamilyType.setIcon(R.mipmap.ic_launcher);
                dialogFamilyType.setTitle("Family Type");
                dialogFamilyType.setSingleChoiceItems(familyTypeList, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        userFamilyType.setText(familyTypeList[which]);
                        dialog.dismiss();
                    }
                });
                dialogFamilyType.create().show();
                break;
            case R.id.user_family_status_et:
                hideKeyboard();
                AlertDialog.Builder dialogFamilyStatus = new AlertDialog.Builder(this, R.style.MyAlertDialogTheme);
                dialogFamilyStatus.setIcon(R.mipmap.ic_launcher);
                dialogFamilyStatus.setTitle("Family Status");
                dialogFamilyStatus.setSingleChoiceItems(familyStatusList, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        userFamilyStatusET.setText(familyStatusList[which]);
                        dialog.dismiss();
                    }
                });
                dialogFamilyStatus.create().show();
                break;
            case R.id.user_family_value_et:
                hideKeyboard();
                AlertDialog.Builder dialogFamilyValue = new AlertDialog.Builder(this, R.style.MyAlertDialogTheme);
                dialogFamilyValue.setIcon(R.mipmap.ic_launcher);
                dialogFamilyValue.setTitle("Family value");
                dialogFamilyValue.setSingleChoiceItems(familyValueList, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        userFamilyValueET.setText(familyValueList[which]);
                        dialog.dismiss();
                    }
                });
                dialogFamilyValue.create().show();
                break;
	        case R.id.user_disability_et:
		        hideKeyboard();
		        AlertDialog.Builder dialogDiablility = new AlertDialog.Builder(this, R.style.MyAlertDialogTheme);
		        dialogDiablility.setIcon(R.mipmap.ic_launcher);
		        dialogDiablility.setTitle("Any Disability");
		        dialogDiablility.setSingleChoiceItems(anyDisabilityList, -1, new DialogInterface.OnClickListener() {
			        @Override
			        public void onClick(DialogInterface dialog, int which) {
				        anyDisabilityET.setText(anyDisabilityList[which]);
				        dialog.dismiss();
			        }
		        });
		        dialogDiablility.create().show();
	        	break;
	        case R.id.user_complextion_et:
		        hideKeyboard();
		        AlertDialog.Builder dialogComplextion = new AlertDialog.Builder(this, R.style.MyAlertDialogTheme);
		        dialogComplextion.setIcon(R.mipmap.ic_launcher);
		        dialogComplextion.setTitle("Complexion");
		        dialogComplextion.setSingleChoiceItems(complextionList, -1, new DialogInterface.OnClickListener() {
			        @Override
			        public void onClick(DialogInterface dialog, int which) {
				        userComplextionET.setText(complextionList[which]);
				        dialog.dismiss();
			        }
		        });
		        dialogComplextion.create().show();
	        	break;
	        case R.id.user_blood_group_et:
		        hideKeyboard();
		        AlertDialog.Builder dialogBloodGroup = new AlertDialog.Builder(this, R.style.MyAlertDialogTheme);
		        dialogBloodGroup.setIcon(R.mipmap.ic_launcher);
		        dialogBloodGroup.setTitle("Blood Group");
		        dialogBloodGroup.setSingleChoiceItems(bloodGroupList, -1, new DialogInterface.OnClickListener() {
			        @Override
			        public void onClick(DialogInterface dialog, int which) {
				        userBloodGroupET.setText(bloodGroupList[which]);
				        dialog.dismiss();
			        }
		        });
		        dialogBloodGroup.create().show();
	        	break;
            case R.id.edit_img:
                selectGalleryImage();
                break;
            case R.id.submit_btn:
                SignUp profileData = new SignUp();
	        	profileData.setBio(userInfoET.getText().toString());
	        	profileData.setUserName(userNameET.getText().toString());
	        	profileData.setGender(userGenderET.getText().toString());
	        	profileData.setHeight(userHeightET.getText().toString());
                profileData.setFkCasteId(editProfileData.getCaste().get(castItem).getCastId());
                profileData.setCountryID(editProfileData.getCountries().get(countryItem).getCountryId());
                profileData.setFkLanguageId(editProfileData.getLanguages().get(langItem).getLanguageId());
                profileData.setFkReligionId(editProfileData.getReligions().get(religionItem).getReligionId());
                profileData.setFkOccupationId(editProfileData.getOccupations().get(qualificationItem).getOccupationId());
                profileData.setFkQualificationId(editProfileData.getQualifications().get(educationItem).getQualificationId());
	        	profileData.setAnnualIncome(userIncomeET.getText().toString());
	        	profileData.setDateOfBirth(userDobET.getText().toString());
	        	profileData.setOccupation(userOccupationET.getText().toString());
	        	profileData.setFkUserId(ApplicationLordBond.preferencesManager.getIntValue(Constants.LOGGED_IN_USER_ID));
	        	profileData.setUserId(ApplicationLordBond.preferencesManager.getIntValue(Constants.LOGGED_IN_USER_ID));
	        	profileData.setFamilyStatus(userFamilyStatusET.getText().toString());
	        	profileData.setFamilyType(userFamilyType.getText().toString());
	        	profileData.setFamilyValue(userFamilyValueET.getText().toString());
	        	profileData.setAge(Integer.parseInt(userAgeET.getText().toString()));
	        	profileData.setMartialStatus(userMartialStatusET.getText().toString());
	        	profileData.setDisablility(anyDisabilityET.getText().toString());
	        	profileData.setBloodGroup(userBloodGroupET.getText().toString());
	        	profileData.setComplextion(userComplextionET.getText().toString());
	        	profileData.setEmployedIn(employedInET.getText().toString());
	        	profileData.setProfileImage(filePath);
	
	            showProgressDialog("Updating...");
	        	loginPresenter.updateProfile(profileData);
	        	break;
        }
    }
    
    private void selectGalleryImage() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, Constants.GALLERY_PHOTO_REQUEST_CODE_KEY);
    }
    
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            switch (requestCode) {
                case Constants.GALLERY_PHOTO_REQUEST_CODE_KEY:
                    onSelectedFromGalleryResults(data);
                    break;
//                case Picker.PICK_IMAGE_CAMERA:
//                    if (cameraPicker == null) {
//                        cameraPicker = new CameraImagePicker(this);
//                        cameraPicker.reinitialize(filePath);
//                    }
//                    cameraPicker.submit(data);
//                    break;
                default:
                    break;
            }
        } else {
        
        }
    }
    
    private void onSelectedFromGalleryResults(Intent data) {
        Bitmap bitmap = null;
        if (data != null) {
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        userImg.setImageBitmap(bitmap);
        filePath = Utils.encodeTobase64(bitmap);
    }
    
    @Override
    public void onSuccess(String response, String status, String extra) {
        if (extra.equalsIgnoreCase(Constants.PROFILE_UPDATE_CODE_KEY)) {
	        Toast.makeText(this, response, Toast.LENGTH_SHORT).show();
	        finish();
        } else {
	        editProfileData = new Gson().fromJson(response, new TypeToken<SignUp>() {
	        }.getType());
	        setData();
        }
	    dismissProgressDialog();
    }
    
    private void setData() {
        GlideHelper.loadImage(this, editProfileData.getProfileImage(), userImg);
        if (editProfileData.getBio() != null) userInfoET.setText(editProfileData.getBio());
        if (editProfileData.getName() != null) userNameET.setText(editProfileData.getName());
        if (editProfileData.getGender() != null) userGenderET.setText(editProfileData.getGender());
        if (editProfileData.getHeight() != null) userHeightET.setText(editProfileData.getHeight());
        userAgeET.setText(String.valueOf(editProfileData.getAge()));
        if (editProfileData.getMartialStatus() != null) userMartialStatusET.setText(editProfileData.getMartialStatus());
        if (editProfileData.getLanguageId() != 0 && editProfileData.getLanguages() != null) {
            for (int i = 0; i < editProfileData.getLanguages().size(); i++) {
                if (editProfileData.getLanguages().get(i).getLanguageId() == editProfileData.getLanguageId()) {
                    userLanguageET.setText(editProfileData.getLanguages().get(i).getLanguage());
                }
            }
        }
        if (editProfileData.getCastId() != 0 && editProfileData.getCaste() != null) {
            for (int i = 0; i < editProfileData.getCaste().size(); i++) {
                if (editProfileData.getCaste().get(i).getCastId() == editProfileData.getCastId()) {
                    userCastET.setText(editProfileData.getCaste().get(i).getCaste());
                }
            }
        }
        if (editProfileData.getCountryId() != 0 && editProfileData.getCountries() != null) {
            for (int i = 0; i < editProfileData.getCountries().size(); i++) {
                if (editProfileData.getCountries().get(i).getCountryId() == editProfileData.getCountryId()) {
                    userCountryET.setText(editProfileData.getCountries().get(i).getCountry());
                }
            }
        }
        if (editProfileData.getReligionId() != 0 && editProfileData.getReligions() != null) {
            for (int i = 0; i < editProfileData.getReligions().size(); i++) {
                if (editProfileData.getReligions().get(i).getReligionId() == editProfileData.getReligionId()) {
                    userReligion.setText(editProfileData.getReligions().get(i).getReligionName());
                }
            }
        }
        if (editProfileData.getQualificationId() != 0 && editProfileData.getQualifications() != null) {
            for (int i = 0; i < editProfileData.getQualifications().size(); i++) {
                if (editProfileData.getQualifications().get(i).getQualificationId() == editProfileData.getQualificationId()) {
                    userEducationET.setText(editProfileData.getQualifications().get(i).getUserQualification());
                }
            }
        }
        if (editProfileData.getOccupationId() != 0 && editProfileData.getOccupations() != null) {
            for (int i = 0; i < editProfileData.getOccupations().size(); i++) {
                if (editProfileData.getOccupations().get(i).getOccupationId() == editProfileData.getOccupationId()) {
                    userOccupationET.setText(editProfileData.getOccupations().get(i).getUserOccupation());
                }
            }
        }
	
	    if (editProfileData.getDisablility() != null) anyDisabilityET.setText(editProfileData.getDisablility());
	    if (editProfileData.getBloodGroup() != null) userBloodGroupET.setText(editProfileData.getBloodGroup());
	    if (editProfileData.getComplextion() != null) userComplextionET.setText(editProfileData.getComplextion());
	    if (editProfileData.getEmployedIn() != null) employedInET.setText(editProfileData.getEmployedIn());
        if (editProfileData.getAnnualIncome() != null) userIncomeET.setText(editProfileData.getAnnualIncome());
        if (editProfileData.getDateOfBirth() != null) userDobET.setText(editProfileData.getDateOfBirth());
        if (editProfileData.getOccupation() != null) userOccupationET.setText(editProfileData.getOccupation());
        if (editProfileData.getFamilyStatus() != null) userFamilyStatusET.setText(editProfileData.getFamilyStatus());
        if (editProfileData.getFamilyType() != null) userFamilyType.setText(editProfileData.getFamilyType());
        if (editProfileData.getFamilyValue() != null) userFamilyValueET.setText(editProfileData.getFamilyValue());
        
    }
    
    @Override
    public void onFail(String errMsg, String status, String extra) {
	    Toast.makeText(this, errMsg, Toast.LENGTH_SHORT).show();
    	dismissProgressDialog();
    }
    
    @Override
    void onPermissonGranted(int permissionCode) {
    
    }
    
    @Override
    public void onImagesChosen(List<ChosenImage> list) {
    
    }
    
    @Override
    public void onError(String s) {
    
    }
}
