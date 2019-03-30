package online.ghuri.com.beacondoctorapp.JournalDetail.Presenter;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;


import online.ghuri.com.beacondoctorapp.JournalDetail.Repository.JournalDetailDbHandler;
import online.ghuri.com.beacondoctorapp.JournalDetail.Repository.JournalDetailRepositoryCallback;
import online.ghuri.com.beacondoctorapp.JournalDetail.Repository.JournalSelectedTopicsGroup;
import online.ghuri.com.beacondoctorapp.JournalDetail.Repository.NewModel.Blog;
import online.ghuri.com.beacondoctorapp.JournalDetail.Repository.NewModel.Issue;
import online.ghuri.com.beacondoctorapp.JournalDetail.Repository.NewModel.Journal;
import online.ghuri.com.beacondoctorapp.JournalDetail.Repository.NewModel.Journals;
import online.ghuri.com.beacondoctorapp.JournalDetail.ViewModel.JournalDetailViewModelCallback;

/**
 * Created by Tanvir on 3/13/2018.
 */

public class JournalDetailsPresenter {
    private JournalDetailViewModelCallback mViewModel;
    private JournalDetailDbHandler mDbhandler;

    private String mTopicsName;
    private String mCategoryID;

    public JournalDetailsPresenter(JournalDetailViewModelCallback mCallBack, JournalDetailDbHandler mDbhandler, String mTopicsName, String mCategoryID) {
        this.mViewModel = mCallBack;
        this.mDbhandler = mDbhandler;

        this.mTopicsName = mTopicsName;
        this.mCategoryID=mCategoryID;
        getJournal();
    }

    private void getJournal(){
        mDbhandler.getJournal(mCategoryID, new JournalDetailRepositoryCallback() {


            @Override
            public void getJournal(Journals journalList) {
                List<JournalSelectedTopicsGroup> journals=convertJournalToDesireOutput(journalList.getJournals());
                mViewModel.showJournal(journals);
                addIssuesFileInBlog(journalList.getJournals());
            }

            @Override
            public void errorOccured(String msg) {
                Log.i("error_msg",msg);
            }
        });
    }

    private void addIssuesFileInBlog(List<online.ghuri.com.beacondoctorapp.JournalDetail.Repository.NewModel.Journal> journals) {
        for (Journal journal :
                journals) {
            for (Issue issue :
                    journal.getIssues()) {
                List<Blog> blogs=issue.getBlogs();
                if (!issue.getFile().equals("")){
                   Blog blog=new Blog();
                   blog.setFile(issue.getFile());
                   blog.setName(issue.getFile());
                   blogs.add(blog);
                }
            }
        }
    }

    private List<JournalSelectedTopicsGroup> convertJournalToDesireOutput(List<online.ghuri.com.beacondoctorapp.JournalDetail.Repository.NewModel.Journal> journalList) {

        List<JournalSelectedTopicsGroup> journals=new ArrayList<>();

        for (online.ghuri.com.beacondoctorapp.JournalDetail.Repository.NewModel.Journal journal:
           journalList) {

            String journalName=journal.getName();





            journals.add(new JournalSelectedTopicsGroup(journalName,journal.getIssues()));


        }

        return journals;
    }





}
