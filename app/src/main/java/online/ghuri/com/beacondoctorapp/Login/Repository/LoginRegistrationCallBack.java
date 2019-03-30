package online.ghuri.com.beacondoctorapp.Login.Repository;

/**
 * Created by Tanvir on 2/28/2018.
 */

public interface LoginRegistrationCallBack {

    void onResponseSuccess(String token);
    void onResponseError();

}
