package online.ghuri.com.beacondoctorapp.JournalCategory.Repository;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * Created by Tanvir on 5/7/2018.
 */

public interface ApiEndPoint {

    @GET("categoryList")
    Call<CategoriesResponse> getCategories(@Header("Authorization") String token);
}
