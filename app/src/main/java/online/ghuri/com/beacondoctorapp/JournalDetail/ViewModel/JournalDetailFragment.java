package online.ghuri.com.beacondoctorapp.JournalDetail.ViewModel;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

import online.ghuri.com.beacondoctorapp.JournalDetail.Presenter.JournalDetailsPresenter;
import online.ghuri.com.beacondoctorapp.JournalDetail.Repository.BlogSIngleTon;
import online.ghuri.com.beacondoctorapp.JournalDetail.Repository.JournalDetailDbHandler;
import online.ghuri.com.beacondoctorapp.JournalDetail.Repository.JournalSelectedTopicsGroup;
import online.ghuri.com.beacondoctorapp.R;
import online.ghuri.com.beacondoctorapp.databinding.ActivityJournalDetailBinding;

public class JournalDetailFragment extends Fragment implements JournalDetailViewModelCallback {
    public static final String JOURNAL_TOPICS_KEY = "JOURNAL_KEY";
    public static final String JOURNAL_PIC_KEY = "journal_pic_key";
    private ActivityJournalDetailBinding mBinding;
    private JournalDetailsPresenter mPresenter;
    private String mTopicsName;
//    private int mPicId;
    private String mCategoryID;


    public void initialFragment(String topicName,String categoryID){
        mTopicsName=topicName;
        mCategoryID=categoryID;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       mBinding=DataBindingUtil.inflate(inflater,R.layout.activity_journal_detail,container,false);
//         mTopicsName=getActivity().getIntent().getStringExtra(JOURNAL_TOPICS_KEY);
//         mPicId=getActivity().getIntent().getIntExtra(JOURNAL_PIC_KEY,R.drawable.orthopaedics);
//        mBinding.toolbarPicImgView.setImageDrawable(ContextCompat.getDrawable(getActivity(),mPicId));
//        mBinding.toolbarTitle.setText(mTopicsName);
        mPresenter=new JournalDetailsPresenter(this,new JournalDetailDbHandler(getActivity()),mTopicsName,mCategoryID);

        return mBinding.getRoot();
    }



    @Override
    public void showJournal(List<JournalSelectedTopicsGroup> journals) {
        JournalDetailsAdapter adapter=new JournalDetailsAdapter(journals);
        mBinding.journalDetailRecyclerView.setAdapter(adapter);
        mBinding.journalDetailRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }


    public class JournalDetailsAdapter extends ExpandableRecyclerViewAdapter<IssueViewHolder,BlogViewHolder>{

        public JournalDetailsAdapter(List<? extends ExpandableGroup> groups) {
            super(groups);
        }

        @Override
        public IssueViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(getActivity())
                    .inflate(R.layout.row_issue,parent,false);
            return new IssueViewHolder(view);
        }

        @Override
        public BlogViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(getActivity())
                    .inflate(R.layout.row_blogs,parent,false);
            return new BlogViewHolder(view);
        }

        @Override
        public void onBindChildViewHolder(final BlogViewHolder holder, int flatPosition, final ExpandableGroup group, final int childIndex) {

           String fileName=((JournalSelectedTopicsGroup)group).getItems().get(childIndex).getName();
            holder.fileNameTv.setText(fileName);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    BlogSIngleTon.getInstance().setmBlogList(((JournalSelectedTopicsGroup)group).getItems().get(childIndex).getBlogs());
//                   startActivity(new Intent(getActivity(), JournalListFragment.class)
//                            .putExtra(JournalListFragment.JOURNAL_EXTRA_TAG,holder.fileNameTv.getText()));

                    JournalListFragment fragment=new JournalListFragment();

                    fragment.initialFragment(holder.fileNameTv.getText().toString());
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container,fragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                            .addToBackStack(null)
                            .commit();

                }
            });
        }

        @Override
        public void onBindGroupViewHolder(IssueViewHolder holder, int flatPosition, ExpandableGroup group) {
            holder.rowNameTv.setText(((JournalSelectedTopicsGroup)group).getTitle());
        }
    }
}
