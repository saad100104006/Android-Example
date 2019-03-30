package online.ghuri.com.beacondoctorapp.JournalDetail.Repository;

import online.ghuri.com.beacondoctorapp.JournalDetail.Repository.NewModel.NewJournalResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Tanvir on 3/13/2018.
 */

public interface ApiEndPoint {

   /* @GET("journal/{topicName}")
    Call<JournalResponse> getJournalDetails(@Path("topicName") String topicName,@Header("Authorization") String token);*/
   @GET("journalApi/{id}")
    Call<NewJournalResponse>getJournalList(@Header("Authorization") String token, @Path("id") String id);
}
