package online.ghuri.com.beacondoctorapp.ConferenceNews.ViewModel;

import java.util.List;

import online.ghuri.com.beacondoctorapp.ConferenceNews.Repository.Conference;

/**
 * Created by Tanvir on 3/5/2018.
 */

public interface ConferenceNewsViewModel {
    void showConferenceList(List<Conference> conferences);
    void showProgressbar();
    void closeProgressbar();
}
