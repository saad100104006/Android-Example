package online.ghuri.com.beacondoctorapp.JournalCategory.Presenter;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import online.ghuri.com.beacondoctorapp.JournalCategory.Repository.Category;
import online.ghuri.com.beacondoctorapp.JournalCategory.Repository.JournalCategoryDBHandler;
import online.ghuri.com.beacondoctorapp.JournalCategory.Repository.JournalCategoryDBHandlerCallback;
import online.ghuri.com.beacondoctorapp.JournalCategory.ViewModel.JournalCategoryViewModel;

/**
 * Created by Tanvir on 5/7/2018.
 */

public class JournalCategoryPresenter {
    private JournalCategoryViewModel mViewModel;
    private JournalCategoryDBHandler mDBHandler;
    private List<Category> mCategoryList;
    private List<Category> mOrginalCategoryList;

    public JournalCategoryPresenter(JournalCategoryViewModel mViewModel, JournalCategoryDBHandler mDBHandler) {
        this.mViewModel = mViewModel;
        this.mDBHandler = mDBHandler;
        mCategoryList=new ArrayList<>();
        mOrginalCategoryList=new ArrayList<>();
        loadData();
    }

    private void loadData() {
        mViewModel.showProgressBar();
        mDBHandler.getCategory(new JournalCategoryDBHandlerCallback() {
            @Override
            public void getCategory(List<Category> categorys) {
                mCategoryList = categorys;
                mOrginalCategoryList.addAll(categorys);

                mViewModel.exitProgressBar();
                mViewModel.displayCategories();
            }

            @Override
            public void getErrorMsg(String msg) {
                Log.i("Category_test ", msg);
            }
        });
    }


    public int getTotalCategory() {
        return mCategoryList.size();
    }

    public String getCategoryName(int position) {
        return mCategoryList.get(position).getName();
    }

    public String getPicName(int position) {
        return mCategoryList.get(position).getImage();
    }

    public String getCategoryID(int position) {
        return mCategoryList.get(position).getId();
    }

    public void searchTextChanged(String oldQuery, String newQuery) {
        Log.i("query_test",oldQuery+" "+newQuery);

        if(oldQuery.isEmpty()){
            mCategoryList.addAll(mOrginalCategoryList);
            mViewModel.refreshList();
        }else {

            mCategoryList.clear();

            for (int i=0;i<mOrginalCategoryList.size();i++){
                String name = mOrginalCategoryList.get(i).getName();
                if (name.toLowerCase().contains(newQuery)){
                    mCategoryList.add(mOrginalCategoryList.get(i));
                }
            }

            mViewModel.refreshList();
        }

    }
}
