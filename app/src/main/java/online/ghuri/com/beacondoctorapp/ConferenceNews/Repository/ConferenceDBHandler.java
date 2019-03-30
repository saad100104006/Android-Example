package online.ghuri.com.beacondoctorapp.ConferenceNews.Repository;

import android.content.Context;

import online.ghuri.com.beacondoctorapp.Preferences;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Tanvir on 3/5/2018.
 */

public class ConferenceDBHandler {

    private static final String BASE_URL = "http://pill.medicare.org.bd/api/";
    private ApiEndPoint mApiEndPoint;
    private Context mContext;

    public ConferenceDBHandler(Context mContext) {
        this.mContext = mContext;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mApiEndPoint = retrofit.create(ApiEndPoint.class);
    }

    public void getConferenceNewsList(final ConferenceCallback callback){
        String token= "Bearer "+ Preferences.getToken(mContext);
        Call<ConferenceResponse> responseCall=mApiEndPoint.getConferenceNewsList(token);
        responseCall.enqueue(new Callback<ConferenceResponse>() {
            @Override
            public void onResponse(Call<ConferenceResponse> call, Response<ConferenceResponse> response) {
                if (response.isSuccessful()){
                    callback.getConferenceNewsList(response.body().getConferences());
                }
            }

            @Override
            public void onFailure(Call<ConferenceResponse> call, Throwable t) {

            }
        });
    }
}
