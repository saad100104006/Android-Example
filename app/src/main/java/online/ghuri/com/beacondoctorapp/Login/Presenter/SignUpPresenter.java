package online.ghuri.com.beacondoctorapp.Login.Presenter;

import android.util.Log;
import android.util.Patterns;

import online.ghuri.com.beacondoctorapp.Login.Repository.LoginRegistrationCallBack;
import online.ghuri.com.beacondoctorapp.Login.Repository.LoginRegistrationDbHandler;
import online.ghuri.com.beacondoctorapp.Login.ViewModel.SignUpViewModel;

/**
 * Created by Tanvir on 2/28/2018.
 */

public class SignUpPresenter {
    private LoginRegistrationDbHandler mDbHandler;
    private SignUpViewModel mViewModel;

    public SignUpPresenter(LoginRegistrationDbHandler mDbHandler, SignUpViewModel mViewModel) {
        this.mDbHandler = mDbHandler;
        this.mViewModel = mViewModel;
    }


    public void onClickCreateBtn(){
        String mrText=mViewModel.getMrSpinnnerTxt();
        String nameText=mViewModel.getNameText();
        String emailText=mViewModel.getEmailText();
        String mobileText=mViewModel.getMobileText();
        String passText=mViewModel.getPassText();
        String day=mViewModel.getdaySpinnnerText();
        String month=mViewModel.getMonthSpinnerText();
        String year=mViewModel.getyearSpinnerText();
        String bmdcRegTex=mViewModel.getBmdcRegNoText();

        String fullName=mrText+" "+nameText;
        String dob=day+"/"+month+"/"+year;

        boolean isPassed=true;

        if(fullName.matches("^[\\\\p{L} .'-]+$") || fullName.isEmpty()){
            isPassed=false;
            mViewModel.showToast("Please Enter valid name ");
        }

        if (emailText.isEmpty() || (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches())){
            isPassed=false;
            mViewModel.showToast("Please Enter valid Email Address ");
        }

        if (passText.isEmpty() || passText.length()<6){
            isPassed=false;
            mViewModel.showToast("Password length must be 6 character ");
        }

        if (bmdcRegTex.isEmpty()){
            isPassed=false;
            mViewModel.showToast("Please Enter valid BMDC Number ");

        }

        Log.i("sign_up_test",fullName+" "+mobileText+" "+bmdcRegTex+" "+dob+" "+emailText+" "+passText);

        if (isPassed){

            mDbHandler.registrationRequest(fullName,
                    mobileText,
                    bmdcRegTex,
                    dob,
                    emailText,
                    passText,
                    new LoginRegistrationCallBack() {
                        @Override
                        public void onResponseSuccess(String token) {
                            mViewModel.showToast("Successfully Registered");
                            mViewModel.closeDialog();
                        }

                        @Override
                        public void onResponseError() {
                            mViewModel.showToast("The email has already been taken. ");
                        }
                    }

            );
        }

    }
}
