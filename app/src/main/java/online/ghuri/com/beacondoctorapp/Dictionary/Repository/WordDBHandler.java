package online.ghuri.com.beacondoctorapp.Dictionary.Repository;

import android.content.Context;

import online.ghuri.com.beacondoctorapp.Preferences;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Tanvir on 2/11/2018.
 */

public class WordDBHandler {
    private static final String BASE_URL = "http://flexibac.com.bd/api/";
    private ApiEndPoint mApiEndPoint;
    private Context mContext;

    public WordDBHandler(Context mContext) {
        this.mContext = mContext;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mApiEndPoint = retrofit.create(ApiEndPoint.class);
    }

    public void getAllArticlesData(final WordCallback callBack, String query){
        String token= "Bearer "+Preferences.getToken(mContext);
        Call<MedicalWordDictionary> call=mApiEndPoint.getWordList(query,token);
        call.enqueue(new Callback<MedicalWordDictionary>() {
            @Override
            public void onResponse(Call<MedicalWordDictionary> call, Response<MedicalWordDictionary> response) {
                if (response.isSuccessful())
                    callBack.getWordlist(response.body().getDictionaryWordList());
            }

            @Override
            public void onFailure(Call<MedicalWordDictionary> call, Throwable t) {
                callBack.getError(t.getMessage());
            }
        });

    }


      public void getWordMeaning(String word, final WordCallback callback){
          String token= "Bearer "+Preferences.getToken(mContext);
        Call<MedicalWordMeaning> call=mApiEndPoint.getWordMeaning(word,token);
        call.enqueue(new Callback<MedicalWordMeaning>() {
            @Override
            public void onResponse(Call<MedicalWordMeaning> call, Response<MedicalWordMeaning> response) {
                if (response.isSuccessful()){
                    callback.getWordMeaning(response.body().getWordMeaning());
                }
            }

            @Override
            public void onFailure(Call<MedicalWordMeaning> call, Throwable t) {

            }
        });
      }
}
