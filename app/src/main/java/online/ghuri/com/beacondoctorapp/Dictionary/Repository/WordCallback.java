package online.ghuri.com.beacondoctorapp.Dictionary.Repository;

import java.util.List;

/**
 * Created by Tanvir on 2/11/2018.
 */

public interface WordCallback {
    void getWordlist(List<String> words);
    void getError(String msg);
    void getWordMeaning(String meaning);
}
