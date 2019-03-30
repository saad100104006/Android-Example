package online.ghuri.com.beacondoctorapp.Splash.Presenter;

import online.ghuri.com.beacondoctorapp.Profile.Repository.ProfileCallback;
import online.ghuri.com.beacondoctorapp.Profile.Repository.ProfileDBHandler;
import online.ghuri.com.beacondoctorapp.Profile.Repository.User;
import online.ghuri.com.beacondoctorapp.Splash.ViewModel.SplashViewModel;

/**
 * Created by Tanvir on 3/26/2018.
 */

public class SplashPresenter {
    private SplashViewModel mViewModel;
    private ProfileDBHandler mDbHandler;

    public SplashPresenter(SplashViewModel mViewModel, ProfileDBHandler mDbHandler) {
        this.mViewModel = mViewModel;
        this.mDbHandler = mDbHandler;
        takeDecision();
    }

    private void takeDecision(){

        if (mDbHandler.getToken()==null){
            mViewModel.openLoginActivity();
        }else {
            mDbHandler.getProfileInfoFromRemote(new ProfileCallback() {
                @Override
                public void getUser(User user) {
                    mViewModel.openHomeActivity();
                }

                @Override
                public void errorOccured(String msg) {
                    mViewModel.openLoginActivity();
                }
            });
        }
    }
}
