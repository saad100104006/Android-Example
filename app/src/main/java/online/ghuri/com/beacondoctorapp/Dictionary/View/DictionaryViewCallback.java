package online.ghuri.com.beacondoctorapp.Dictionary.View;

import java.util.List;

import online.ghuri.com.beacondoctorapp.Dictionary.Repository.SearchResult;

/**
 * Created by Tanvir on 2/11/2018.
 */

public interface DictionaryViewCallback {

    public void getWordList(List<SearchResult> words);
    public void showSearchProgress();
    public void hideSearchProgress();
    public void clearSearchSuggestion();
    public void searchBarTitle(String word);
    public void clearSearchFocus();

    void setWordText(String wordMeaning);
    void wordTextVisibility(int visibility);
}
