package online.ghuri.com.beacondoctorapp;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import online.ghuri.com.beacondoctorapp.databinding.FragmentJournalBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsPaperFragment extends Fragment {
    private FragmentJournalBinding mBinding;

    public NewsPaperFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       mBinding= DataBindingUtil.inflate(inflater,R.layout.fragment_journal,container,false);
        mBinding.journalRecycler.setLayoutManager(new GridLayoutManager(getActivity(),2));
        mBinding.journalRecycler.setAdapter(new NewsPaperAdapter());





        return mBinding.getRoot();
    }

    public class NewsPaperViewHolder extends RecyclerView.ViewHolder{
        Button newsPaperBtn;
        public NewsPaperViewHolder(View itemView) {
            super(itemView);
            newsPaperBtn=itemView.findViewById(R.id.news_paper_btn);
        }
    }


    public class NewsPaperAdapter extends RecyclerView.Adapter<NewsPaperViewHolder>{

        @Override
        public NewsPaperViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
         View view=LayoutInflater.from(getActivity())
                 .inflate(R.layout.row_news_paper,parent,false);
            return new NewsPaperViewHolder(view);
        }

        @Override
        public void onBindViewHolder(NewsPaperViewHolder holder, int position) {
        holder.newsPaperBtn.setText(mNewsPaperList[position]);
        }

        @Override
        public int getItemCount() {
            return mNewsPaperList.length;
        }
    }


    public String[] mNewsPaperList=new String[]{
            "Prothom Alo",
            "The Daily Star",
            "Ittefaq",
            "Janakantha",
            "Bangladesh Pratidin",
            "New Age",
            "The Independant",
            "The Daily Observer"
    };

}
