package online.ghuri.com.beacondoctorapp.Login.Presenter;

import online.ghuri.com.beacondoctorapp.Login.Repository.LoginRegistrationCallBack;
import online.ghuri.com.beacondoctorapp.Login.Repository.LoginRegistrationDbHandler;
import online.ghuri.com.beacondoctorapp.Login.ViewModel.LoginViewModel;

/**
 * Created by Tanvir on 2/28/2018.
 */

public class LoginPresenter {

    private LoginRegistrationDbHandler mDbHandler;
    private LoginViewModel mViewModel;
    private int mPassShowCounter;

    public LoginPresenter(LoginRegistrationDbHandler mDbHandler, LoginViewModel mViewModel) {
        this.mDbHandler = mDbHandler;
        this.mViewModel = mViewModel;
    }

    public void onClickSignInBtn(){
        mViewModel.showProgressBar();
        String email=mViewModel.getEmailText();
        String pass=mViewModel.getPasswordText();

        mDbHandler.logInRequest(email, pass, new LoginRegistrationCallBack() {
            @Override
            public void onResponseSuccess(String token) {
                mDbHandler.saveToken(token);
                mViewModel.endProgressBar();
                mViewModel.openHomeActivity();
            }

            @Override
            public void onResponseError() {
             mViewModel.showToast("Please check you credential!");
                mViewModel.endProgressBar();
            }
        });


    }

    public void onClickPassShowImageButton() {



        String pass=mViewModel.getPasswordText();

       /* if (pass.length()==0){
            mPassShowCounter=0;
            mViewModel.hidePassword();
            return;
        }*/


        mPassShowCounter++;


        if (mPassShowCounter%2==1){
            mViewModel.showPassword();
        }else
            mViewModel.hidePassword();

        mViewModel.setPassCursor(pass.length());

    }
}
