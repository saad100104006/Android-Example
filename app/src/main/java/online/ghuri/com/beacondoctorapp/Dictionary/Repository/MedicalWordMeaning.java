
package online.ghuri.com.beacondoctorapp.Dictionary.Repository;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MedicalWordMeaning {

    @SerializedName("meaning")
    @Expose
    private String wordMeaning;

    public String getWordMeaning() {
        return wordMeaning;
    }

    public void setWordMeaning(String wordMeaning) {
        this.wordMeaning = wordMeaning;
    }

}
