package online.ghuri.com.beacondoctorapp.ConferenceNews.Repository;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * Created by Tanvir on 3/5/2018.
 */

public interface ApiEndPoint {
    @GET("conferenceNews")
    Call<ConferenceResponse> getConferenceNewsList(@Header("Authorization") String token);

}
