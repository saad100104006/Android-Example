package online.ghuri.com.beacondoctorapp.Profile.ViewModel;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import online.ghuri.com.beacondoctorapp.Profile.Repository.User;
import online.ghuri.com.beacondoctorapp.R;
import online.ghuri.com.beacondoctorapp.databinding.FragmentProfileTabBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileTabFragment extends Fragment {
   FragmentProfileTabBinding mBinding;
   private User mUser;
   private int mFragmentIndex;

   public static final int BASIC_INDEX=0;
   public static final int PROFESSIONAL_INDEX=1;
   public static final int EDUCATIONAL_INDEX=2;


    public ProfileTabFragment() {
        // Required empty public constructor
    }

    public void initFragment(User user , int index){
        mUser=user;
        mFragmentIndex=index;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_profile_tab, container, false);

        switch (mFragmentIndex){
            case BASIC_INDEX:
                mBinding.firstTv.setText("Date Of Birth: "+mUser.getBirth());
                mBinding.secondTv.setText("Marriage Day: "+mUser.getProfile().getMarr());
                mBinding.thirdTv.setText("Phone: "+mUser.getMobile());
                mBinding.fourthTv.setText("Email: "+mUser.getEmail());
                break;

            case PROFESSIONAL_INDEX:
                mBinding.firstTv.setText("Department: "+mUser.getProfile().getDept());
                mBinding.secondTv.setText("Institute: "+mUser.getProfile().getInst());
                mBinding.thirdTv.setText("Chamber-1: "+mUser.getProfile().getChambera());
                mBinding.fourthTv.setText("Chamber-2:: "+mUser.getProfile().getChamberb());

                break;

            case EDUCATIONAL_INDEX:
                mBinding.firstTv.setText("Medical College Batch: ");
                mBinding.secondTv.setText("College Batch: ");
                 mBinding.thirdTv.setVisibility(View.GONE);
                mBinding.fourthTv.setVisibility(View.GONE);

                break;
        }

        return mBinding.getRoot();
    }

}
