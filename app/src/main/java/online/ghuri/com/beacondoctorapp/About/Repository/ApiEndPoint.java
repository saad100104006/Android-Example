package online.ghuri.com.beacondoctorapp.About.Repository;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * Created by Tanvir on 3/27/2018.
 */

public interface ApiEndPoint {
    @GET("about")
    Call<AboutResponse> getAbout(@Header("Authorization") String token);

}
