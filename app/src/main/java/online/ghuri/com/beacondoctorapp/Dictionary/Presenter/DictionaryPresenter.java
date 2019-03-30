package online.ghuri.com.beacondoctorapp.Dictionary.Presenter;

import android.view.View;

import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;

import java.util.ArrayList;
import java.util.List;

import online.ghuri.com.beacondoctorapp.Dictionary.Repository.SearchResult;
import online.ghuri.com.beacondoctorapp.Dictionary.Repository.WordCallback;
import online.ghuri.com.beacondoctorapp.Dictionary.Repository.WordDBHandler;
import online.ghuri.com.beacondoctorapp.Dictionary.View.DictionaryViewCallback;

/**
 * Created by Tanvir on 2/11/2018.
 */

public class DictionaryPresenter {
    private DictionaryViewCallback mCallback;
    WordDBHandler mDbHandler;
    private List<SearchResult> mSearchResults;
    private String mSelectedWord;
    private String mWordMeaning;

    public DictionaryPresenter(DictionaryViewCallback mCallback,WordDBHandler handler) {
        this.mCallback = mCallback;
        mDbHandler=handler;
    }

    public void findWords(String newQuery) {
        mCallback.wordTextVisibility(View.GONE);
        if (newQuery.length()<3){
            mCallback.clearSearchSuggestion();
            return;
        }
        mCallback.showSearchProgress();

        mDbHandler.getAllArticlesData(new WordCallback() {
            @Override
            public void getWordlist(List<String> words) {
                SearchSuggestion searchSuggestion;
                if (mSearchResults==null)
               mSearchResults= convertStringToSearchResult(words);
                else {
                    List<SearchResult> results=convertStringToSearchResult(words);
                    mSearchResults.clear();
                    mSearchResults.addAll(results);
                }
                mCallback.getWordList(mSearchResults);
                mCallback.hideSearchProgress();
            }

            @Override
            public void getError(String msg) {

            }

            @Override
            public void getWordMeaning(String meaning) {

            }
        },newQuery);
    }

    private List<SearchResult> convertStringToSearchResult(List<String> words) {
        List<SearchResult> results=new ArrayList<>();
        for (String word :
                words) {
            SearchResult result=new SearchResult(word);
            results.add(result);
        }
        return results;
    }

    public void onClickSearchResult(SearchSuggestion searchSuggestion) {
        SearchResult result= (SearchResult) searchSuggestion;
        mSelectedWord=result.getBody();
        mCallback.searchBarTitle(mSelectedWord);
        mCallback.clearSearchSuggestion();
       mDbHandler.getWordMeaning(mSelectedWord, new WordCallback() {
           @Override
           public void getWordlist(List<String> words) {

           }

           @Override
           public void getError(String msg) {

           }

           @Override
           public void getWordMeaning(String meaning) {
                mCallback.wordTextVisibility(View.VISIBLE);
                mCallback.setWordText(meaning);

           }
       });


    }

    public void backClicked() {
        mCallback.wordTextVisibility(View.VISIBLE);
    }
}
