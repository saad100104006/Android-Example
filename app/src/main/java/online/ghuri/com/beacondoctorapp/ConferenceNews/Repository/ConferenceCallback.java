package online.ghuri.com.beacondoctorapp.ConferenceNews.Repository;

import java.util.List;

/**
 * Created by Tanvir on 3/5/2018.
 */

public interface ConferenceCallback {

    void getConferenceNewsList(List<Conference> conferences);
}
