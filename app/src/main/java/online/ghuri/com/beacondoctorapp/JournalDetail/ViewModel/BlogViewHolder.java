package online.ghuri.com.beacondoctorapp.JournalDetail.ViewModel;

import android.view.View;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

import online.ghuri.com.beacondoctorapp.R;

/**
 * Created by Tanvir on 3/13/2018.
 */

public class BlogViewHolder extends ChildViewHolder {
    TextView fileNameTv;
    View view;
    public BlogViewHolder(final View itemView) {
        super(itemView);
        fileNameTv=itemView.findViewById(R.id.file_name_tv);
        view=itemView;

    }
}
