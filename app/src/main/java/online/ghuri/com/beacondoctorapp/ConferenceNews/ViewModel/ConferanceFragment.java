package online.ghuri.com.beacondoctorapp.ConferenceNews.ViewModel;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import online.ghuri.com.beacondoctorapp.ConferenceNews.Presenter.ConferencePresenter;
import online.ghuri.com.beacondoctorapp.ConferenceNews.Repository.Conference;
import online.ghuri.com.beacondoctorapp.ConferenceNews.Repository.ConferenceDBHandler;
import online.ghuri.com.beacondoctorapp.R;
import online.ghuri.com.beacondoctorapp.databinding.FragmentConferanceBinding;


/**
 * A simple {@link Fragment} subclass.
 */
public class ConferanceFragment extends Fragment implements ConferenceNewsViewModel {

    private FragmentConferanceBinding mBinding;
    private ConferencePresenter mPresenter;
    private ConferenceNewsAdapter mAdapter;

    public ConferanceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding= DataBindingUtil.inflate(inflater, R.layout.fragment_conferance, container, false);
        mPresenter=new ConferencePresenter(this,new ConferenceDBHandler(getActivity()));
        mPresenter.getAllConferenceList();
        return mBinding.getRoot();
    }

    @Override
    public void showConferenceList(List<Conference> conferences) {
        if (mAdapter==null){
            mAdapter=new ConferenceNewsAdapter(conferences);
            mBinding.conferenceRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
            mBinding.conferenceRecycler.setAdapter(mAdapter);
        }
    }

    @Override
    public void showProgressbar() {

    }

    @Override
    public void closeProgressbar() {

    }

    private class ConferenencViewHolder extends RecyclerView.ViewHolder{
        private TextView nameTv, scheduleTv,placeTv;
        public ConferenencViewHolder(View itemView) {
            super(itemView);
            nameTv=itemView.findViewById(R.id.conference_name_tv);
            scheduleTv=itemView.findViewById(R.id.conference_schedule_tv);
            placeTv=itemView.findViewById(R.id.conference_place_tv);

        }
    }

    private class ConferenceNewsAdapter extends RecyclerView.Adapter<ConferenencViewHolder>{

        private List<Conference> mConferenceList;

        private ConferenceNewsAdapter(List<Conference> mConferenceList) {
            this.mConferenceList = mConferenceList;
        }

        @Override
        public ConferenencViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view=LayoutInflater.from(getActivity())
                    .inflate(R.layout.row_conference,parent,false);
            return new ConferenencViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ConferenencViewHolder holder, int position) {
            Conference conference=mConferenceList.get(position);
            holder.nameTv.setText(conference.getTitle());
            String schedule=mPresenter.getSchedule(conference);
            holder.scheduleTv.setText(schedule);
            holder.placeTv.setText(conference.getAddr());

        }

        @Override
        public int getItemCount() {
            return mConferenceList.size();
        }
    }
}
