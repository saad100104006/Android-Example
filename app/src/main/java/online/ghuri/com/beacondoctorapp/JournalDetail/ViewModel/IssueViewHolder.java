package online.ghuri.com.beacondoctorapp.JournalDetail.ViewModel;

import android.view.View;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import online.ghuri.com.beacondoctorapp.R;

/**
 * Created by Tanvir on 3/13/2018.
 */

public class IssueViewHolder extends GroupViewHolder{

    TextView rowNameTv;

    public IssueViewHolder(View itemView) {
        super(itemView);
        rowNameTv=itemView.findViewById(R.id.issue_name_tv);
    }
}
