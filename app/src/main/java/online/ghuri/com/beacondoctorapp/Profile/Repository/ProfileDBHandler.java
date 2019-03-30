package online.ghuri.com.beacondoctorapp.Profile.Repository;

import android.content.Context;
import android.util.Log;

import io.realm.Realm;
import io.realm.RealmResults;
import online.ghuri.com.beacondoctorapp.Preferences;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Tanvir on 3/14/2018.
 */

public class ProfileDBHandler {
    private static final String BASE_URL="http://flexibac.com.bd/api/";
    private ApiEndPoint mApiEndPoint;
    private Context mContext;
    private Realm mRealm;

    public ProfileDBHandler(Context mContext) {
        this.mContext = mContext;
        Retrofit retrofit=new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        mApiEndPoint=retrofit.create(ApiEndPoint.class);
        Realm.init(mContext);
        mRealm=Realm.getDefaultInstance();
    }

    public void getProfileInfoFromRemote(final ProfileCallback callback){
        String token= "Bearer "+ Preferences.getToken(mContext);

        Call<UserInfoResponse> responseCall=mApiEndPoint.getUserDetails(token);
        responseCall.enqueue(new Callback<UserInfoResponse>() {
            @Override
            public void onResponse(Call<UserInfoResponse> call, Response<UserInfoResponse> response) {
                if (response.isSuccessful()){
                    callback.getUser(response.body().getUser());
                }else {
                    callback.errorOccured("response Unsuccessfull");
                }
            }

            @Override
            public void onFailure(Call<UserInfoResponse> call, Throwable t) {
                callback.errorOccured(t.getMessage());
            }
        });
    }


    public void getProfileInfoFromLocal(ProfileCallback callback){
        Log.i("Database_test","from_local");
        RealmResults<User> results=mRealm.where(User.class).findAll();
      if (results.size()>0)
       callback.getUser( results.get(0));
        else
            callback.errorOccured("NUI");

    }

    public void saveProfileInfo(User user){
        mRealm.beginTransaction();
        mRealm.insert(user);
        mRealm.commitTransaction();

        Log.i("Database_test","user saved");
    }

    public void updateUserField(String degree, String desig, String department, String institute, String chamber1, String chamber2, String phoneNumber, String email, String birthDay, String marriageDay, String faccebookLink) {

        Log.i("Database_test","Update Field");
        User user=mRealm.where(User.class).findFirst();
        mRealm.beginTransaction();
        user.getProfile().setDegree(degree);
        user.getProfile().setDesig(desig);
        user.getProfile().setDept(department);
        user.getProfile().setInst(institute);
        user.getProfile().setChambera(chamber1);
        user.getProfile().setChamberb(chamber2);
        user.setMobile(phoneNumber);
        user.setEmail(email);
        user.setBirth(birthDay);
        user.getProfile().setMarr(marriageDay);
        user.getProfile().setFb(faccebookLink);
        mRealm.commitTransaction();


    }

    public void saveUserProfilePic(byte[] byteData) {
        User user=mRealm.where(User.class).findFirst();
        mRealm.beginTransaction();
        user.setmProfilePicByteData(byteData);
        mRealm.commitTransaction();

    }

    public String getToken(){
       return Preferences.getToken(mContext);
    }
}
