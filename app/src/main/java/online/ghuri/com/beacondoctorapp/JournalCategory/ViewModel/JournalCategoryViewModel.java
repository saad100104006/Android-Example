package online.ghuri.com.beacondoctorapp.JournalCategory.ViewModel;

/**
 * Created by Tanvir on 5/7/2018.
 */

public interface JournalCategoryViewModel {
    public void displayCategories();
    void showProgressBar();
    void exitProgressBar();

    void refreshList();
}
