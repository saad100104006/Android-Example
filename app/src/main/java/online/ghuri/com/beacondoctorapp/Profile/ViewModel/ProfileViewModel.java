package online.ghuri.com.beacondoctorapp.Profile.ViewModel;

import android.graphics.Bitmap;

import online.ghuri.com.beacondoctorapp.Profile.Repository.User;

/**
 * Created by Tanvir on 3/14/2018.
 */

public interface ProfileViewModel {


    void setUserData(User user);


    String getDegree();
    String getDesig();
    String getDepartment();
    String getInstitute();
    String getChamber1();
    String getChamber2();
    String getPhoneNumber();
    String getEmail();
    String getBirthDay();
    String getMarriageDay();
    String getFaccebookLink();

    void openEditProfileFragment();

    void finishActivity();

    void setProfilePic(Bitmap bitmap);
}
