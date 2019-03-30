package online.ghuri.com.beacondoctorapp.Profile.Presenter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import online.ghuri.com.beacondoctorapp.Profile.Repository.ProfileCallback;
import online.ghuri.com.beacondoctorapp.Profile.Repository.ProfileDBHandler;
import online.ghuri.com.beacondoctorapp.Profile.Repository.User;
import online.ghuri.com.beacondoctorapp.Profile.ViewModel.ProfileViewModel;

/**
 * Created by Tanvir on 3/14/2018.
 */

public class ProfilePresenter {
    private ProfileViewModel mViewModel;
    private ProfileDBHandler mDBHandler;
    private User mUser;
    private boolean mIsUserInfoChanged;

    public ProfilePresenter(ProfileViewModel mViewModel, ProfileDBHandler mDBHandler) {
        this.mViewModel = mViewModel;
        this.mDBHandler = mDBHandler;

    }



    public void getProfileInfo(){


        mDBHandler.getProfileInfoFromLocal(new ProfileCallback() {
            @Override
            public void getUser(User user) {
                mViewModel.setUserData(user);
                byte[]profilePicByteArray=user.getmProfilePicByteData();
                if (profilePicByteArray!=null){
                    Bitmap bitmap= BitmapFactory.decodeByteArray(profilePicByteArray,0,profilePicByteArray.length);
                    if (bitmap!=null){
                        mViewModel.setProfilePic(bitmap);
                    }
                }
                mUser=user;
            }

            @Override
            public void errorOccured(String msg) {
                if (msg.equals("NUI")){
                    mDBHandler.getProfileInfoFromRemote(new ProfileCallback() {
                        @Override
                        public void getUser(User user) {
                            mViewModel.setUserData(user);
                            mDBHandler.saveProfileInfo(user);
                            mUser=user;
                        }

                        @Override
                        public void errorOccured(String msg) {

                        }
                    });
                }
            }
        });



    }

    public void userInfoChanged() {
        if (isUserDataChange()){
            mIsUserInfoChanged =true;
        }

    }

    private boolean isUserDataChange(){

        if(!mViewModel.getDegree().equals(mUser.getProfile().getDegree())){
            return true;
        } if (!mViewModel.getDesig().equals(mUser.getProfile().getDesig())){
            return true;
        } if (!mViewModel.getDepartment().equals(mUser.getProfile().getDept())){
            return true;
        } if (!mViewModel.getInstitute().equals(mUser.getProfile().getInst())){
            return true;
        } if (!mViewModel.getChamber1().equals(mUser.getProfile().getChambera())){
            return true;
        } if (!mViewModel.getChamber2().equals(mUser.getProfile().getChamberb())){
            return true;
        } if ((!mViewModel.getPhoneNumber().equals(mUser.getMobile()))){
            return true;
        } if (!mViewModel.getEmail().equals(mUser.getEmail())){
            return true;
        } if (!mViewModel.getBirthDay().equals(mUser.getBirth())){
            return true;
        } if (!mViewModel.getMarriageDay().equals(mUser.getProfile().getMarr())){
            return true;
        } if (!mViewModel.getFaccebookLink().equals(mUser.getProfile().getFb())){
            return true;
        }

        return false;
    }

    public void onPause() {

        if (mIsUserInfoChanged){
            mDBHandler.updateUserField(mViewModel.getDegree(),
                    mViewModel.getDesig(),
                    mViewModel.getDepartment(),
                    mViewModel.getInstitute(),
                    mViewModel.getChamber1(),
                    mViewModel.getChamber2(),
                    mViewModel.getPhoneNumber(),
                    mViewModel.getEmail(),
                    mViewModel.getBirthDay(),
                    mViewModel.getMarriageDay(),
                    mViewModel.getFaccebookLink()
                    );

        }
    }


    public void saveUserProfilePic(byte[] byteData) {
        mDBHandler.saveUserProfilePic(byteData);
    }

    public void onSettingBtnClick() {
        mViewModel.openEditProfileFragment();
    }

    public void backImageBtnClick() {
        mViewModel.finishActivity();
    }
}
