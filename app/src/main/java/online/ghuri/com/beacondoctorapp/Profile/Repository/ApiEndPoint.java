package online.ghuri.com.beacondoctorapp.Profile.Repository;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * Created by Tanvir on 3/14/2018.
 */

public interface ApiEndPoint {

    @GET("userDetails")
    Call<UserInfoResponse> getUserDetails(@Header("Authorization") String token);
}
