package online.ghuri.com.beacondoctorapp.GenericAndBrandSearch.ViewModel;

import java.util.List;

import online.ghuri.com.beacondoctorapp.GenericAndBrandSearch.Repository.MedicineInfo;

/**
 * Created by Tanvir on 2/12/2018.
 */

public interface SearchMedicineViewModel {
    public void showSearchProgress();
    public void hideSearchProgress();
    public void clearSearchSuggestion();
    public void searchBarTitle(String word);

    void showMedicineSearchResult(List<MedicineInfo> medicineInfoList);

    void setSearchBarHint(String hint);
}
