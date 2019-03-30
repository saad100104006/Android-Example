package online.ghuri.com.beacondoctorapp.Dictionary.Repository;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Tanvir on 2/11/2018.
 */

public interface ApiEndPoint {

    @GET("words/{word}")
    Call<MedicalWordDictionary> getWordList(@Path("word") String medicineQuery, @Header("Authorization") String token);

    @GET("meanings/{word}")
    Call<MedicalWordMeaning> getWordMeaning(@Path("word") String medicineQuery, @Header("Authorization") String token);
}
