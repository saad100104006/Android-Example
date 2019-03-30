package online.ghuri.com.beacondoctorapp.JournalCategory.Repository;

import java.util.List;

/**
 * Created by Tanvir on 5/7/2018.
 */

public interface JournalCategoryDBHandlerCallback {
    void getCategory(List<Category> categorys);
    void getErrorMsg(String msg);
}
