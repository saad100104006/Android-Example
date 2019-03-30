package online.ghuri.com.beacondoctorapp.Login.Repository;

import android.content.Context;

import online.ghuri.com.beacondoctorapp.Preferences;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Tanvir on 2/28/2018.
 */

public class LoginRegistrationDbHandler {

    private static final String BASE_URL = "http://flexibac.com.bd/api/";
    private ApiEndPoint mApiEndPoint;
    private Context mContext;


    public LoginRegistrationDbHandler(Context context) {
        mContext=context;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mApiEndPoint = retrofit.create(ApiEndPoint.class);
    }


    public void logInRequest(String email, String passWord, final LoginRegistrationCallBack callBack){
       Call<SuccessResponse> responseCall= mApiEndPoint.userLoginPostRequest(email,passWord);
       responseCall.enqueue(new Callback<SuccessResponse>() {
           @Override
           public void onResponse(Call<SuccessResponse> call, Response<SuccessResponse> response) {
               if (response.isSuccessful()){

                   callBack.onResponseSuccess(response.body().getSuccess().getToken());
               }else {
                    callBack.onResponseError();
               }

           }

           @Override
           public void onFailure(Call<SuccessResponse> call, Throwable t) {
                callBack.onResponseError();
           }
       });
    }


    public void registrationRequest(String name,String mobileNo,String bmdcNo,String dob,String email, String passWord, final LoginRegistrationCallBack callBack){
        Call<SuccessResponse> responseCall= mApiEndPoint.userRegisterPostRequest(name,
                email,
                passWord,
                mobileNo,
                dob,
                bmdcNo);
        responseCall.enqueue(new Callback<SuccessResponse>() {
            @Override
            public void onResponse(Call<SuccessResponse> call, Response<SuccessResponse> response) {
                if (response.isSuccessful()){

                    callBack.onResponseSuccess(response.body().getSuccess().getToken());
                }else {
                    callBack.onResponseError();
                }

            }

            @Override
            public void onFailure(Call<SuccessResponse> call, Throwable t) {
                callBack.onResponseError();
            }
        });
    }


    public void saveToken(String token) {
        Preferences.setToken(mContext,token);


    }
}
