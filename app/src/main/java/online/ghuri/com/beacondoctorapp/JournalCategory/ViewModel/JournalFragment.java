package online.ghuri.com.beacondoctorapp.JournalCategory.ViewModel;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.bumptech.glide.GenericTransitionOptions;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import online.ghuri.com.beacondoctorapp.JournalCategory.Presenter.JournalCategoryPresenter;
import online.ghuri.com.beacondoctorapp.JournalCategory.Repository.JournalCategoryDBHandler;
import online.ghuri.com.beacondoctorapp.JournalDetail.ViewModel.JournalDetailFragment;
import online.ghuri.com.beacondoctorapp.R;
import online.ghuri.com.beacondoctorapp.databinding.FragmentJournalBinding;


/**
 * A simple {@link Fragment} subclass.
 */
public class JournalFragment extends Fragment implements JournalCategoryViewModel{

    private FragmentJournalBinding mBinding;
    private FragmentCallback mCallBack;
    private JournalCategoryPresenter mPresenter;
    private JournalAdapter mAdapter;

    public JournalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_journal, container, false);

        final List<String> nameListAll = new ArrayList<>(Arrays.asList(mNameListTemp));
        final List<String> nameList = new ArrayList<>();

        final List<Integer> picListAll=new ArrayList<>(Arrays.asList(mPicIds));
        final List<Integer> picList=new ArrayList<>();

        mPresenter=new JournalCategoryPresenter(this,new JournalCategoryDBHandler(getActivity()));


       /* nameList.addAll(nameListAll);
        picList.addAll(picListAll);
        final JournalAdapter adapter = new JournalAdapter(nameList,picList);
        mBinding.journalRecycler.setAdapter(adapter);
        mBinding.journalRecycler.setLayoutManager(new GridLayoutManager(getActivity(), 4));

        mBinding.floatingSearchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, String newQuery) {

                nameList.clear();
                picList.clear();
                if (newQuery.isEmpty()) {
                    nameList.addAll(nameListAll);
                    picList.addAll(picListAll);
                    adapter.notifyDataSetChanged();
                    return;
                }
                for (int i = 0; i < nameListAll.size(); i++) {
                    String name = nameListAll.get(i);

                    if ((name.toLowerCase(),newQuery.toLowerCase())) {
                        nameList.add(name);
                        picList.add(picListAll.get(i));
                    }
                }

                adapter.notifyDataSetChanged();

            }
        });*/

     
        mBinding.floatingSearchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, String newQuery) {

              mPresenter.searchTextChanged(oldQuery,newQuery);

            }
        });

        return mBinding.getRoot();
    }

    @Override
    public void displayCategories() {
         mAdapter = new JournalAdapter();
        mBinding.journalRecycler.setAdapter(mAdapter);
        mBinding.journalRecycler.setLayoutManager(new GridLayoutManager(getActivity(), 4));
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void exitProgressBar() {

    }

    @Override
    public void refreshList() {
        mAdapter.notifyDataSetChanged();
    }


    public class JournalViewHolder extends RecyclerView.ViewHolder {
        TextView journalTv;
        CircleImageView picImg;

        public JournalViewHolder(View itemView) {
            super(itemView);
            journalTv = itemView.findViewById(R.id.journal_name_tv);
            picImg=itemView.findViewById(R.id.profile_image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int itemId=mPicIds[getLayoutPosition()];
//                    startActivity(new Intent(getActivity(), JournalDetailFragment.class)
//                    .putExtra(JournalDetailFragment.JOURNAL_TOPICS_KEY,journalTv.getText())
//                    .putExtra(JournalDetailFragment.JOURNAL_PIC_KEY,itemId));

                    JournalDetailFragment fragment=new JournalDetailFragment();
                    String id = mPresenter.getCategoryID(getLayoutPosition());
                    fragment.initialFragment(journalTv.getText().toString(),id);
                    mCallBack.updateToolbar(journalTv.getText().toString(),itemId);
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container,fragment)
                            .addToBackStack(null)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                            .commit();
                }
            });
        }
    }

    public class JournalAdapter extends RecyclerView.Adapter<JournalViewHolder> {
//        private List<String> nameList;
//       private List<Integer> picList;

        public JournalAdapter() {
//            this.nameList = nameList;
//            this.picList = picList;
        }

        @Override
        public JournalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.row_journal, parent, false);
            return new JournalViewHolder(view);
        }

        @Override
        public void onBindViewHolder(JournalViewHolder holder, int position) {
//            String medicineName=nameList.get(position);
//            if (medicineName.contains("\n")){
//                holder.journalTv.setMaxLines(2);
//            }else
//                holder.journalTv.setMaxLines(1);
//            holder.journalTv.setText(medicineName);
//            holder.picImg.setImageDrawable(ContextCompat.getDrawable(getActivity(),picList.get(position)));
            holder.journalTv.setText(mPresenter.getCategoryName(position));
            String url="http://flexibac.com.bd/journal/"+mPresenter.getPicName(position);
            Glide
                    .with(getActivity())
                    .load(url)
                    .thumbnail(0.1f)
                    .apply(new RequestOptions().override(105,105).centerCrop())
                    .into(holder.picImg);
        }

        @Override
        public int getItemCount() {
            return mPresenter.getTotalCategory();
        }
    }


    private String[] mNameListTemp = new String[]{

            "Orthopaedics",
            "Neurology",
            "Medicine",
            "Obstetrics\nGynecology",
            "Psychiatry",
            "Rheumatology",
            "Surgery",
            "Physical\nMedicine",
            "Cardiology",
            "Gastro\nenterology",
            "Urology",
            "Dermatology",
            "Diabetology",
            "Nephrology",
            "Ophthal\nmology",
            "Paediatrics",
            "Anatomy",
            "ENT",
            "Geriatrics",
            "Pain\nManagement"
    };

    private Integer[] mPicIds = new Integer[]{
            R.drawable.orthopaedics,
            R.drawable.neurology,
            R.drawable.medicine,
            R.drawable.obstetrics_gynecology,
            R.drawable.psychiatry,
            R.drawable.rheumatology,
            R.drawable.surgery,
            R.drawable.physical_medicine,
            R.drawable.cardiology,
            R.drawable.gastro_enterology,
            R.drawable.urology,
            R.drawable.dermatology,
            R.drawable.diabetology,
            R.drawable.nephrology,
            R.drawable.ophthalmology,
            R.drawable.paediatrics,
            R.drawable.anatomy,
            R.drawable.ent,
            R.drawable.geriatrics,
            R.drawable.pain_management

    };


    private boolean isStringMatches(String original, String toCompare){
        boolean isMatch=false;
        for (int i=0;i<toCompare.length();i++){
            if (toCompare.charAt(i)==original.charAt(i)){
                isMatch=true;
            }else {
                return false;
            }
        }

        return isMatch;
    }


    public interface FragmentCallback{
        void updateToolbar(String title, int picId);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallBack= (FragmentCallback) context;
    }

    @Override
    public void onStart() {
        super.onStart();
        mCallBack.updateToolbar("Journal",R.drawable.journal);
    }
}
