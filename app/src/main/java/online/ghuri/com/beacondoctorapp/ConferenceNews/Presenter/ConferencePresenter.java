package online.ghuri.com.beacondoctorapp.ConferenceNews.Presenter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import online.ghuri.com.beacondoctorapp.ConferenceNews.Repository.Conference;
import online.ghuri.com.beacondoctorapp.ConferenceNews.Repository.ConferenceCallback;
import online.ghuri.com.beacondoctorapp.ConferenceNews.Repository.ConferenceDBHandler;
import online.ghuri.com.beacondoctorapp.ConferenceNews.ViewModel.ConferenceNewsViewModel;

/**
 * Created by Tanvir on 3/5/2018.
 */

public class ConferencePresenter {
    private ConferenceNewsViewModel mViewModel;
    private ConferenceDBHandler mDBhandler;

    public ConferencePresenter(ConferenceNewsViewModel mViewModel, ConferenceDBHandler mDBhandler) {
        this.mViewModel = mViewModel;
        this.mDBhandler = mDBhandler;
    }

    public void getAllConferenceList(){
        mViewModel.showProgressbar();
        mDBhandler.getConferenceNewsList(new ConferenceCallback() {
            @Override
            public void getConferenceNewsList(List<Conference> conferences) {
                mViewModel.showConferenceList(conferences);
                mViewModel.closeProgressbar();
            }
        });
    }

    public String getSchedule(Conference conference) {
        String start=conference.getStart().split(" ")[0];
        String end=conference.getEnd().split(" ")[0];
        Date startDate=null,endDate=null;

        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyyy-MM-dd");
        try {
            startDate=dateFormat.parse(start);
            endDate=dateFormat.parse(end);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        dateFormat=new SimpleDateFormat("MMMM dd, yyyy");

        String startOutput=dateFormat.format(startDate);
        String endOutput=dateFormat.format(endDate);


        return startOutput+" - "+endOutput;
    }
}
