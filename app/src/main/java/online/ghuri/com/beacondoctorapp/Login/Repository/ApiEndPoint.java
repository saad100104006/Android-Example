package online.ghuri.com.beacondoctorapp.Login.Repository;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Tanvir on 2/28/2018.
 */

public interface ApiEndPoint {

    @FormUrlEncoded
    @POST("userRegister")
    Call<SuccessResponse> userRegisterPostRequest(@Field("name") String userName,
                                                  @Field("email") String userEmail,
                                                  @Field("password") String password,
                                                  @Field("mobile") String mobile,
                                                  @Field("birth") String birthDate,
                                                  @Field("bmdc") String bmdcNo
                                                    );



    @FormUrlEncoded
    @POST("userLogin")
    Call<SuccessResponse> userLoginPostRequest(
                                                  @Field("email") String userEmail,
                                                  @Field("password") String password

    );



}
