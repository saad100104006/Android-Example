package online.ghuri.com.beacondoctorapp.Dictionary.Repository;

import android.annotation.SuppressLint;
import android.os.Parcel;

import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;

/**
 * Created by Tanvir on 2/12/2018.
 */

@SuppressLint("ParcelCreator")
public class SearchResult implements SearchSuggestion {

   private String mMedicineName;

    public SearchResult(String medicineName) {
        this.mMedicineName = medicineName;
    }

    @Override
    public String getBody() {
        return mMedicineName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(mMedicineName);
    }
}
