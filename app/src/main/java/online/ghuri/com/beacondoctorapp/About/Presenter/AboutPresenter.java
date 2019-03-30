package online.ghuri.com.beacondoctorapp.About.Presenter;

import online.ghuri.com.beacondoctorapp.About.Repository.About;
import online.ghuri.com.beacondoctorapp.About.Repository.AboutDbHandler;
import online.ghuri.com.beacondoctorapp.About.Repository.AboutRepoCallback;
import online.ghuri.com.beacondoctorapp.About.ViewModel.AboutViewModel;

/**
 * Created by Tanvir on 3/27/2018.
 */

public class AboutPresenter  {
    private AboutViewModel mViewModel;
    private AboutDbHandler mDbHandler;

    public AboutPresenter(AboutViewModel mViewModel, AboutDbHandler mDbHandler) {
        this.mViewModel = mViewModel;
        this.mDbHandler = mDbHandler;
        loadAboutData();
    }

    private void loadAboutData(){
        mViewModel.showProgressBar();
        mDbHandler.getAbout(new AboutRepoCallback() {
            @Override
            public void getAbout(About about) {
                mViewModel.showAboutpic("http://flexibac.com.bd/journal/"+about.getImg());
                mViewModel.showAboutText(about.getDes());
                mViewModel.exitProgressBar();
            }

            @Override
            public void errorMsg(String msg) {

            }
        });
    }


}
