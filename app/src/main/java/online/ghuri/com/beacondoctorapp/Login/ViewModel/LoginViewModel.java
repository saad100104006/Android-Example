package online.ghuri.com.beacondoctorapp.Login.ViewModel;

/**
 * Created by Tanvir on 2/28/2018.
 */

public interface LoginViewModel {

    void showProgressBar();
    void endProgressBar();


     String getEmailText();
     String getPasswordText();
     void openHomeActivity();

     void showPassword();
     void hidePassword();

     void setPassCursor(int length);


    void showToast(String msg);

}
