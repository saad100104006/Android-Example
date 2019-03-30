package online.ghuri.com.beacondoctorapp.JournalDetail.Repository;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

import online.ghuri.com.beacondoctorapp.JournalDetail.Repository.NewModel.*;

/**
 * Created by Tanvir on 3/13/2018.
 */

public class JournalSelectedTopicsGroup extends ExpandableGroup<online.ghuri.com.beacondoctorapp.JournalDetail.Repository.NewModel.Issue> {
    public JournalSelectedTopicsGroup(String title, List<online.ghuri.com.beacondoctorapp.JournalDetail.Repository.NewModel.Issue> items) {
        super(title, items);
    }
}
