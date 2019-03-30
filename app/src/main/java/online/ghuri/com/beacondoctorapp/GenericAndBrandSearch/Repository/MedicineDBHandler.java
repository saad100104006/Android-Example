package online.ghuri.com.beacondoctorapp.GenericAndBrandSearch.Repository;

import android.content.Context;

import online.ghuri.com.beacondoctorapp.Preferences;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Tanvir on 2/12/2018.
 */

public class MedicineDBHandler {
    private static final String BASE_URL = "http://flexibac.com.bd/api/";
    private ApiEndPoint mApiEndPoint;
    private Context mContext;
    private  Call<MedicineInfoRoot> call;
    public static final int GENERIC_TAG=0;
    public static final int BRAND_TAG=1;
    public static final int COMPANY_TAG=2;

    public MedicineDBHandler(Context mContext) {
        this.mContext = mContext;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mApiEndPoint = retrofit.create(ApiEndPoint.class);

    }

    public void getMedicineInfoList(final String query,int searchTag, final SearchDBCallBack callBack){
        String token="Bearer "+ Preferences.getToken(mContext);

        if (call!=null && call.isExecuted()){
            call.cancel();
        }
        switch (searchTag){
            case GENERIC_TAG:
                call=mApiEndPoint.getMedicineInfoByGenericName(token,query);
                break;
            case BRAND_TAG:
                call=mApiEndPoint.getMedicineInfoByBrandName(token,query);
                break;
                default:
                    call=mApiEndPoint.getMedicineInfoByCompanyName(token,query);
                    break;

        }

        call.enqueue(new Callback<MedicineInfoRoot>() {
            @Override
            public void onResponse(Call<MedicineInfoRoot> call, Response<MedicineInfoRoot> response) {

                if (response.isSuccessful()){
                    callBack.getMedicineInfo(response.body().getMedicineInfo(),query);
                }
            }

            @Override
            public void onFailure(Call<MedicineInfoRoot> call, Throwable t) {
                callBack.getErrorMsg(t.getMessage());
            }
        });
    }




}
