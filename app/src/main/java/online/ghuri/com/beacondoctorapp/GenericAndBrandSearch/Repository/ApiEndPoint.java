package online.ghuri.com.beacondoctorapp.GenericAndBrandSearch.Repository;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Tanvir on 2/12/2018.
 */

public interface ApiEndPoint {

    @GET("gbsearch")
    Call<MedicineInfoRoot> getMedicineInfoByGenericName(@Header("Authorization") String token , @Query("key") String medicineName);

    @GET("gb_brand_search")
    Call<MedicineInfoRoot> getMedicineInfoByBrandName(@Header("Authorization") String token , @Query("key") String medicineName);

    @GET("gb_company_search")
    Call<MedicineInfoRoot> getMedicineInfoByCompanyName(@Header("Authorization") String token , @Query("key") String medicineName);


}
