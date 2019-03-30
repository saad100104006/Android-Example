package online.ghuri.com.beacondoctorapp.Profile.Repository;

/**
 * Created by Tanvir on 3/14/2018.
 */

public interface ProfileCallback {
    void getUser(User user);
    void errorOccured(String msg);
}
