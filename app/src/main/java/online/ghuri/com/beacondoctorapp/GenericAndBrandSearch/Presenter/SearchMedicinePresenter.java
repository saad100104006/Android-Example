package online.ghuri.com.beacondoctorapp.GenericAndBrandSearch.Presenter;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import online.ghuri.com.beacondoctorapp.GenericAndBrandSearch.Repository.MedicineDBHandler;
import online.ghuri.com.beacondoctorapp.GenericAndBrandSearch.Repository.MedicineInfo;
import online.ghuri.com.beacondoctorapp.GenericAndBrandSearch.Repository.SearchDBCallBack;
import online.ghuri.com.beacondoctorapp.GenericAndBrandSearch.ViewModel.SearchMedicineViewModel;

/**
 * Created by Tanvir on 2/12/2018.
 */

public class SearchMedicinePresenter {
    private SearchMedicineViewModel mViewModel;
    MedicineDBHandler mDBHandler;
    private List<MedicineInfo> mMedicineInfos;
    private int mSearchTag;


    public SearchMedicinePresenter(SearchMedicineViewModel mViewModel,MedicineDBHandler dbHandler) {
        this.mViewModel = mViewModel;
        mDBHandler=dbHandler;
        mMedicineInfos=new ArrayList<>();
        mViewModel.setSearchBarHint("Search Generic Name");
    }


    public void findMedicineInfo(final String newQuery) {
        Log.v("query_test",newQuery);
        if (newQuery.length()<3){
          //  mViewModel.clearSearchSuggestion();
            if (!mMedicineInfos.isEmpty()){
                mMedicineInfos.clear();
                mViewModel.showMedicineSearchResult(mMedicineInfos);
                mViewModel.clearSearchSuggestion();
                mViewModel.hideSearchProgress();
            }

        }else {

            mViewModel.showSearchProgress();

            mDBHandler.getMedicineInfoList(newQuery,mSearchTag,new SearchDBCallBack() {
                @Override
                public void getMedicineInfo(List<MedicineInfo> medicineInfoList,String query) {
                    /*if (mMedicineInfos.isEmpty()){
                        mMedicineInfos.addAll(medicineInfoList);
                    }else {
                        mMedicineInfos.clear();
                        mMedicineInfos.addAll(medicineInfoList);
                    }*/
                    if (query.equals(newQuery)){

                        Log.i("query_test",newQuery);

                        mMedicineInfos.clear();
                        mMedicineInfos.addAll(medicineInfoList);
                        mViewModel.showMedicineSearchResult(mMedicineInfos);
                        mViewModel.hideSearchProgress();
                    }
                }

                @Override
                public void getErrorMsg(String msg) {

                }
            });
        }

    }

    public void onGenericMenuItemClick() {

      //  mViewModel.searchBarTitle("");
        mViewModel.setSearchBarHint("Search Generic Name");
        mSearchTag=MedicineDBHandler.GENERIC_TAG;
    }

    public void onBrandMenuItemClick() {
//        mViewModel.searchBarTitle("");
        mViewModel.setSearchBarHint("Search Brand Name");
        mSearchTag=MedicineDBHandler.BRAND_TAG;




    }

    public void onCompanyMenuItemClick() {
//        mViewModel.searchBarTitle("");
        mViewModel.setSearchBarHint("Search Company Name");
        mSearchTag=MedicineDBHandler.COMPANY_TAG;




    }
}
