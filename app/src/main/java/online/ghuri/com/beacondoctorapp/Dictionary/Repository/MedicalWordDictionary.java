
package online.ghuri.com.beacondoctorapp.Dictionary.Repository;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MedicalWordDictionary {

    @SerializedName("words")
    @Expose
    private List<String> dictionaryWordList = null;

    public List<String> getDictionaryWordList() {
        return dictionaryWordList;
    }

    public void setDictionaryWordList(List<String> dictionaryWordList) {
        this.dictionaryWordList = dictionaryWordList;
    }

}
