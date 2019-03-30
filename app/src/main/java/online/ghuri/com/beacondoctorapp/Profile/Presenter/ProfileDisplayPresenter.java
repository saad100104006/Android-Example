package online.ghuri.com.beacondoctorapp.Profile.Presenter;

import online.ghuri.com.beacondoctorapp.Profile.Repository.ProfileDBHandler;
import online.ghuri.com.beacondoctorapp.Profile.ViewModel.ProfileViewModel;

/**
 * Created by Tanvir on 3/17/2018.
 */

public class ProfileDisplayPresenter extends ProfilePresenter {
    ProfileViewModel mViewModel;
    ProfileDBHandler mDbhandler;
    public ProfileDisplayPresenter(ProfileViewModel mViewModel, ProfileDBHandler mDBHandler) {
        super(mViewModel, mDBHandler);
        this.mViewModel=mViewModel;
        this.mDbhandler=mDBHandler;

    }

    public void onSettingBtnClick() {
        mViewModel.openEditProfileFragment();
    }

    public void backImageBtnClick() {
        mViewModel.finishActivity();
    }
}
