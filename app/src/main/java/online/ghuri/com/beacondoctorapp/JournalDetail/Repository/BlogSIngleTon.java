package online.ghuri.com.beacondoctorapp.JournalDetail.Repository;

import java.util.List;

import online.ghuri.com.beacondoctorapp.JournalDetail.Repository.NewModel.*;

/**
 * Created by Tanvir on 3/21/2018.
 */

public class BlogSIngleTon {
    private List<online.ghuri.com.beacondoctorapp.JournalDetail.Repository.NewModel.Blog> mBlogList;
    private static final BlogSIngleTon ourInstance = new BlogSIngleTon();

   public static BlogSIngleTon getInstance() {
        return ourInstance;
    }

    private BlogSIngleTon() {
    }

    public List<online.ghuri.com.beacondoctorapp.JournalDetail.Repository.NewModel.Blog> getmBlogList() {
        return mBlogList;
    }

    public void setmBlogList(List<online.ghuri.com.beacondoctorapp.JournalDetail.Repository.NewModel.Blog> mBlogList) {
        this.mBlogList = mBlogList;
    }
}
