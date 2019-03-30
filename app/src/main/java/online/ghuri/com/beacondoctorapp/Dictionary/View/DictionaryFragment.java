package online.ghuri.com.beacondoctorapp.Dictionary.View;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabWidget;
import android.widget.Toast;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;

import java.util.List;

import online.ghuri.com.beacondoctorapp.Dictionary.Presenter.DictionaryPresenter;
import online.ghuri.com.beacondoctorapp.Dictionary.Repository.SearchResult;
import online.ghuri.com.beacondoctorapp.Dictionary.Repository.WordDBHandler;
import online.ghuri.com.beacondoctorapp.R;
import online.ghuri.com.beacondoctorapp.databinding.FragmentDictionaryBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class DictionaryFragment extends Fragment implements DictionaryViewCallback {

    private FragmentDictionaryBinding mBinding;
    private DictionaryPresenter mPresenter;
    public DictionaryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       mBinding= DataBindingUtil.inflate(inflater,R.layout.fragment_dictionary, container, false);
       mPresenter=new DictionaryPresenter(this,new WordDBHandler(getContext()));
       mBinding.floatingSearchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
           @Override
           public void onSearchTextChanged(String oldQuery, String newQuery) {
               mPresenter.findWords(newQuery);
           }
       });

       mBinding.floatingSearchView.setOnSearchListener(new FloatingSearchView.OnSearchListener() {
           @Override
           public void onSuggestionClicked(SearchSuggestion searchSuggestion) {
              mPresenter.onClickSearchResult(searchSuggestion);
              mBinding.floatingSearchView.clearSearchFocus();

           }

           @Override
           public void onSearchAction(String currentQuery) {

           }
       });

      mBinding.floatingSearchView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
          @Override
          public void onFocusChange(View v, boolean hasFocus) {
              if (!hasFocus)
                  mPresenter.backClicked();
          }
      });
        mBinding.floatingSearchView.setOnHomeActionClickListener(
                new FloatingSearchView.OnHomeActionClickListener() {

                    @Override
                    public void onHomeClicked() {
                        mPresenter.backClicked();
                        Toast.makeText(getActivity(), "testing", Toast.LENGTH_SHORT).show();
                    }});

        return mBinding.getRoot();
    }

    @Override
    public void getWordList(List<SearchResult> words) {
        mBinding.floatingSearchView.swapSuggestions(words);
    }

    @Override
    public void showSearchProgress() {
        mBinding.floatingSearchView.showProgress();
    }

    @Override
    public void hideSearchProgress() {
        mBinding.floatingSearchView.hideProgress();
    }

    @Override
    public void clearSearchSuggestion() {
        mBinding.floatingSearchView.clearSuggestions();
    }

    @Override
    public void searchBarTitle(String word) {
        mBinding.floatingSearchView.setSearchBarTitle("");
        mBinding.floatingSearchView.setSearchHint(word);

    }

    @Override
    public void clearSearchFocus() {
        mBinding.floatingSearchView.clearSearchFocus();
    }

    @Override
    public void setWordText(String wordMeaning) {
        mBinding.detailsWordTv.setText(wordMeaning);
    }

    @Override
    public void wordTextVisibility(int visibility) {
        mBinding.detailsWordTv.setVisibility(visibility);
    }
}
