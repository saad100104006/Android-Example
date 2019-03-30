package online.ghuri.com.beacondoctorapp.JournalDetail.ViewModel;

import java.util.List;

import online.ghuri.com.beacondoctorapp.JournalDetail.Repository.JournalSelectedTopicsGroup;

/**
 * Created by Tanvir on 3/13/2018.
 */

public interface JournalDetailViewModelCallback {
    void showJournal(List<JournalSelectedTopicsGroup> journals);
}
