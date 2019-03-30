package online.ghuri.com.beacondoctorapp.JournalCategory.Repository;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import online.ghuri.com.beacondoctorapp.Preferences;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Tanvir on 5/7/2018.
 */

public class JournalCategoryDBHandler {

    private static final String BASE_URL="http://www.flexibac.com.bd/api/";
    private ApiEndPoint mApiEndPoint;
    private Context mContext;

    public JournalCategoryDBHandler(Context mContext) {
        this.mContext = mContext;
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit=new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BASE_URL)
                .build();
        mApiEndPoint=retrofit.create(ApiEndPoint.class);
    }


    public void getCategory(final JournalCategoryDBHandlerCallback callback){
        String token= "Bearer "+ Preferences.getToken(mContext);
        Call<CategoriesResponse> responseCall=mApiEndPoint.getCategories(token);

        responseCall.enqueue(new Callback<CategoriesResponse>() {
            @Override
            public void onResponse(Call<CategoriesResponse> call, Response<CategoriesResponse> response) {
                if (response.isSuccessful()){
                    callback.getCategory(response.body().getCategorys());

                }else {
                    callback.getErrorMsg("Error Response");
                }
            }

            @Override
            public void onFailure(Call<CategoriesResponse> call, Throwable t) {
                callback.getErrorMsg(t.getMessage());
            }
        });
    }
}
