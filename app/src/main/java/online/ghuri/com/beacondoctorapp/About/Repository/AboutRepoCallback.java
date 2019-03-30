package online.ghuri.com.beacondoctorapp.About.Repository;

/**
 * Created by Tanvir on 3/27/2018.
 */

public interface AboutRepoCallback {

    public void getAbout(About about);
    public void errorMsg(String msg);
}
