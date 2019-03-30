package online.ghuri.com.beacondoctorapp.About.Repository;

import android.content.Context;

import online.ghuri.com.beacondoctorapp.Dictionary.Repository.*;
import online.ghuri.com.beacondoctorapp.Preferences;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Tanvir on 3/27/2018.
 */

public class AboutDbHandler {

    private Context mContext;
    private static final String BASE_URL = "http://flexibac.com.bd/api/";
    private ApiEndPoint mApiEndPoint;

    public AboutDbHandler(Context mContext) {
        this.mContext = mContext;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mApiEndPoint = retrofit.create(ApiEndPoint.class);

    }

    public void getAbout(final AboutRepoCallback callback){
        String token= "Bearer "+ Preferences.getToken(mContext);
        Call<AboutResponse> aboutResponseCall=mApiEndPoint.getAbout(token);
        aboutResponseCall.enqueue(new Callback<AboutResponse>() {
            @Override
            public void onResponse(Call<AboutResponse> call, Response<AboutResponse> response) {
                if (response.isSuccessful()){
                    callback.getAbout(response.body().getAbout());
                }else {
                    callback.errorMsg("");
                }
            }

            @Override
            public void onFailure(Call<AboutResponse> call, Throwable t) {
                callback.errorMsg(t.getMessage());
            }
        });
    }
}
