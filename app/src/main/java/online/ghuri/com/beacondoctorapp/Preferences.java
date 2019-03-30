package online.ghuri.com.beacondoctorapp;

import android.content.Context;
import android.preference.PreferenceManager;

/**
 * Created by Tanvir on 2/28/2018.
 */

public  class Preferences {

    private static final String TOKEN_KEY = "token_key";

    public static void setToken(Context context, String token){
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString(TOKEN_KEY,token)
                .apply();
    }

    public static String getToken(Context context){
    return     PreferenceManager.getDefaultSharedPreferences(context)
                .getString(TOKEN_KEY,null);

    }

}
