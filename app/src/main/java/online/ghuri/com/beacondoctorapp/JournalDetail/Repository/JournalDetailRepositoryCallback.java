package online.ghuri.com.beacondoctorapp.JournalDetail.Repository;

import java.util.List;

import online.ghuri.com.beacondoctorapp.JournalDetail.Repository.NewModel.Journals;

/**
 * Created by Tanvir on 3/13/2018.
 */

public interface JournalDetailRepositoryCallback {

    void getJournal(Journals journalList);
    void errorOccured(String msg);
}
